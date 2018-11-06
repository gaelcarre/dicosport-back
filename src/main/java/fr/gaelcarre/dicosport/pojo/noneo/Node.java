package fr.gaelcarre.dicosport.pojo.noneo;

public class Node {

	private String caption;
	private String type;
	private Long id;
	private String color;
	private Integer cluster;

	/**
	 * @param caption
	 * @param type
	 * @param id
	 */
	public Node(String caption, String type, Long id, String color, Integer cluster) {
		super();
		this.caption = caption;
		this.type = type;
		this.id = id;
		this.color = color;
		this.cluster = cluster;
	}

	/**
	 * @return the cluster
	 */
	public Integer getCluster() {
		return this.cluster;
	}

	/**
	 * @param cluster
	 *            the cluster to set
	 */
	public void setCluster(Integer cluster) {
		this.cluster = cluster;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return this.caption;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

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

}
