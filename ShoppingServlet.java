package basicprojects;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/shop")
public class ShoppingServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code,qty,sb;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		HttpSession session=request.getSession();
		System.out.println("Session max interval:"+session.getMaxInactiveInterval());
		System.out.println("Session id:"+session.getId());
		
		session.setMaxInactiveInterval(60);
		sb=request.getParameter("s");
		
		if(sb.equals("Add item"))
		{
			code=request.getParameter("pcode");
			qty=request.getParameter("t1");
			session.setAttribute(code,qty);
			response.sendRedirect("shopping.html");
		}
		else if(sb.equals("Remove item"))
		{
			code=request.getParameter("pcode");
			session.removeAttribute(code);
			response.sendRedirect("shopping.html");
		}
		else if(sb.equals("Show items"))
		{
			Enumeration e=session.getAttributeNames();
			if(e.hasMoreElements())
			{
				out.println("<h1><center>Items added to cart are<br><br>");
				while (e.hasMoreElements()) 
				{
					out.println("<body bgcolor=#F19DE2 >");
					String c=(String)e.nextElement();
					out.println("<br>");
					out.println("Product code:"+c);
					out.println("Quantity:"+session.getAttribute(c));
					
				}
			}
			else
			{
				out.println("<body bgcolor=#D86CA5>");
				out.println("<center><h2>No items in cart!!!");
			}
			out.println("<br><center>");
			out.println("<a href=shopping.html> <h2>Shop Again!!</h2></a>");
		}
		else if(sb.equals("Logout"))
		{
			session.invalidate();
			out.println("<body bgcolor=#D86CA5>");
			out.println("<br><br><br><br><center>");
			out.println("<a href=shopping.html> <h1>Shop Again!!</h1></a>");
		}
		out.close();
	}
}
