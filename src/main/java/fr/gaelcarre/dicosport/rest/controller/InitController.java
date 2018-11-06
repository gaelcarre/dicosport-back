// package fr.gaelcarre.dicosport.rest.controller;
//
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Random;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;
//
// import fr.gaelcarre.dicosport.pojo.Category;
// import fr.gaelcarre.dicosport.pojo.Membership;
// import fr.gaelcarre.dicosport.pojo.News;
// import fr.gaelcarre.dicosport.pojo.Sport;
// import fr.gaelcarre.dicosport.pojo.SubCategory;
// import fr.gaelcarre.dicosport.repository.CategoryRepository;
// import fr.gaelcarre.dicosport.repository.DataBaseRepository;
// import fr.gaelcarre.dicosport.repository.MembershipRepository;
// import fr.gaelcarre.dicosport.repository.NewsRepository;
// import fr.gaelcarre.dicosport.repository.SportRepository;
// import fr.gaelcarre.dicosport.repository.SubCategoryRepository;
// import fr.gaelcarre.dicosport.util.CSVReader;
//
// @Component
// public class InitController {
//
// private Logger logger = LoggerFactory.getLogger(InitController.class);
//
// private String url = "csv/semi-final";
//
// @Autowired
// private SportRepository sportRepository;
// @Autowired
// private DataBaseRepository databaseRepository;
// @Autowired
// private CategoryRepository categoryRepository;
// @Autowired
// private NewsRepository newsRepository;
// @Autowired
// private MembershipRepository membershipRepository;
// @Autowired
// private SubCategoryRepository subCategoryRepository;
//
// private Map<Integer, Sport> sports;
// private Map<Integer, Category> categories;
// private List<Membership> members = new ArrayList<>();
// private List<SubCategory> subcategories = new ArrayList<>();
//
// @Transactional
// public void init() {
//
// this.logger.debug("Init dataset");
//
// purge();
//
// preparerSports();
// preparerCategories();
//
// createMemberOf();
// createSubCategories();
//
// this.sportRepository.save(this.sports.values(), Integer.MAX_VALUE);
// this.categoryRepository.save(this.categories.values(), Integer.MAX_VALUE);
// this.membershipRepository.save(this.members, Integer.MAX_VALUE);
// this.subCategoryRepository.save(this.subcategories, Integer.MAX_VALUE);
//
// News n1 = new News(new Date(), "POC", "Sport Encyclopedia is in a POC's state
// with SDN");
// News n2 = new News(new Date(), "Launching", "We keep you in touch");
// this.newsRepository.save(n1);
// this.newsRepository.save(n2);
//
// }
//
// private void createMemberOf() {
// // sports.get(1).getCategories().add(categories.get(1));
// // sports.get(2).getCategories().add(categories.get(1));
// // sports.get(2).getCategories().add(categories.get(13));
// // sports.get(3).getCategories().add(categories.get(1));
// // sports.get(3).getCategories().add(categories.get(13));
// // sports.get(4).getCategories().add(categories.get(1));
// // sports.get(4).getCategories().add(categories.get(13));
// // sports.get(5).getCategories().add(categories.get(2));
// // sports.get(5).getCategories().add(categories.get(12));
// // sports.get(6).getCategories().add(categories.get(2));
// // sports.get(6).getCategories().add(categories.get(12));
// // sports.get(7).getCategories().add(categories.get(1));
// // sports.get(7).getCategories().add(categories.get(2));
// // sports.get(7).getCategories().add(categories.get(11));
//
// HashMap<String, String> resultcsv = CSVReader.read(this.url + "/member.csv");
//
// for (Map.Entry<String, String> entry : resultcsv.entrySet()) {
// Integer sportId = Integer.parseInt(entry.getKey());
// Integer categId = Integer.parseInt(entry.getValue());
//
// Sport sport = this.sports.get(sportId);
// Category categ = this.categories.get(categId);
//
// if (sport != null && categ != null) {
//
// this.logger.info("Creating " + sport.getName() + " member of " +
// categ.getName());
//
// Membership m = new Membership();
// m.setCategory(categ);
// m.setSport(sport);
// this.members.add(m);
// // if (sport.getCategories() == null)
// // sport.setCategories(new HashSet<>());
// // sport.getCategories().add(m);
// //
// // if (categ.getSports() == null)
// // categ.setSports(new HashSet<>());
// // categ.getSports().add(m);
//
// }
//
// }
//
// }
//
// private void createSubCategories() {
// HashMap<String, String> resultcsv = CSVReader.read(this.url +
// "/subcategory.csv");
//
// for (Map.Entry<String, String> entry : resultcsv.entrySet()) {
// Integer childId = Integer.parseInt(entry.getKey());
// Integer parentId = Integer.parseInt(entry.getValue());
//
// Category cchild = this.categories.get(childId);
// Category pchild = this.categories.get(parentId);
//
// if (cchild != null && pchild != null) {
// this.logger.info("Creating " + cchild.getName() + " subcategory of " +
// pchild.getName());
//
// SubCategory s = new SubCategory();
// s.setStart(cchild);
// s.setEnd(pchild);
// this.subcategories.add(s);
//
// }
// }
// }
//
// private void preparerCategories() {
// this.categories = new HashMap<>();
// // categories.put(1, new Category("Sport Collectif"));
// // categories.put(2, new Category("Sport individuel"));
// // categories.put(3, new Category("Sport avec animaux"));
// // categories.put(4, new Category("Danse"));
// // categories.put(5, new Category("Athlétisme"));
// // categories.put(6, new Category("Gymnastique"));
// // categories.put(7, new Category("Handisport"));
// // categories.put(8, new Category("Raquette"));
// // categories.put(9, new Category("Sport d'hiver"));
// // categories.put(10, new Category("Sport mécanique"));
// // categories.put(11, new Category("Sport de combat"));
// // categories.put(12, new Category("Art martial"));
// // categories.put(13, new Category("Sport de balle et ballon"));
// //
// // // Parent
// // categories.get(12).setParent(categories.get(11));
// // categories.get(8).setParent(categories.get(13));
//
// HashMap<String, String> resultcsv = CSVReader.read(this.url +
// "/categories.csv");
//
// for (Map.Entry<String, String> entry : resultcsv.entrySet()) {
// this.categories.put(Integer.parseInt(entry.getValue()), new
// Category(entry.getKey(), randomColor()));
// this.logger.info(entry.getKey());
// }
//
// createSubCategories();
//
// }
//
// private void preparerSports() {
// this.sports = new HashMap<>();
// // sports.put(1, new Sport("Hockey sur glace"));
// // sports.put(2, new Sport("Football"));
// // sports.put(3, new Sport("Basketball"));
// // sports.put(4, new Sport("Handball"));
// // sports.put(5, new Sport("Judo"));
// // sports.put(6, new Sport("Karate"));
// // sports.put(7, new Sport("Escrime"));
//
// HashMap<String, String> resultcsv = CSVReader.read(this.url + "/sports.csv");
//
// for (Map.Entry<String, String> entry : resultcsv.entrySet()) {
// this.sports.put(Integer.parseInt(entry.getValue()), new
// Sport(entry.getKey()));
// this.logger.info(entry.getKey());
// }
//
// }
//
// private String randomColor() {
// // create random object - reuse this as often as possible
// Random random = new Random();
//
// // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
// int nextInt = random.nextInt(256 * 256 * 256);
//
// // format it as hexadecimal string (with hashtag and leading zeros)
// String colorCode = String.format("#%06x", nextInt);
//
// return colorCode;
// }
//
// @Transactional
// private void purge() {
// this.databaseRepository.purge();
// }
//
// }
