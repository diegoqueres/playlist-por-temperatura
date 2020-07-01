package net.diegoqueres.playlistportemperatura.integrations.spotify.mappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapper para leitura do resultado da busca na API.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchMapper {

	@JsonProperty("tracks")
	private TracksMapper tracks;

	public TracksMapper getTracks() {
		return tracks;
	}

	public void setTracks(TracksMapper tracks) {
		this.tracks = tracks;
	}

}
