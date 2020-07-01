package net.diegoqueres.playlistportemperatura.integrations.spotify.entities;

import java.util.ArrayList;
import java.util.List;

import net.diegoqueres.playlistportemperatura.enums.Genre;

/**
 * Representa uma playlist sugerida pela aplicação (em memória).
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
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
