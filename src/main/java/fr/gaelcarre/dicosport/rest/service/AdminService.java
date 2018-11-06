package fr.gaelcarre.dicosport.rest.service;

import org.neo4j.ogm.session.Neo4jException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gaelcarre.dicosport.repository.DataBaseRepository;

@RestController
@RequestMapping
@Service
public class AdminService {

	private static final Logger log = LoggerFactory.getLogger(AdminService.class);

	@Autowired
	private DataBaseRepository databaseRepository;

	@DeleteMapping("/admin/purge")
	@Transactional
	public ResponseEntity<Boolean> purge() {
		try {
			this.databaseRepository.purge();
		} catch (Neo4jException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(true);

	}
}
