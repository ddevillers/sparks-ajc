package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Compte;
import model.Medecin;
import model.Secretaire;



@WebServlet("/connect")
public class connect extends SpringServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext()
			.getRequestDispatcher("/WEB-INF/connect.jsp")
			.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String login=request.getParameter("login");
		String password=request.getParameter("password");
		
		Compte c = this.daoCompte.checkConnect(login, password);
		
		request.getSession().setAttribute("login", login);
	
		if(c instanceof Medecin) 
		{
			request.getSession().setAttribute("typeAccount", "Medecin");
			request.getSession().setAttribute("isConnect", "Y");
			
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/medecin.jsp").forward(request, response);
		}
		else if(c instanceof Secretaire) 
		{
			request.getSession().setAttribute("typeAccount", "Secretaire");
			request.getSession().setAttribute("isConnect", "Y");
			this.getServletContext().getRequestDispatcher("/WEB-INF/secretaire.jsp").forward(request, response);
		}
		else 
		{
			request.getSession().setAttribute("isConnect","N");
			doGet(request,response);
		}
		
		
	
	}

}
