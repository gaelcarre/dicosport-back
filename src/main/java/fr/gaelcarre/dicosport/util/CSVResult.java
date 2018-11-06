package fr.gaelcarre.dicosport.util;

import java.util.ArrayList;
import java.util.List;

public class CSVResult {

	private List<CSVEntity> entities = new ArrayList<>();

	public void put(String string, String string2) {
		this.entities.add(new CSVEntity(string, string2));
	}

	public List<CSVEntity> getEntities() {
		return this.entities;
	}

}
