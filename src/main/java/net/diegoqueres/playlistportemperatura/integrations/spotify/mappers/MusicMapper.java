package net.diegoqueres.playlistportemperatura.integrations.spotify.mappers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa uma música no endpoint da API do Spotify.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public class MusicMapper {

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("artists")
	private List<ArtistMapper> artists;

	public MusicMapper() {
	}

	public MusicMapper(String id, String name) {
		this.artists = new ArrayList<>();
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

	public List<ArtistMapper> getArtists() {
		return artists;
	}

	public void setArtists(List<ArtistMapper> artists) {
		this.artists = artists;
	}

}