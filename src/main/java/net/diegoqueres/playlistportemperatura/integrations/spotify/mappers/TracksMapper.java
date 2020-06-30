package net.diegoqueres.playlistportemperatura.integrations.spotify.mappers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
