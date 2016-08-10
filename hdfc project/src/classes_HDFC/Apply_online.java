package classes_HDFC;
import java.lang.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class Apply_online extends HttpServlet {
	ServletConfig config;
	public void init(ServletConfig config){
		this.config=config;
	}
	 SessionFactory factory=null;
	 Session session=null;
	 Configuration cfg = new Configuration();
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		factory = cfg.buildSessionFactory();
		session = factory.openSession();
    	try
    		{
    			cfg.configure("hibernate.cfg.xml"); 
    			Transaction tx = session.beginTransaction();
    			applications applicant=new applications();
    			Login_Details log=new Login_Details();
    			applicant.setCustomer_Phone(Long.parseLong(req.getParameter("Customer_Phone")));
    			applicant.setAmount(Integer.parseInt(req.getParameter("Amount")));
    			applicant.setCustomer_Address(req.getParameter("Customer_Address"));
    			applicant.setCustomer_Age(Integer.parseInt(req.getParameter("Customer_Age")));
    			applicant.setCustomer_Id(applicant.getCustomer_Phone());
    			applicant.setCustomer_Name(req.getParameter("Customer_Name"));
    			applicant.setCustomer_Salary(Integer.parseInt(req.getParameter("Customer_Salary")));
    			Integer x=0;
    			applicant.setCustomer_Status(x);
    			applicant.setTime(Integer.parseInt(req.getParameter("Time")));
    			log.setCustomer_Id(applicant.getCustomer_Phone());
    			log.setCustomer_Password(req.getParameter("Customer_Password"));
    			// A = P (1 + r/n) ^ nt:
    			Integer Amount=applicant.getAmount();
    			
    			Integer Time= (12*applicant.getTime());
    			Double a=Math.pow((1+(13/1200)),Time);
    			Double Amt= Amount*a;
    			Integer EMI=(Amt.intValue())/Time;
    			applicant.setCustomer_EMI(EMI);
    			if(applicant.getCustomer_Salary()<= (2*EMI)){
    				applicant.setCustomer_Status(0);
    			}
    			else if(applicant.getCustomer_Salary()> (2*EMI)){
    				applicant.setCustomer_Status(1);
    			}
    	//		System.out.println(Amt+" "+ applicant.getCustomer_EMI());
    			session.save(applicant);
    			session.save(log);
    			tx.commit();
    		}
    	catch (Exception e) {
    			System.out.println("hello fool");
    			e.printStackTrace();
    			System.out.println(e.getMessage());
    	}
    	finally{
    		session.close();
			RequestDispatcher dis=null;
			dis=config.getServletContext().getRequestDispatcher("/main_css.html");
			dis.include(req, res);
    	}
}
}