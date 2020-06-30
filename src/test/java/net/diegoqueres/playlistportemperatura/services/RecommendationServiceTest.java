package net.diegoqueres.playlistportemperatura.services;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.Playlist;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;

/**
 * Testes para o serviço de recomendação de playlist da aplicação.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 */
@SpringBootTest
@ActiveProfiles("test")
public class RecommendationServiceTest {

	@Autowired
	private RecommendationService service;

	@Test
	public void testRequestRecommendationByCity() {
		User user = new User();
		City city = new City();
		city.setName("Campinas");

		Recommendation recommendation = service.requestRecommendation(user, city);
		Playlist playlist = recommendation.getPlaylist();

		//testa se a playlist retornada é válida
		assertNotNull(playlist);
		assertNotNull(playlist.getMusics());
		assertFalse(playlist.getMusics().isEmpty());
		
		//testa se recuperou corretamente dados de localidade válida
		assertNotNull(recommendation.getCity().getId());
		var country = recommendation.getCity().getCountry();
		assertNotNull(country.getId());
		assertNotNull(country.getCode());

	}

	@Test
	public void testRequestRecommendationByLatitudeLongitude() {
		User user = new User();
		City city = new City();
		city.setLatitude(-22.9519767f);
		city.setLongitude(-47.0418347f);

		Recommendation recommendation = service.requestRecommendation(user, city);
		Playlist playlist = recommendation.getPlaylist();

		assertNotNull(playlist);
		assertNotNull(playlist.getMusics());
		assertFalse(playlist.getMusics().isEmpty());

	}

	@Test
	public void testRequestRecommendationLocationNotFound() {
		User user = new User();
		City city = new City();
		city.setName("d343434adfdfdr434343cadabralslsl");

		assertThrows(ResourceNotFoundException.class, () -> {
			service.requestRecommendation(user, city);
		});

	}

	@Test
	public void testRequestRecommendationLocationWithInvalidGeographicCoordinates() {
		User user = new User();
		City city = new City();
		city.setLatitude(100f);
		city.setLongitude(-200f);

		assertThrows(IllegalArgumentException.class, () -> {
			service.requestRecommendation(user, city);
		});

	}

	/**
	 * Testa solicitação de recomendação se o usuário estiver em um local inóspito.
	 * Onde não há uma "cidade" associada à localização.
	 */
	@Test
	public void testRequestRecommendationInAnInhospitablePoint() {
		final int LAT = 0, LNG = 1;
		float[] SOUTH_PACIFIC_LOCATION = new float[] { -35.7542865f, -135.3123678f };

		User user = new User();
		City city = new City();
		city.setLatitude(SOUTH_PACIFIC_LOCATION[LAT]);
		city.setLongitude(SOUTH_PACIFIC_LOCATION[LNG]);

		Recommendation recommendation = service.requestRecommendation(user, city);
		Playlist playlist = recommendation.getPlaylist();

		assertNotNull(playlist);
		assertNotNull(playlist.getMusics());
		assertFalse(playlist.getMusics().isEmpty());
		
		//testa se não salvou dados de localidade inválida
		assertTrue(recommendation.getCity().getName().isEmpty());
		assertNull(recommendation.getCity().getCountry());

	}

	/**
	 * Testa se as temperaturas fornecidas retornam os gêneros corretos.
	 */
	@Test
	public void testRecommendationGenreByTemperature() {

		// Baixas temperaturas devem obter música clássica.
		assertEquals(service.getGenreByTemperature(0f), Genre.CLASSICAL);

		// Temperatura média deve obter rock.
		assertEquals(service.getGenreByTemperature(20f), Genre.ROCK);

		// Temperatura alta deve obter pop.
		assertEquals(service.getGenreByTemperature(35f), Genre.POP);

	}

}