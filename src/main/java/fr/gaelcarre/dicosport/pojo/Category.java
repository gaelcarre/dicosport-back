package fr.gaelcarre.dicosport.pojo;

import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Category {
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category(Long id, String name, Set<Sport> sports, Set<Category> subCategories, Category parent) {
		super();
		this.id = id;
		this.name = name;
		this.sports = sports;
		this.subCategories = subCategories;
		this.parent = parent;
	}

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	private @Id @GeneratedValue Long id;
	private String name;
	@Relationship(type = "MEMBER_OF", direction = Relationship.INCOMING)
	private Set<Sport> sports;
	@Relationship(type = "CATEGORIZED_UNDER")
	private Set<Category> subCategories;
	@Relationship(type = "CATEGORIZED_UNDER", direction = Relationship.INCOMING)
	private Category parent;

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

	public Set<Sport> getSports() {
		return this.sports;
	}

	public void setSports(Set<Sport> sports) {
		this.sports = sports;
	}

	public Set<Category> getSubCategories() {
		return this.subCategories;
	}

	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public Category getParent() {
		return this.parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Category " + this.name;
	}
}
