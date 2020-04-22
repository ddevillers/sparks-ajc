package dao.jpa;

import java.util.List;

import dao.IDAOVisite;
import model.Visite;

public class DAOVisiteJpa extends DaoJpa implements IDAOVisite {
	@Override
	public void insert(Visite entity) {
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
	public void update(Visite entity) {
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
		
//		this.em.remove( this.selectById(id) );
		
		try {
			Visite visiteToRemove = new Visite();
			visiteToRemove.setId(id);
			
			this.em.getTransaction().begin(); //On pense à démarrer la transaction
			this.em.remove(
				this.em.merge(visiteToRemove) //On attache car la visite n'est pas attachée
			);
			
			this.em.getTransaction().commit(); //On pense à commit la transaction
		}
		
		catch (Exception e) { //Y'a un problème ??
			e.printStackTrace();
			this.em.getTransaction().rollback(); //On annule la transaction
		}
		
		
		//Solution #15
//		this.em
//			.createQuery("delete from Visite v where v.id = :id")
//			.setParameter("id", id)
//			.executeUpdate();
	}

	@Override
	public Visite selectById(Integer id) {
		// select by id => em.find
		return this.em.find(Visite.class, id);
		
		//Ou bien ça ...
//		return this.em 
////				.createQuery("select v from Visite v where v.id = ?1", Visite.class)
////				.setParameter(1, id)
//				.createQuery("select v from Visite v where v.id = :id", Visite.class)
//				.setParameter("id", id)
//				.getSingleResult();
	}

	@Override
	public List<Visite> selectAll() {
		return this.em
				.createQuery("select v from Visite v", Visite.class)
				.getResultList();
	}

}