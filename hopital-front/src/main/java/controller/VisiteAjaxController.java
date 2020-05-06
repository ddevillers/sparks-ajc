package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.IDAOVisite;
import model.Visite;

@Controller
public class VisiteAjaxController {
	@Autowired
	private IDAOVisite daoVisite;
	
	@GetMapping("/visite-ajax")
	@ResponseBody
	public String testAjax() {
		return "visites";
	}

	@PostMapping("/visite-ajax.json")
	@ResponseBody
	public Visite testAjaxJson(@RequestBody Visite visite) {
		this.daoVisite.save(visite);
		
		return visite;
	}

	@PostMapping("/visite-ajax.html")
	public String testAjaxHtml(@RequestBody Visite visite, Model model) {
		this.daoVisite.save(visite);
		
		visite = this.daoVisite.findById(visite.getId()).orElse(visite);
		model.addAttribute("v", visite);
		
		return "visite-part";
	}
}