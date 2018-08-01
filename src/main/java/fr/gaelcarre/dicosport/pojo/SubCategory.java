package fr.gaelcarre.dicosport.pojo;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "CATEGORIZED_UNDER")
public class SubCategory {
	@StartNode
	Category start;
	@EndNode
	Category end;
}
