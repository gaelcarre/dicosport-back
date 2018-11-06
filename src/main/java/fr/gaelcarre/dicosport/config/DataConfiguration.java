package fr.gaelcarre.dicosport.config;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.LoadStrategy;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "fr.gaelcarre.dicosport.repository")
@EnableTransactionManagement
public class DataConfiguration {

	@Bean
	public SessionFactory sessionFactory() {
		// with domain entity base package(s)
		SessionFactory sf = new SessionFactory(configuration(), "fr.gaelcarre.dicosport.pojo");
		sf.setLoadStrategy(LoadStrategy.SCHEMA_LOAD_STRATEGY);
		return sf;
	}

	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {
		ConfigurationSource properties = new ClasspathConfigurationSource("db.properties");
		return new org.neo4j.ogm.config.Configuration.Builder(properties).build();
	}

	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}
}
