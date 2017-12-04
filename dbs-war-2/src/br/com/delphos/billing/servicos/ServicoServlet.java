package br.com.delphos.billing.servicos;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServicoServlet
 */
@WebServlet("/ServicoServlet")
public class ServicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	   @EJB( lookup="java:global/dbs/BillingServiceBean")
	   private BillingService billingservice;
	   
	   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
	       response.setContentType("text/html;charset=UTF-8");
	       PrintWriter out = response.getWriter();
	       out.println("<html>");
	       out.println("<head>");
	       out.println("<title>Servlet FooServlet</title>");
	       out.println("</head>");
	       out.println("<body>");
	       out.println("<h1>FooRemote.echo returned: " + billingservice.printHello() + "</h1>");
	       out.println("</body>");
	       out.println("</html>");
	   }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
