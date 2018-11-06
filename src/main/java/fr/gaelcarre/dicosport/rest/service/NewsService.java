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

import fr.gaelcarre.dicosport.pojo.News;
import fr.gaelcarre.dicosport.repository.NewsRepository;

@RestController
@RequestMapping
@Service
public class NewsService {

	private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

	@Autowired
	private NewsRepository newsRepository;

	@GetMapping("/public/news")
	@Transactional
	public ResponseEntity<Iterable<News>> getAllNews() {
		return ResponseEntity.ok(this.newsRepository.findAll(1));
	}

}
