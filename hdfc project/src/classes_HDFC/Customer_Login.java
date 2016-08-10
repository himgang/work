package classes_HDFC;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

/**
 * Servlet implementation class Customer_Login
 */
public class Customer_Login extends HttpServlet {
	ServletConfig config;
	public void init(ServletConfig config){
		this.config=config;
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 SessionFactory factory=null;
   	 Session session=null;
   	try
   		{
   			Configuration cfg = new Configuration();
   			PrintWriter out= res.getWriter();
   			RequestDispatcher dis=null;
   			cfg.configure("hibernate.cfg.xml"); 
   			factory = cfg.buildSessionFactory();
   			session = factory.openSession();
   			Transaction tx = session.beginTransaction();
   			Long login=Long.parseLong(req.getParameter("Customer_Id"));
   			String pass=req.getParameter("Customer_Password");
   			Criteria cr = session.createCriteria(applications.class);
   			cr.add(Restrictions.eq("Customer_Id", login));
   			List<applications> results = cr.list();
   			applications k=results.get(0);
   			Criteria crt = session.createCriteria(Login_Details.class);
   			crt.add(Restrictions.eq("Customer_Id", login));
   			List<Login_Details> result = crt.list();
   			Login_Details log=result.get(0);
   			/*System.out.println(k.getCustomer_Name());
   			System.out.println(k.getCustomer_Phone());
   			System.out.println(k.getAmount());
   			System.out.println(k.getTime());
   			System.out.println(k.getCustomer_Status());
   			System.out.println(k.getCustomer_EMI());
   			System.out.println(log.getCustomer_Password());
   			System.out.println(log.getCustomer_Id());*/
   			tx.commit();
   			if(results.isEmpty()){
   				out.println("LoginId or Pasword Doesn't Matchkkkk");
   			}
   			else if(log.getCustomer_Password().equals(pass)){
   			//	out.println("LoginId or Password Matches");
   				HttpSession sess=req.getSession();  
   				sess.setAttribute("id",login); 
   				req.setAttribute("name", k.getCustomer_Name());
   				req.setAttribute("Customer_Phone",Long.toString(k.getCustomer_Phone()));
   				req.setAttribute("Amount",Integer.toString(k.getAmount()));
   				req.setAttribute("Time",Integer.toString( k.getTime()));
   				req.setAttribute("Status",Integer.toString( k.getCustomer_Status()));
   				req.setAttribute("Customer_EMI", Integer.toString(k.getCustomer_EMI()));
   				dis=config.getServletContext().getRequestDispatcher("/Customer_Dash_css.jsp");
   				dis.include(req, res);
   				
   			}
   			else {
   				out.println("LoginId or Pasword Doesn't Match");
   			}
   			session.close();
   		}
   	catch (Exception e) {
   		e.printStackTrace();
   			PrintWriter out= res.getWriter();
			RequestDispatcher dis=null;
   			out.println("User Doesn't Exist");
   			System.out.println(e.getMessage());
			dis=config.getServletContext().getRequestDispatcher("/Customer_login_css.html");
			dis.include(req, res);
   	}
}
}
