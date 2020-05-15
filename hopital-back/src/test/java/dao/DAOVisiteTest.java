package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.AppConfig;
import model.Medecin;
import model.Patient;
import model.Visite;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:application-context.xml")
@ContextConfiguration(classes = AppConfig.class)
public class DAOVisiteTest {
	@Autowired(required = false)
	private IDAOVisite daoVisite;
	
	//Tester si l'instance existe bien ... :)
	@Test
	public void testDaoExists() {
		assertNotNull(this.daoVisite);
	}
	
	
	
	@Test
	@Transactional
	@Rollback
	public void testInsert() {
		assertNotNull(this.daoVisite);
		
		Visite visite = new Visite();
		Medecin medecin = new Medecin();
		Patient patient = new Patient();
		
		
		medecin.setId(2);
		patient.setSecu(666);
		
		visite.setDateVisite(LocalDate.now());
		visite.setPrix(24);
//		visite.setMedecin(medecin);
		visite.setPatient(patient);
		
		this.daoVisite.save(visite);
		
		
		
		//Si on veut tester, sans médecin par exemple
//		try {
//			this.daoVisite.save(visite);
//			fail("Y'a de médecin !!!!");
//		}
//		
//		catch (Exception e) {
//			//ici c'est normal parce qu'il y a pas de médecin
//		}
	}
	
	
	
	
//	@Test
	public void test() {
		assertTrue(true);
		assertEquals(2, (1 + 1));
//		assertEquals(2, (1 - 1));
		
		if (1 == 1) {
			fail("On devrait pas etre parce que l'id est null");
		}
	}
}