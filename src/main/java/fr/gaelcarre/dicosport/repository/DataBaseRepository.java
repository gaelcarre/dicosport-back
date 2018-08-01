package fr.gaelcarre.dicosport.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DataBaseRepository extends Neo4jRepository<Object, Long> {

	@Query("MATCH (a) WITH a LIMIT 10000 OPTIONAL MATCH (a)-[r]-() DELETE a,r RETURN COUNT(*)")
	public void purge();

}
