package net.diegoqueres.playlistportemperatura.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.openweather.OpenWeatherIntegration;
import net.diegoqueres.playlistportemperatura.integrations.spotify.SpotifyIntegration;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.SpotifyInput;
import net.diegoqueres.playlistportemperatura.services.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	private static final int TEMPERATURE_HIGH = 25;
	private static final int TEMPERATURE_LOW = 10;

	@Autowired
	private OpenWeatherIntegration weatherIntegration;

	@Autowired
	private SpotifyIntegration spotifyIntegration;

	@Override
	public Recommendation requestRecommendation(User user, City city) {
		var weather = weatherIntegration.integrate(city);
		var recommendation = new Recommendation();
		var genre = getGenreByTemperature(weather.getTemperature());
		var input = new SpotifyInput(genre, weather.getCity().getCountry());

		recommendation.setCity(weather.getCity());
		recommendation.setPlaylist(spotifyIntegration.integrate(input));

		return recommendation;

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

}