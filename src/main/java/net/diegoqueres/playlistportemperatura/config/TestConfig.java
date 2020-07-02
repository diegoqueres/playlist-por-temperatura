package net.diegoqueres.playlistportemperatura.config;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Country;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.entities.enums.Role;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.Playlist;
import net.diegoqueres.playlistportemperatura.services.CityService;
import net.diegoqueres.playlistportemperatura.services.CountryService;
import net.diegoqueres.playlistportemperatura.services.RecommendationService;
import net.diegoqueres.playlistportemperatura.services.UserService;

/**
 * Configura uma massa de dados pré-existentes para os testes.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RecommendationService recommendationService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@Override
	public void run(String... args) throws Exception {
		final int MAX = 20;

		generateNewUser(Role.ADMIN);
		generateNewUser(Role.USER, "email.duplicado@gmail.com");
		for (int i = 0; i < (MAX - 2); i++)
			generateNewUser(Role.USER);

		generateNewLocations();

		for (int i = 0; i < MAX; i++) {
			var isAddCity = (i < (MAX - 5));
			userService.findAll().forEach((user) -> generateNewRecommendation(user, isAddCity));
		}

	}

	private void generateNewLocations() {
		var country1 = countryService.insert(new Country(1, "BR"));
		var country2 = countryService.insert(new Country(2, "CL"));
		var country3 = countryService.insert(new Country(3, "US"));
		var country4 = countryService.insert(new Country(4, "FR"));

		var city1 = new City(1L, "São Paulo", country1);
		city1.setLatitude(-23.55f);
		city1.setLongitude(-46.64f);
		cityService.insert(city1);
		
		cityService.insert(new City(2L, "Santiago", country2));
		cityService.insert(new City(3L, "New York", country3));
		cityService.insert(new City(4L, "Paris", country4));

	}

	private Recommendation generateNewRecommendation(User user, boolean isAddCity) {
		var recommendation = new Recommendation();
		recommendation.setTemperature(generatingRandomFloat(-20f, 42f));
		recommendation.setPlaylist(new Playlist());
		recommendation.setGenre(recommendationService.getGenreByTemperature(recommendation.getTemperature()));

		if (isAddCity) {
			Random rand = new Random();
			var cities = cityService.findAll();
			var city = cities.get(rand.nextInt(cities.size()));
			recommendation.setCity(city);
		}

		recommendation.setUser(user);

		return recommendationService.insert(recommendation);

	}

	private float generatingRandomFloat(float min, float max) {
		var generatedFloat = min + new Random().nextFloat() * (max - min);
		return generatedFloat;
	}

	private User generateNewUser(Role role, String... params) {
		var user = new User();
		user.setName(RandomStringUtils.randomAlphabetic(30));
		var email = RandomStringUtils.randomAlphabetic(10) + "@user.com.br";
		if (params.length > 0) {
			email = params[0];
		}
		user.setEmail(email);
		user.setPassword(RandomStringUtils.random(12, Boolean.TRUE, Boolean.TRUE));
		user.setRole(role);
		return userService.signUp(user);
	}

}
