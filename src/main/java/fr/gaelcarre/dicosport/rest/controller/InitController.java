package fr.gaelcarre.dicosport.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.gaelcarre.dicosport.pojo.Category;
import fr.gaelcarre.dicosport.pojo.Sport;
import fr.gaelcarre.dicosport.repository.CategoryRepository;
import fr.gaelcarre.dicosport.repository.DataBaseRepository;
import fr.gaelcarre.dicosport.repository.SportRepository;

@Component
public class InitController {

	private Logger logger = LoggerFactory.getLogger(InitController.class);

	@Autowired
	private SportRepository sportRepository;
	@Autowired
	private DataBaseRepository databaseRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public void init() {

		this.logger.debug("Init dataset");

		purge();

		Map<Integer, Sport> sports = preparerSports();
		Map<Integer, Category> categories = preparerCategories();

		createMemberOf(sports, categories);

		this.sportRepository.save(sports.values(), Integer.MAX_VALUE);
		// this.categoryRepository.save(categories.values(), Integer.MAX_VALUE);
	}

	private void createMemberOf(Map<Integer, Sport> sports, Map<Integer, Category> categories) {
		sports.get(1).getCategories().add(categories.get(1));
		sports.get(2).getCategories().add(categories.get(1));
		sports.get(2).getCategories().add(categories.get(13));
		sports.get(3).getCategories().add(categories.get(1));
		sports.get(3).getCategories().add(categories.get(13));
		sports.get(4).getCategories().add(categories.get(1));
		sports.get(4).getCategories().add(categories.get(13));
		sports.get(5).getCategories().add(categories.get(2));
		sports.get(5).getCategories().add(categories.get(12));
		sports.get(6).getCategories().add(categories.get(2));
		sports.get(6).getCategories().add(categories.get(12));
		sports.get(7).getCategories().add(categories.get(1));
		sports.get(7).getCategories().add(categories.get(2));
		sports.get(7).getCategories().add(categories.get(11));
	}

	private Map<Integer, Category> preparerCategories() {
		Map<Integer, Category> categories = new HashMap<>();
		categories.put(1, new Category("Sport Collectif"));
		categories.put(2, new Category("Sport individuel"));
		categories.put(3, new Category("Sport avec animaux"));
		categories.put(4, new Category("Danse"));
		categories.put(5, new Category("Athlétisme"));
		categories.put(6, new Category("Gymnastique"));
		categories.put(7, new Category("Handisport"));
		categories.put(8, new Category("Raquette"));
		categories.put(9, new Category("Sport d'hiver"));
		categories.put(10, new Category("Sport mécanique"));
		categories.put(11, new Category("Sport de combat"));
		categories.put(12, new Category("Art martial"));
		categories.put(13, new Category("Sport de balle et ballon"));

		// Parent
		categories.get(12).setParent(categories.get(11));
		categories.get(8).setParent(categories.get(13));

		return categories;
	}

	private Map<Integer, Sport> preparerSports() {
		Map<Integer, Sport> sports = new HashMap<>();
		sports.put(1, new Sport("Hockey sur glace"));
		sports.put(2, new Sport("Football"));
		sports.put(3, new Sport("Basketball"));
		sports.put(4, new Sport("Handball"));
		sports.put(5, new Sport("Judo"));
		sports.put(6, new Sport("Karate"));
		sports.put(7, new Sport("Escrime"));

		return sports;
	}

	@Transactional
	private void purge() {
		this.databaseRepository.purge();
	}

}
