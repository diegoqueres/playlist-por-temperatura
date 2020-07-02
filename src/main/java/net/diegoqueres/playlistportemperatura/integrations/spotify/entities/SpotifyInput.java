package net.diegoqueres.playlistportemperatura.integrations.spotify.entities;

import java.util.Optional;

import net.diegoqueres.playlistportemperatura.entities.Country;
import net.diegoqueres.playlistportemperatura.entities.enums.Genre;

/**
 * Dados de entrada para solicitar uma playlist na API do Spotify.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
public class SpotifyInput {
	private Genre genre;
	private Optional<Country> country;

	public SpotifyInput() {
	}

	public SpotifyInput(Genre genre, Country country) {
		this.genre = genre;
		this.country = Optional.ofNullable(country);
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Optional<Country> getCountry() {
		return country;
	}

	public void setCountry(Optional<Country> country) {
		this.country = country;
	}

}
