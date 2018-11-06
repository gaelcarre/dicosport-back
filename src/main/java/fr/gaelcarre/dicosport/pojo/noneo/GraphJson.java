package fr.gaelcarre.dicosport.pojo.noneo;

import java.util.List;

public class GraphJson {

	private String comment;
	private List<Node> nodes;
	private List<Edge> edges;

	/**
	 * @param comment
	 * @param nodes
	 * @param edges
	 */
	public GraphJson(String comment, List<Node> nodes, List<Edge> edges) {
		super();
		this.comment = comment;
		this.nodes = nodes;
		this.edges = edges;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the nodes
	 */
	public List<Node> getNodes() {
		return this.nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return this.edges;
	}

	/**
	 * @param edges
	 *            the edges to set
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

}
