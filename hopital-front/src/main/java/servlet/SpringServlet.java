package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import dao.IDAOCompte;

public abstract class SpringServlet extends HttpServlet {
	@Autowired
	protected IDAOCompte daoCompte;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport //On demande à Spring de scan la classe pour injecter les dépendances
			.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}