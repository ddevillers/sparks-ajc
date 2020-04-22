package dao.jpa;

import java.util.List;

import dao.IDAOPatient;
import model.Patient;

public class DAOPatientJpa extends DaoJpa implements IDAOPatient {
	@Override
	public void insert(Patient entity) {
		//insert => em.persist
		
		this.em.getTransaction().begin(); //On pense à démarrer la transaction
		
		try {
			this.em.persist(entity);
			this.em.getTransaction().commit(); //On pense à commit la transaction
		}
		
		catch (Exception e) { //Y'a un problème ??
			this.em.getTransaction().rollback(); //On annule la transaction
		}
	}

	@Override
	public void update(Patient entity) {
		// update => em.merge
				
		try {
			this.em.getTransaction().begin(); //On pense à démarrer la transaction
			this.em.merge(entity);
			this.em.getTransaction().commit(); //On pense à commit la transaction
		}
		
		catch (Exception e) { //Y'a un problème ??
			this.em.getTransaction().rollback(); //On annule la transaction
		}
	}

	@Override
	public void delete(Integer id) {
		// delete => em.remove ( em.merge ( ?? ) )
		
		try {
			Patient patientToRemove = new Patient();
			patientToRemove.setSecu(id);
			
			this.em.getTransaction().begin(); //On pense à démarrer la transaction
			this.em.remove(
				this.em.merge(patientToRemove) //On attache car la visite n'est pas attachée
			);
			
			this.em.getTransaction().commit(); //On pense à commit la transaction
		}
		
		catch (Exception e) { //Y'a un problème ??
			e.printStackTrace();
			this.em.getTransaction().rollback(); //On annule la transaction
		}
	}

	@Override
	public Patient selectById(Integer id) {
		// select by id => em.find
		return this.em.find(Patient.class, id);
	}

	@Override
	public List<Patient> selectAll() {
		return this.em
				.createQuery("select p from Patient p", Patient.class)
				.getResultList();
	}
}