package classes_HDFC;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * Servlet implementation class Admin_Login
 */
public class Admin_Login extends HttpServlet {
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
   			String login1=new String("admin");
   			String pass1=new String ("HDFC");
   			String login=req.getParameter("Login_Id");
   			String pass=req.getParameter("Password");
   			if( login1.equals(login) && pass1.equals(pass)){
   				Criteria cr = session.createCriteria(applications.class);
   	   			List<applications> results = cr.list();
   	   			for( applications k: results){
   	   				System.out.println(k.getCustomer_Name());
   	   			}
   	   			req.setAttribute("results", results);
   				dis=config.getServletContext().getRequestDispatcher("/Admin_Dash_css.jsp");
   				dis.forward(req, res);
   				
   			}
   			else{
   				dis=config.getServletContext().getRequestDispatcher("/Admin_login_css.html");
   				dis.include(req, res);
   				
   			}
	}catch (Exception e) {
   		e.printStackTrace();
		PrintWriter out= res.getWriter();
		RequestDispatcher dis=null;
		out.println("User Doesn't Exist");
		System.out.println(e.getMessage());
		dis=config.getServletContext().getRequestDispatcher("/Admin_login_css.html");
		dis.include(req, res);

}
}
}	
