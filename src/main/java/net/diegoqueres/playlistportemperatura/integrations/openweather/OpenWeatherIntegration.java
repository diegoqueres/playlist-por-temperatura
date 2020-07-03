package net.diegoqueres.playlistportemperatura.integrations.openweather;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.integrations.Integration;
import net.diegoqueres.playlistportemperatura.integrations.exception.IntegrationException;
import net.diegoqueres.playlistportemperatura.integrations.openweather.entities.Weather;
import net.diegoqueres.playlistportemperatura.integrations.openweather.mappers.WeatherMapper;
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
public class OpenWeatherIntegration implements Integration<City, Weather> {
	private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherIntegration.class);

	private static final String URL_OPENWEATHER = "http://api.openweathermap.org/data/2.5/weather";
	
	@Value("${openweather.api.key}")
	private String apiKey;

	public OpenWeatherIntegration() {
	}

	@Override
	public Weather integrate(City city) {
		LOG.info("Integrating to get location data from {}", city);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = new HttpEntity<>("headers", new HttpHeaders());
		Weather weather = null;
		var url = "";

		try {
			url = getUrlToIntegrate(city);
			ResponseEntity<WeatherMapper> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					WeatherMapper.class);

			if (response.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(response.getBody())) {
				weather = parseToWeather(response.getBody());
			} 

		} catch (HttpClientErrorException ex) {
			if (ex.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
				throw new ResourceNotFoundException(city);
			}

			LOG.error("Error making request to Open Weather URL: {}", url);
			throw new IntegrationException("Open Weather", city);

		}

		return weather;

	}

	private Weather parseToWeather(WeatherMapper mapper) {
		var weather = new Weather();
		weather.setTemperature(mapper.getTemperature());

		if (!mapper.getCityName().isEmpty()) {
			var city = new City();
			city.setId(mapper.getCityId());
			city.setName(mapper.getCityName());
			city.setLatitude(mapper.getLat());
			city.setLongitude(mapper.getLng());
			city.setCountry(mapper.getCountry());
			weather.setCity(city);
		}

		return weather;

	}

	private String getUrlToIntegrate(City city) {
		Optional<String> cityName = Optional.ofNullable(city.getName());
		if (cityName.isPresent() && !cityName.isEmpty()) {
			return String.format("%s?appid=%s&q=%s&units=metric", URL_OPENWEATHER, apiKey, city.getName());
		} else {
			validateLocation(city.getLatitude(), city.getLongitude());
			return String.format("%s?appid=%s&lat=%.6f&lon=%.6f&units=metric", URL_OPENWEATHER, apiKey,
					city.getLatitude(), city.getLongitude());
		}
	}

	private void validateLocation(Float lat, Float lng) {
		if (!LocationUtils.isValidCoordinates(lat, lng)) {
			throw new IllegalArgumentException(
					String.format("Geographic coordinates are invalid: lat=%.3f, lng=%.3f", lat, lng));
		}
	}

}
