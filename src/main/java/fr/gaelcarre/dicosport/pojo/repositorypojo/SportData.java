package fr.gaelcarre.dicosport.pojo.repositorypojo;

import org.springframework.data.neo4j.annotation.QueryResult;

import fr.gaelcarre.dicosport.pojo.Category;
import fr.gaelcarre.dicosport.pojo.Sport;

@QueryResult
public class SportData {

	private Sport sport;
	private Category category;

	public SportData() {

	}

	/**
	 * @param sport
	 * @param category
	 */
	public SportData(Sport sport, Category category) {
		super();
		this.sport = sport;
		this.category = category;
	}

	/**
	 * @return the sport
	 */
	public Sport getSport() {
		return this.sport;
	}

	/**
	 * @param sport
	 *            the sport to set
	 */
	public void setSport(Sport sport) {
		this.sport = sport;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

}
