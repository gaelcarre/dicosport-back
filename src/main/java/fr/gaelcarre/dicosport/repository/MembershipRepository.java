package fr.gaelcarre.dicosport.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import fr.gaelcarre.dicosport.pojo.Membership;

public interface MembershipRepository extends Neo4jRepository<Membership, Long> {

}
