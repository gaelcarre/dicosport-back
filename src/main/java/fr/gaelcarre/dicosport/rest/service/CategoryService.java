package fr.gaelcarre.dicosport.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gaelcarre.dicosport.pojo.Category;
import fr.gaelcarre.dicosport.repository.CategoryRepository;

@RestController
@RequestMapping
@Service
public class CategoryService {

	private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/public/categories")
	@Transactional
	public ResponseEntity<Iterable<Category>> getAllCategories() {
		return ResponseEntity.ok(this.categoryRepository.findAll());
	}

}
