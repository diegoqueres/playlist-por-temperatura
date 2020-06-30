package net.diegoqueres.playlistportemperatura.integrations.spotify.entities;

import java.util.ArrayList;
import java.util.List;

import net.diegoqueres.playlistportemperatura.enums.Genre;

public class Playlist {
	private Genre genre;
	private List<Music> musics;

	public Playlist() {
		musics = new ArrayList<>();
	}

	public Playlist(List<Music> musics, Genre genre) {
		this.genre = genre;
		this.musics = musics;
	}

	public List<Music> getMusics() {
		return musics;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
