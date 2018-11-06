package fr.gaelcarre.dicosport.pojo;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "REGULATED_BY")
public class Regulation {
	@Id
	@GeneratedValue
	private Long id;
	@StartNode
	Sport sport;
	@EndNode
	Rule Rule;
	private String value;
}
