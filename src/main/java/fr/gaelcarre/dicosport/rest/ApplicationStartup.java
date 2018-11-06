package fr.gaelcarre.dicosport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import fr.gaelcarre.dicosport.rest.controller.InitControllerV2;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private InitControllerV2 initcontroller;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		// this.initcontroller.init();

	}

}
