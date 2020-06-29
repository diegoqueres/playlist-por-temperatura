package net.diegoqueres.playlistportemperatura.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.openweather.OpenWeatherIntegration;
import net.diegoqueres.playlistportemperatura.integrations.spotify.SpotifyIntegration;
import net.diegoqueres.playlistportemperatura.services.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	private OpenWeatherIntegration weatherIntegration;
	
	@Autowired
	private SpotifyIntegration spotifyIntegration;
	
	
    @Override
    public Recommendation requestRecommendation(User user, City city) {
    	var temperature = weatherIntegration.getTemperatureByCity(city);
    	var recommendation = new Recommendation();
    	var genre = getGenreByTemperature(temperature);
        recommendation.setMusics(spotifyIntegration.getPlaylistByGenre(genre));

        return recommendation;
        
    }
    
    private Genre getGenreByTemperature(Float temperature) {
    	return Genre.POP;
    }
    
    
    
    
}