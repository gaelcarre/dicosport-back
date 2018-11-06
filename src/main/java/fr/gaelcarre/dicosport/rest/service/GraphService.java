package fr.gaelcarre.dicosport.rest.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import fr.gaelcarre.dicosport.pojo.Category;
import fr.gaelcarre.dicosport.pojo.Membership;
import fr.gaelcarre.dicosport.pojo.Sport;
import fr.gaelcarre.dicosport.pojo.SubCategory;
import fr.gaelcarre.dicosport.pojo.SubSport;
import fr.gaelcarre.dicosport.pojo.noneo.Edge;
import fr.gaelcarre.dicosport.pojo.noneo.GraphJson;
import fr.gaelcarre.dicosport.pojo.noneo.Node;
import fr.gaelcarre.dicosport.pojo.utils.ColorUtils;
import fr.gaelcarre.dicosport.repository.CategoryRepository;
import fr.gaelcarre.dicosport.repository.MembershipRepository;
import fr.gaelcarre.dicosport.repository.SportRepository;
import fr.gaelcarre.dicosport.repository.SubCategoryRepository;
import fr.gaelcarre.dicosport.repository.SubSportRepository;

@RequestMapping
@RestController
@Service
public class GraphService {

	private static final Logger log = LoggerFactory.getLogger(GraphService.class);

	@Autowired
	private SportRepository sportRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MembershipRepository membershipRepository;

	@Autowired
	private SubCategoryRepository subcategoryRepository;

	@Autowired
	private SubSportRepository subSportRepository;

	@GetMapping("/public/graph/sports")
	@Transactional
	public ResponseEntity<GraphJson> sports() {

		GraphJson graph = new GraphJson("sports", new ArrayList<>(), new ArrayList<>());

		processSports(graph);
		processSubSports(graph);

		return ResponseEntity.ok(graph);

	}

	@GetMapping("/public/graph/categories")
	@Transactional
	public ResponseEntity<GraphJson> categories() {

		GraphJson graph = new GraphJson("categories", new ArrayList<>(), new ArrayList<>());

		processCategories(graph);
		processSubCategories(graph);

		return ResponseEntity.ok(graph);

	}

	@GetMapping("/public/graph/memberof")
	@Transactional
	public ResponseEntity<GraphJson> memberof() {

		GraphJson graph = new GraphJson("memberof", new ArrayList<>(), new ArrayList<>());

		processSports(graph);
		processCategories(graph);
		processMembership(graph);

		return ResponseEntity.ok(graph);

	}

	@GetMapping("/public/graph")
	@Transactional
	public ResponseEntity<GraphJson> graph() {

		GraphJson graph = new GraphJson("memberof", new ArrayList<>(), new ArrayList<>());

		processSports(graph);
		processCategories(graph);
		processMembership(graph);
		processSubCategories(graph);
		processSubSports(graph);

		return ResponseEntity.ok(graph);

	}

	// @GetMapping("/public/graph/clustermemberof")
	// @Transactional
	// public ResponseEntity<List<SportData>> cluster() {
	// List<SportData> datas = this.sportRepository.memberof();
	// return ResponseEntity.ok(datas);
	// }

	private void processSubCategories(GraphJson graph) {
		for (SubCategory s : this.subcategoryRepository.findAll())
			graph.getEdges()
					.add(new Edge(s.getStart().getId(), s.getEnd().getId(), "subcategory", "CATEGORIZED_UNDER"));
	}

	private void processSubSports(GraphJson graph) {
		for (SubSport s : this.subSportRepository.findAll())
			graph.getEdges().add(new Edge(s.getStart().getId(), s.getEnd().getId(), "subsport", "SPORT_UNDER"));
	}

	private void processMembership(GraphJson graph) {
		for (Membership m : this.membershipRepository.findAll())
			graph.getEdges().add(new Edge(m.getSport().getId(), m.getCategory().getId(), "memberof", "MEMBER_OF"));
	}

	private void processCategories(GraphJson graph) {
		for (Category c : this.categoryRepository.findAll())
			graph.getNodes().add(new Node(c.getName(), "category", c.getId(), c.getColor(), null));
	}

	private void processSports(GraphJson graph) {
		List<Sport> sports = Lists.newArrayList(this.sportRepository.findAll(2));
		sports.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		for (Sport s : sports) {
			Color color = null;
			if (s.getCategories() != null)
				for (Membership m : s.getCategories())
					if (color == null)
						color = new Color(m.getCategory().getColorRed(), m.getCategory().getColorGreen(),
								m.getCategory().getColorBlue());
					else
						color = new Color((color.getRed() + m.getCategory().getColorRed()) / 2,
								(color.getBlue() + m.getCategory().getColorBlue()) / 2,
								(color.getGreen() + m.getCategory().getColorGreen()) / 2);
			if (color == null)
				color = Color.WHITE;

			graph.getNodes().add(new Node(s.getName(), "sport", s.getId(), "#" + ColorUtils.getHexa(color), null));
		}

	}

}
