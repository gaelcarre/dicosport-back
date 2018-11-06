package fr.gaelcarre.dicosport.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@GetMapping("/admin")
	public ResponseEntity<Boolean> connect() {
		return ResponseEntity.ok(true);
	}

}
