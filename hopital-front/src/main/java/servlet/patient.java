package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import dao.IDAOPatient;
import model.Patient;


@WebServlet("/patient")
public class patient extends HttpServlet {
	@Autowired
	private IDAOPatient daoPatient;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport //On demande à Spring de scan la classe pour injecter les dépendances
			.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Patient> list = this.daoPatient.findAll();
		
		request.setAttribute("patients",list);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/page1.jsp").forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String nom= request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		int secu=Integer.parseInt(request.getParameter("secu"));
		
		Patient patient = daoPatient.findById(123).get(); //new Patient();
		
//		patient.setSecu(secu);
		patient.setNom(nom);
		patient.setPrenom(prenom);
		
		
		daoPatient.save(patient);
		System.out.println(patient);
		
		//doGet(request, response);
	}

}
