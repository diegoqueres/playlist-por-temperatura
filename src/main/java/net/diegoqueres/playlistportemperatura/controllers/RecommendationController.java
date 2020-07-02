package net.diegoqueres.playlistportemperatura.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.services.RecommendationService;
import net.diegoqueres.playlistportemperatura.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/recommendations")
public class RecommendationController {
	private static final Logger LOG = LoggerFactory.getLogger(RecommendationController.class);

	@Autowired
	private RecommendationService service;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/city/{cityName}")
	public ResponseEntity<Recommendation> requestRecommendationByCity(@PathVariable String cityName) {
		var fakeUser = userService.findAll().get(0); // temporário até incluir camada de segurança
		var city = new City(cityName);
		var recommendation = service.requestRecommendation(fakeUser, city);

		return ResponseEntity.ok().body(recommendation);

	}

	@GetMapping(value = "/coords")
	public ResponseEntity<Recommendation> requestRecommendationByLocation(
			@RequestParam(value = "lat", required = true) Float lat,
			@RequestParam(value = "lng", required = true) Float lng) {
		var fakeUser = userService.findAll().get(0); // temporário até incluir camada de segurança
		var city = new City(lat, lng);
		var recommendation = service.requestRecommendation(fakeUser, city);

		return ResponseEntity.ok().body(recommendation);

	}

}
