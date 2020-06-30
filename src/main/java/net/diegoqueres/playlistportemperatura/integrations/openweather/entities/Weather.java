package net.diegoqueres.playlistportemperatura.integrations.openweather.entities;

import net.diegoqueres.playlistportemperatura.entities.City;

/**
 * Encapsula o retorno da integração com o Open Weather.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public class Weather {
	private City city;
	private Float temperature;
	
	public Weather() {
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}	
	
}
