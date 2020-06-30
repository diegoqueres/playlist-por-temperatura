package net.diegoqueres.playlistportemperatura.integrations.openweather.mappers;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.diegoqueres.playlistportemperatura.entities.Country;

/**
 * Representa dados de meteorologia no endpoint da API do Open Weather.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMapper {

	@JsonProperty("id")
	private Long cityId;

	@JsonProperty("name")
	private String cityName;

	private Country country;

	private Float lat;

	private Float lng;

	private Float temperature;

	public WeatherMapper() {
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonProperty("coord")
	private void unpackFieldsFromCoord(Map<String, Float> coords) {
		setLat(coords.get("lat"));
		setLng(coords.get("lon"));
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	@JsonProperty("main")
	private void unpackFieldsFromMain(Map<String, Float> main) {
		setTemperature(main.get("temp"));
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	@JsonProperty("sys")
	private void unpackFieldsFromSys(Map<String, String> sys) {
		var countryData = Optional.ofNullable(sys.get("country"));
		if (countryData.isPresent()) {
			setCountry(new Country(sys.get("id"), countryData.get()));
		}
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
