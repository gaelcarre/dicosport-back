package fr.gaelcarre.dicosport.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import fr.gaelcarre.dicosport.pojo.News;

@Repository
public interface NewsRepository extends Neo4jRepository<News, Long> {

}
