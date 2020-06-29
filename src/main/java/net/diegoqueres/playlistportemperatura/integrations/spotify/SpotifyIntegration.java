package net.diegoqueres.playlistportemperatura.integrations.spotify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.spotify.mappers.ArtistMapper;
import net.diegoqueres.playlistportemperatura.integrations.spotify.mappers.MusicMapper;

/**
 * Responsável por coordenar a interface de integração com a api do Spotify.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
@Service
public class SpotifyIntegration {

	public List<MusicMapper> getPlaylistByGenre(Genre genre) {
		List<MusicMapper> musics = new ArrayList<>();

		var artist1 = new ArtistMapper("6tw6EpCt3", "Pink Floyd");
		var artist2 = new ArtistMapper("6tw434fCt", "Beatles");
		var music1 = new MusicMapper("dkfk39849", "Another brick on the wall");
		var music2 = new MusicMapper("dkfk3re49", "Michelle");
		var music3 = new MusicMapper("dkf339849", "Live and let die");

		music1.getArtists().add(artist1);
		music2.getArtists().add(artist2);
		music3.getArtists().add(artist1);
		music3.getArtists().add(artist2);

		musics.addAll(Arrays.asList(music1, music2, music3));

		return musics;

	}

}
