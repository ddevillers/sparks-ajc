package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestHibernate {
	public static void main(String[] args) throws ClassNotFoundException {
//		Class.forName("com.mysql.jdbc.Driver");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HopitalUnit");
		
		emf.close();
	}
}
