package fr.gaelcarre.dicosport.pojo;

import java.util.Date;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.id.InternalIdStrategy;

@NodeEntity(label = "news")
public class News {

	public News() {

	}

	/**
	 * @param id
	 * @param date
	 * @param title
	 * @param content
	 */
	public News(Long id, Date date, String title, String content) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.content = content;
	}

	/**
	 * @param date
	 * @param title
	 * @param content
	 */
	public News(Date date, String title, String content) {
		super();
		this.date = date;
		this.title = title;
		this.content = content;
	}

	private @Id @GeneratedValue(strategy = InternalIdStrategy.class) Long id;
	private Date date;
	private String title;
	private String content;

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
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
