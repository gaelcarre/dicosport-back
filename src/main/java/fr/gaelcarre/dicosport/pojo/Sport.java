package fr.gaelcarre.dicosport.pojo;

import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.id.InternalIdStrategy;
import org.springframework.lang.Nullable;

@NodeEntity(label = "sport")
public class Sport {

	private @Id @GeneratedValue(strategy = InternalIdStrategy.class) Long id;
	private String name;
	private String federation;
	@Nullable
	private String description;
	@Nullable
	private String image;
	@Nullable
	@Relationship(type = "MEMBER_OF")
	private Set<Membership> categories;
	@Nullable
	@Relationship(type = "SPORT_UNDER")
	private Set<SubSport> subSports;

	public Sport(Long id, String name, String description, String image, String federation) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.federation = federation;
	}

	public Sport(String name) {
		this.name = name;
	}

	public Sport() {
		super();
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Membership> getCategories() {

		// if (this.categories == null)
		// this.categories = new HashSet<>();

		return this.categories;
	}

	public void setCategories(Set<Membership> categories) {
		this.categories = categories;
	}

	/**
	 * @return the federation
	 */
	public String getFederation() {
		return this.federation;
	}

	/**
	 * @param federation
	 *            the federation to set
	 */
	public void setFederation(String federation) {
		this.federation = federation;
	}

	/**
	 * @return the subSports
	 */
	public Set<SubSport> getSubSports() {
		return this.subSports;
	}

	/**
	 * @param subSports
	 *            the subSports to set
	 */
	public void setSubSports(Set<SubSport> subSports) {
		this.subSports = subSports;
	}

	@Override
	public String toString() {
		return "Sport " + this.name;
	}

	public void checkIntegrity() {
		for (Membership m : getCategories())
			if (m.getSport() == null)
				m.setSport(this);

	}

}
