package net.diegoqueres.playlistportemperatura.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Country;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.entities.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.openweather.OpenWeatherIntegration;
import net.diegoqueres.playlistportemperatura.integrations.spotify.SpotifyIntegration;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.SpotifyInput;
import net.diegoqueres.playlistportemperatura.repositories.RecommendationRepository;
import net.diegoqueres.playlistportemperatura.services.CityService;
import net.diegoqueres.playlistportemperatura.services.CountryService;
import net.diegoqueres.playlistportemperatura.services.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceImpl.class);

	private static final int TEMPERATURE_HIGH = 25;
	private static final int TEMPERATURE_LOW = 10;

	@Autowired
	private RecommendationRepository repository;

	@Autowired
	private OpenWeatherIntegration weatherIntegration;

	@Autowired
	private SpotifyIntegration spotifyIntegration;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@Override
	public Recommendation requestRecommendation(User user, City city) {
		LOG.info("Realizando integrações para fazer recomendação de playlist para a cidade: {}", city);
		var weather = weatherIntegration.integrate(city);
		var recommendation = new Recommendation();

		Optional.ofNullable(weather.getCity()).ifPresent((c) -> {
			var countryObj = countryService.findById(c.getCountry().getId())
					.orElse(countryService.insert(c.getCountry()));
			var cityObj = cityService.findById(c.getId()).orElse(cityService.insert(c));
			recommendation.setCity(cityObj);
		});

		recommendation.setTemperature(weather.getTemperature());
		recommendation.setGenre(getGenreByTemperature(weather.getTemperature()));
		recommendation.setUser(user);

		var input = new SpotifyInput(recommendation.getGenre(), recommendation.getCountry());
		recommendation.setPlaylist(spotifyIntegration.integrate(input));

		var insertedRecommendation = insert(recommendation);
		if (insertedRecommendation.getCity() == null) {
			insertedRecommendation.setCity(city); // respostas com lat/lng (mesmo se cidade não existir)
		}

		return insertedRecommendation;

	}

	public Genre getGenreByTemperature(Float temperature) {
		int temperatureIntVal = Math.round(temperature);
		if (temperatureIntVal < TEMPERATURE_LOW) {
			return Genre.CLASSICAL;
		} else if (temperatureIntVal > TEMPERATURE_HIGH) {
			return Genre.POP;
		} else {
			return Genre.ROCK;
		}

	}

	@Override
	public Recommendation insert(Recommendation obj) {
		LOG.info("Gravando a recomendação de playlist: {}", obj);
		return repository.save(obj);
	}

}