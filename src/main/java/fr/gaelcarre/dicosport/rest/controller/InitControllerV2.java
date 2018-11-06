package fr.gaelcarre.dicosport.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.gaelcarre.dicosport.pojo.Category;
import fr.gaelcarre.dicosport.pojo.Membership;
import fr.gaelcarre.dicosport.pojo.News;
import fr.gaelcarre.dicosport.pojo.Sport;
import fr.gaelcarre.dicosport.pojo.SubCategory;
import fr.gaelcarre.dicosport.pojo.SubSport;
import fr.gaelcarre.dicosport.repository.CategoryRepository;
import fr.gaelcarre.dicosport.repository.DataBaseRepository;
import fr.gaelcarre.dicosport.repository.MembershipRepository;
import fr.gaelcarre.dicosport.repository.NewsRepository;
import fr.gaelcarre.dicosport.repository.SportRepository;
import fr.gaelcarre.dicosport.repository.SubCategoryRepository;
import fr.gaelcarre.dicosport.repository.SubSportRepository;
import fr.gaelcarre.dicosport.util.CSVEntity;
import fr.gaelcarre.dicosport.util.CSVReader;
import fr.gaelcarre.dicosport.util.CSVResult;

@Component
public class InitControllerV2 {

	private Logger logger = LoggerFactory.getLogger(InitControllerV2.class);

	private String url = "csv/v2";

	@Autowired
	private SportRepository sportRepository;
	@Autowired
	private DataBaseRepository databaseRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private MembershipRepository membershipRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private SubSportRepository subsportRepository;

	private Map<Integer, Sport> sports;
	private Map<Integer, Category> categories;
	private List<Membership> members = new ArrayList<>();
	private List<SubCategory> subcategories = new ArrayList<>();
	private List<SubSport> subsports = new ArrayList<>();

	@Transactional
	public void init() {

		this.logger.debug("Init dataset");

		purge();

		preparerSports();
		preparerCategories();

		Sport s = this.sports.get(554);
		// this.logger.debug(s.toString());

		createMemberOf();
		createSubCategories();
		createSubSport();

		this.sportRepository.save(this.sports.values(), Integer.MAX_VALUE);
		this.categoryRepository.save(this.categories.values(), Integer.MAX_VALUE);
		this.membershipRepository.save(this.members, Integer.MAX_VALUE);
		this.subCategoryRepository.save(this.subcategories, Integer.MAX_VALUE);
		this.subsportRepository.save(this.subsports, Integer.MAX_VALUE);

		News n1 = new News(new Date(), "POC", "Sport Encyclopedia is in a POC's state with SDN");
		News n2 = new News(new Date(), "Launching", "We keep you in touch");
		this.newsRepository.save(n1);
		this.newsRepository.save(n2);

	}

	private void createSubSport() {

		CSVResult resultcsv = CSVReader.read(this.url + "/subsports.csv");

		for (CSVEntity entry : resultcsv.getEntities()) {
			Integer childId = Integer.parseInt(entry.getKey());
			Integer parentId = Integer.parseInt(entry.getValue());

			Sport child = this.sports.get(childId);
			Sport parent = this.sports.get(parentId);

			if (child != null && parent != null) {
				SubSport s = new SubSport();
				s.setStart(child);
				s.setEnd(parent);
				this.subsports.add(s);
			}
		}
	}

	private void createMemberOf() {

		CSVResult resultcsv = CSVReader.read(this.url + "/member.csv");

		for (CSVEntity entry : resultcsv.getEntities()) {
			Integer sportId = Integer.parseInt(entry.getKey());
			Integer categId = Integer.parseInt(entry.getValue());

			Sport sport = this.sports.get(sportId);
			Category categ = this.categories.get(categId);

			if (sport != null && categ != null) {

				this.logger.info("Creating " + sport.getName() + " member of " + categ.getName());

				Membership m = new Membership();
				m.setCategory(categ);
				m.setSport(sport);
				this.members.add(m);

			}

		}

	}

	private void createSubCategories() {
		CSVResult resultcsv = CSVReader.read(this.url + "/subcategory.csv");

		for (CSVEntity entry : resultcsv.getEntities()) {
			Integer childId = Integer.parseInt(entry.getKey());
			Integer parentId = Integer.parseInt(entry.getValue());

			Category cchild = this.categories.get(childId);
			Category pchild = this.categories.get(parentId);

			if (cchild != null && pchild != null) {
				this.logger.info("Creating " + cchild.getName() + " subcategory of " + pchild.getName());

				SubCategory s = new SubCategory();
				s.setStart(cchild);
				s.setEnd(pchild);
				this.subcategories.add(s);

			}
		}
	}

	private void preparerCategories() {
		this.categories = new HashMap<>();
		CSVResult resultcsv = CSVReader.read(this.url + "/categories.csv");

		for (CSVEntity entry : resultcsv.getEntities()) {
			this.categories.put(Integer.parseInt(entry.getValue()), new Category(entry.getKey(), randomColor()));
			this.logger.info(entry.getKey());
		}

		createSubCategories();

	}

	private void preparerSports() {
		this.sports = new HashMap<>();
		CSVResult resultcsv = CSVReader.read(this.url + "/sports.csv");

		for (CSVEntity entry : resultcsv.getEntities()) {
			if (Integer.parseInt(entry.getValue()) == 556 || Integer.parseInt(entry.getValue()) == 554
					|| "Basque pelota".equals(entry.getKey()))
				this.logger.debug("XARE");
			this.sports.put(Integer.parseInt(entry.getValue()), new Sport(entry.getKey()));
			this.logger.info(entry.getKey());
		}

	}

	private String randomColor() {
		// create random object - reuse this as often as possible
		Random random = new Random();

		// create a big random number - maximum is ffffff (hex) = 16777215 (dez)
		int nextInt = random.nextInt(256 * 256 * 256);

		// format it as hexadecimal string (with hashtag and leading zeros)
		String colorCode = String.format("#%06x", nextInt);

		return colorCode;
	}

	@Transactional
	private void purge() {
		this.databaseRepository.purge();
	}

}
