package fr.gaelcarre.dicosport.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import fr.gaelcarre.dicosport.pojo.Sport;

@Repository
public interface SportRepository extends Neo4jRepository<Sport, Long> {

}
