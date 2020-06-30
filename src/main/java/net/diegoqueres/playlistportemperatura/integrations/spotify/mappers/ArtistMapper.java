package net.diegoqueres.playlistportemperatura.integrations.spotify.mappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapper da música no JSON que a API retorna.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistMapper {

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

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