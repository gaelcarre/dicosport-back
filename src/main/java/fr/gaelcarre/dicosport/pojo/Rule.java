package fr.gaelcarre.dicosport.pojo;

import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Rule {
	private @Id @GeneratedValue Long id;
	private String name;
	private String description;
	private String schemaImage;
	@Relationship(type = "REGULATED_BY", direction = Relationship.INCOMING)
	private Set<Sport> sports;
}
