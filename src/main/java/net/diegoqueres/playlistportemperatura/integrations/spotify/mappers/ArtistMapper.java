package net.diegoqueres.playlistportemperatura.integrations.spotify.mappers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa um artista no endpoint da API do Spotify.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public class ArtistMapper {

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	public ArtistMapper() {
	}

	public ArtistMapper(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}