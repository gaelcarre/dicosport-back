package fr.gaelcarre.dicosport.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import fr.gaelcarre.dicosport.pojo.SubCategory;

public interface SubCategoryRepository extends Neo4jRepository<SubCategory, Long> {

}
