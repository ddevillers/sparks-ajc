package app;

import dao.IDAOCompte;
import dao.IDAOPatient;
import dao.IDAOVisite;
import dao.jpa.DAOCompteJpa;
import dao.jpa.DAOPatientJpa;
import dao.jpa.DAOVisiteJpa;
import dao.jpa.DaoJpa;
import model.Medecin;
import model.Patient;
import model.Visite;

public class TestHibernate {
	public static void main(String[] args) {
		IDAOVisite daoVisite = HopitalContext.getDaoVisite();
		IDAOPatient daoPatient = HopitalContext.getDaoPatient();
		IDAOCompte daoCompte = HopitalContext.getDaoCompte();
		
		
		Medecin medecin = (Medecin) daoCompte.selectById(3);
//		Patient patient = daoPatient.selectById(11);
//		Patient patient = new Patient(845632, "ALBERT", "Dupont");
//		
//		Visite visite = new Visite(medecin, patient);
//		
//		daoPatient.insert(patient);
//		daoVisite.insert(visite);
//		
//		
//		patient = daoPatient.selectById(11);
//		patient.getAdresse().setVoie("de la soif");
//		daoPatient.update(patient);
		
		
//		
//		
		
//		Visite visite = daoVisite.selectById(3);
//		visite.setPrix(10);
//		daoVisite.update(visite);
		
//		daoVisite.delete(3);
		
		
		
		DaoJpa.close();
	}
}
