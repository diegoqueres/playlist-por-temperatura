package net.diegoqueres.playlistportemperatura.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.services.RecommendationService;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;

/**
 * Testes para o serviço de recomendação de playlist da aplicação.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 */
@SpringBootTest
@ActiveProfiles("test")
class RecommendationServiceImplTest {

	@Autowired
	private RecommendationService service;

	@Test
	public void testRequestRecommendationByCity() {
		User user = new User();
		City city = new City();
		city.setName("Campinas");

		Recommendation recommendation = service.requestRecommendation(user, city);

		Assertions.assertNotNull(recommendation.getMusics());
		Assertions.assertFalse(recommendation.getMusics().isEmpty());

	}

	@Test
	public void testRequestRecommendationByLatitudeLongitude() {
		User user = new User();
		City city = new City();
		city.setLatitude(-22.951542f);
		city.setLongitude(-47.0479287f);

		Recommendation recommendation = service.requestRecommendation(user, city);

		Assertions.assertNotNull(recommendation.getMusics());
		Assertions.assertFalse(recommendation.getMusics().isEmpty());

	}

	@Test
	public void testRequestRecommendationLocationNotFound() {
		User user = new User();
		City city = new City();
		city.setName("d343434adfdfdr434343cadabralslsl");

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.requestRecommendation(user, city);
		});

	}

	@Test
	public void testRequestRecommendationLocationWithInvalidGeographicCoordinates() {
		User user = new User();
		City city = new City();
		city.setLatitude(100f);
		city.setLongitude(-200f);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.requestRecommendation(user, city);
		});

	}

}