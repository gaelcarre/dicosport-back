package fr.gaelcarre.dicosport.pojo.noneo;

public class Edge {

	private Long source;
	private Long target;
	private String caption;
	private String type;

	/**
	 * @param source
	 * @param target
	 * @param caption
	 */
	public Edge(Long source, Long target, String caption, String type) {
		super();
		this.source = source;
		this.target = target;
		this.caption = caption;
		this.type = type;
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
	 * @return the source
	 */
	public Long getSource() {
		return this.source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Long source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public Long getTarget() {
		return this.target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(Long target) {
		this.target = target;
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

}
