package fr.gaelcarre.dicosport.rest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import fr.gaelcarre.dicosport.pojo.Category;
import fr.gaelcarre.dicosport.pojo.Sport;
import fr.gaelcarre.dicosport.repository.SportRepository;

@RestController
@RequestMapping
@Service
public class SportService {

	private static final Logger logger = LoggerFactory.getLogger(SportService.class);

	@Autowired
	private SportRepository sportRepository;

	@GetMapping("/public/sports")
	@Transactional
	public ResponseEntity<List<Sport>> getAllSports() {
		List<Sport> sports = Lists.newArrayList(this.sportRepository.findAll(2));
		sports.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		return ResponseEntity.ok(sports);
	}

	@GetMapping("/public/sports/{id}")
	@Transactional
	public ResponseEntity<Optional<Sport>> getSport(@PathVariable Long id) {
		Optional<Sport> s = this.sportRepository.findById(id);
		logger.debug(s.toString());

		if (s.isPresent())
			return ResponseEntity.ok(s);
		else
			return ResponseEntity.notFound().build();

	}

	@GetMapping("/public/sports/{id}/categories")
	@Transactional
	public ResponseEntity<List<Category>> getCategoriesForSport(@PathVariable Long id) {
		List<Category> categories = this.sportRepository.getCategories(id);

		if (categories.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(categories);

	}

	@PutMapping("/sports/{id}")
	@Transactional
	public ResponseEntity<Sport> putSport(@PathVariable Long id, @RequestBody Sport sport) {
		if (sport == null)
			return ResponseEntity.badRequest().build();
		else {
			sport.checkIntegrity();
			this.sportRepository.save(sport);
			return ResponseEntity.ok(sport);
		}
	}

	@PostMapping("/sports")
	@Transactional
	public ResponseEntity<Sport> postSport(@RequestBody Sport sport) {
		if (sport == null)
			return ResponseEntity.badRequest().build();
		else {
			sport.checkIntegrity();
			this.sportRepository.save(sport);
			return ResponseEntity.ok(sport);
		}
	}

}
