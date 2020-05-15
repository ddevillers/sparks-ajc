package fr.formation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/home") // PREFIXER TOUS LES MAPPINGS DE CETTE CLASSE
public class HomeController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@GetMapping("/connect")
	public String connect() {
		
		int param = 5;
		
		logger.debug("Je suis passé par ici {} : {}", param, param * 2);
		
		return "connect";
	}
}