package fr.gaelcarre.dicosport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import fr.gaelcarre.dicosport.rest.controller.InitController;

@SpringBootApplication(exclude = { Neo4jDataAutoConfiguration.class })
@EnableNeo4jRepositories("fr.gaelcarre.dicosport.repository")
@EntityScan(basePackages = "fr.gaelcarre.dicosport.pojo")
@ComponentScan("fr.gaelcarre.*")
@EnableConfigurationProperties
public class Application {

	@Autowired
	private static InitController initController;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
