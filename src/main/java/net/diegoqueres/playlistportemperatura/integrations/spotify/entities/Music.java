package net.diegoqueres.playlistportemperatura.integrations.spotify.entities;

import java.util.ArrayList;
import java.util.List;

public class Music {
	private String id;
	private String name;
	private List<Artist> artists;

	public Music() {
		artists = new ArrayList<>();
	}

	public Music(String id, String name) {
		this();
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

	public List<Artist> getArtists() {
		return artists;
	}

}
