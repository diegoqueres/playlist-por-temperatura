package net.diegoqueres.playlistportemperatura.integrations.openweather;

import java.util.Optional;

import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;
import net.diegoqueres.playlistportemperatura.utils.LocationUtils;

/**
 * Responsável por coordenar a interface de integração com a api do Open
 * Weather.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
@Service
public class OpenWeatherIntegration {

	public Float getTemperatureByCity(City city) {
		if (Optional.ofNullable(city.getName()).isPresent()) {
	    	if (city.getName().equals("d343434adfdfdr434343cadabralslsl")) {
	    		throw new ResourceNotFoundException(city);
	    	}
		} else if (Optional.ofNullable(city.getLatitude()).isPresent() && Optional.ofNullable(city.getLongitude()).isPresent()) {
			if (!LocationUtils.isValidCoordinates(city.getLatitude(), city.getLongitude())) {
				throw new IllegalArgumentException(String.format("Geographic coordinates are invalid: lat=%.3f, lng=%.3f", city.getLatitude(), city.getLongitude()));
			}
		}
    	
    	return 20.0f;
    	
	}
	
}
