package fr.gaelcarre.dicosport.pojo;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@RelationshipEntity(type = "SPORT_UNDER")
public class SubSport {
	@Id
	@GeneratedValue
	private Long id;
	@StartNode
	@JsonIgnore
	Sport start;
	@EndNode
	Sport end;

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the start
	 */
	public Sport getStart() {
		return this.start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(Sport start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Sport getEnd() {
		return this.end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Sport end) {
		this.end = end;
	}

}
