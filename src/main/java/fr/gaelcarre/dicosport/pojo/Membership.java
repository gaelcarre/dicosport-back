package fr.gaelcarre.dicosport.pojo;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "MEMBER_OF")
public class Membership {
	@StartNode
	Sport sport;
	@EndNode
	Category category;

}
