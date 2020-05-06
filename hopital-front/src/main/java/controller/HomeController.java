package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.IDAOCompte;
import model.Compte;

@Controller
//@RequestMapping("/home") // PREFIXER TOUS LES MAPPINGS DE CETTE CLASSE
public class HomeController {
	@Autowired
	private IDAOCompte daoCompte;
	
	@GetMapping({ "/", "/connect" })
	public String connect(Model model) {
		return "connect";
	}
	
	
	@PostMapping("/connect")
	public String connect(
			@RequestParam(value = "login") String username,
			@RequestParam String password,
			HttpSession session, Model model) {
		
		if (username.isEmpty()) {
			model.addAttribute("error", "Username must not be empty");
			return "connect";
		}
		
		//On vérifie la connexion
		Compte compte = this.daoCompte.checkConnect(username, password);
		
		if (compte == null) {
			model.addAttribute("error", "Login incorrect");
			return "connect";
		}

		session.setAttribute("compte", compte);
		return "redirect:/visite";
	}
}