package net.diegoqueres.playlistportemperatura.integrations.spotify.mappers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapper que encapsula a lista de músicas no resultado da API.
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
public class TracksMapper {

	@JsonProperty("items")
	private List<ItemMapper> items;

	public List<ItemMapper> getItems() {
		return items;
	}

	public void setItems(List<ItemMapper> items) {
		this.items = items;
	}

}
