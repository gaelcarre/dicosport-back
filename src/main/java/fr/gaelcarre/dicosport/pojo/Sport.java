package fr.gaelcarre.dicosport.pojo;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Sport {
	public Sport(Long id, String name, String descrition) {
		super();
		this.id = id;
		this.name = name;
		this.descrition = descrition;
	}

	public Sport(String name) {
		this.name = name;
	}

	private @Id @GeneratedValue Long id;
	private String name;
	private String descrition;
	@Relationship(type = "MEMBER_OF")
	private Set<Category> categories;
	@Relationship(type = "REGULATED_BY")
	private Set<Rule> rules;

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

	public String getDescrition() {
		return this.descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public Set<Category> getCategories() {

		if (this.categories == null)
			this.categories = new HashSet<>();

		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Rule> getRules() {
		return this.rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		return "Sport " + this.name;
	}

}
