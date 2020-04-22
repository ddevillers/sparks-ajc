package app;

import dao.IDAOCompte;
import dao.IDAOPatient;
import dao.IDAOVisite;
import dao.jpa.DAOCompteJpa;
import dao.jpa.DAOPatientJpa;
import dao.jpa.DAOVisiteJpa;

public class HopitalContext {
	private static IDAOVisite daoVisite;
	private static IDAOPatient daoPatient;
	private static IDAOCompte daoCompte;
	
	
	public static IDAOVisite getDaoVisite() {
		if (daoVisite == null) {
			daoVisite = new DAOVisiteJpa();
		}
		
		return daoVisite;
	}
	
	
	public static IDAOPatient getDaoPatient() {
		if (daoPatient == null) {
			daoPatient = new DAOPatientJpa();
		}
		
		return daoPatient;
	}
	
	
	public static IDAOCompte getDaoCompte() {
		if (daoCompte == null) {
			daoCompte = new DAOCompteJpa();
		}
		
		return daoCompte;
	}
}