package config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration //Ici SPRING SAIT qu'il doit manager cette classe
@EnableTransactionManagement // ACTIVE L'ANNOTATION @Transactional
@EnableJpaRepositories("dao") // ACTIVE LES REPOSITORES DATA-JPA
public class JpaConfig {
	// BEAN DE CONFIGURATION POUR L'ACCES A LA BASE DE DONNEES
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		// On y affecte les propriétés
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/hopital");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setMaxTotal(10);

		return dataSource; // On donne cette DataSource à Spring
	}

	// BEAN ENTITYMANAGERFACTORY
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

		// On y affecte les propriétés
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("model");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(this.hibernateProperties());

		return emf; // On donne cette emf à Spring
	}

	public Properties hibernateProperties() {
		Properties properties = new Properties();

		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "false");

		return properties;
	}
	
	
	//BEAN DE GESTIONNAIRE DE TRANSACTIONS
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		
		//On y associe les propriétés
		transactionManager.setEntityManagerFactory(emf);
		
		return transactionManager; //On donne ce gestionnaire à Spring
		
		
		//FONCTIONNERA EGALEMENT
//		return new JpaTransactionManager(emf);
	}
}