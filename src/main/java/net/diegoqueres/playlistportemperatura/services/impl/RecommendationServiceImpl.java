package net.diegoqueres.playlistportemperatura.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Country;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.openweather.OpenWeatherIntegration;
import net.diegoqueres.playlistportemperatura.integrations.spotify.SpotifyIntegration;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.SpotifyInput;
import net.diegoqueres.playlistportemperatura.repositories.RecommendationRepository;
import net.diegoqueres.playlistportemperatura.services.CityService;
import net.diegoqueres.playlistportemperatura.services.CountryService;
import net.diegoqueres.playlistportemperatura.services.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
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
		var weather = weatherIntegration.integrate(city);
		var recommendation = new Recommendation();
		var genre = getGenreByTemperature(weather.getTemperature());
		var cityAPI = Optional.ofNullable(weather.getCity());
		Country countryAPI = cityAPI.isPresent() ? cityAPI.get().getCountry() : null;
		var input = new SpotifyInput(genre, countryAPI);

		cityAPI.ifPresent((c) -> {
			var countryObj = countryService.findById(c.getCountry().getId()).orElse(countryService.insert(c.getCountry()));
			var cityObj = cityService.findById(c.getId()).orElse(cityService.insert(c));
			recommendation.setCity(cityObj);
		});

		recommendation.setGenre(genre);
		recommendation.setPlaylist(spotifyIntegration.integrate(input));

		return insert(recommendation);

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
		return repository.save(obj);
	}

}