package fr.gaelcarre.dicosport.pojo;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.id.InternalIdStrategy;
import org.springframework.lang.Nullable;

@NodeEntity(label = "category")
public class Category {

	private @Id @GeneratedValue(strategy = InternalIdStrategy.class) Long id;
	private String name;
	@Nullable
	private String color;
	@Nullable
	@Relationship(type = "MEMBER_OF")
	private Set<Membership> sports;
	@Nullable
	@Relationship(type = "CATEGORIZED_UNDER")
	private Set<SubCategory> subcategories;

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
	}

	public Category(String name, String color) {
		this.name = name;
		this.color = color;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Membership> getSports() {
		if (this.sports == null)
			this.sports = new HashSet<>();
		return this.sports;
	}

	public void setSports(Set<Membership> sports) {
		this.sports = sports;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the subcategories
	 */
	public Set<SubCategory> getSubcategories() {
		return this.subcategories;
	}

	/**
	 * @param subcategories
	 *            the subcategories to set
	 */
	public void setSubcategories(Set<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}

	@Override
	public String toString() {
		return "Category " + this.name;
	}

	public Integer getColorRed() {
		return Integer.parseInt(("" + this.color.charAt(1) + this.color.charAt(2)), 16);
	}

	public Integer getColorGreen() {
		return Integer.parseInt(("" + this.color.charAt(3) + this.color.charAt(4)), 16);
	}

	public Integer getColorBlue() {
		return Integer.parseInt(("" + this.color.charAt(5) + this.color.charAt(6)), 16);
	}
}
