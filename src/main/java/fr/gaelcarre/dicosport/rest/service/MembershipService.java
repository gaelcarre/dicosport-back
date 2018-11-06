package fr.gaelcarre.dicosport.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gaelcarre.dicosport.pojo.Membership;
import fr.gaelcarre.dicosport.repository.MembershipRepository;

@RestController
@RequestMapping
@Service
public class MembershipService {

	private static final Logger log = LoggerFactory.getLogger(MembershipService.class);

	@Autowired
	private MembershipRepository membershipRepository;

	@PutMapping("/memberships/{id}")
	@Transactional
	public void putMembership(@PathVariable Long id, @RequestBody Membership membership) {
		if (membership != null && membership.getId().equals(id))
			this.membershipRepository.save(membership);
	}

	@PostMapping("/memberships")
	@Transactional
	public void postMembership(@RequestBody Membership membership) {
		if (membership != null)
			this.membershipRepository.save(membership);
	}

	@DeleteMapping("/memberships/{id}")
	@Transactional
	public void deleteMembership(@PathVariable Long id) {
		log.debug("--------------- delete relationship: " + id);
		this.membershipRepository.deleteById(id);
	}

}
