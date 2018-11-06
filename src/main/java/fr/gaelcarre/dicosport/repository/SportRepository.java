package fr.gaelcarre.dicosport.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.gaelcarre.dicosport.pojo.Category;
import fr.gaelcarre.dicosport.pojo.Sport;

@Repository
public interface SportRepository extends Neo4jRepository<Sport, Long> {

	public Sport getById(Long id);

	@Query("match (s:sport)-[:SPORT_UNDER*0..5]->()-[:MEMBER_OF*0..5]->(c:category)-[:CATEGORIZED_UNDER*0..5]->(cu:category) where id(s)={sportId} return c,cu")
	public List<Category> getCategories(@Param("sportId") Long id);

}
