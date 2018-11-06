package fr.gaelcarre.dicosport.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import fr.gaelcarre.dicosport.pojo.SubSport;

public interface SubSportRepository extends Neo4jRepository<SubSport, Long> {

}
