package fr.gaelcarre.dicosport.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import fr.gaelcarre.dicosport.pojo.Category;

@Repository
public interface CategoryRepository extends Neo4jRepository<Category, Long> {

}
