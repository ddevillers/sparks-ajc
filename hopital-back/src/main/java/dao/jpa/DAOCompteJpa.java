package dao.jpa;

import java.util.List;

import dao.IDAOCompte;
import model.Compte;

public class DAOCompteJpa extends DaoJpa implements IDAOCompte {
	@Override
	public void insert(Compte entity) {
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
	public void update(Compte entity) {
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
			Compte compteToRemove = new Compte();
			compteToRemove.setId(id);
			
			this.em.getTransaction().begin(); //On pense à démarrer la transaction
			this.em.remove(
				this.em.merge(compteToRemove) //On attache car la visite n'est pas attachée
			);
			
			this.em.getTransaction().commit(); //On pense à commit la transaction
		}
		
		catch (Exception e) { //Y'a un problème ??
			e.printStackTrace();
			this.em.getTransaction().rollback(); //On annule la transaction
		}
	}

	@Override
	public Compte selectById(Integer id) {
		// select by id => em.find
		return this.em.find(Compte.class, id);
	}

	@Override
	public List<Compte> selectAll() {
		return this.em
				.createQuery("select c from Compte c", Compte.class)
				.getResultList();
	}
}