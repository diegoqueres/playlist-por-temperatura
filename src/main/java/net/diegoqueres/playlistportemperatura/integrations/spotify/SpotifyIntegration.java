package net.diegoqueres.playlistportemperatura.integrations.spotify;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.diegoqueres.playlistportemperatura.entities.Country;
import net.diegoqueres.playlistportemperatura.entities.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.Integration;
import net.diegoqueres.playlistportemperatura.integrations.exception.IntegrationException;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.Artist;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.Music;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.Playlist;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.SpotifyInput;
import net.diegoqueres.playlistportemperatura.integrations.spotify.mappers.SearchMapper;
import net.diegoqueres.playlistportemperatura.integrations.spotify.mappers.TokenMapper;
import net.diegoqueres.playlistportemperatura.utils.RandomUtils;

/**
 * Responsável por coordenar a interface de integração com a api do Spotify.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
@Service
public class SpotifyIntegration implements Integration<SpotifyInput, Playlist> {
	private static final Logger LOG = LoggerFactory.getLogger(SpotifyIntegration.class);

	private static final String SPOTIFY_URL_TOKEN = "https://accounts.spotify.com/api/token";
	private static final String SPOTIFY_URL_SEARCH = "https://api.spotify.com/v1/search";
	private static final int LIMIT_SONGS_RETURNED = 50;
	
	@Value("${spotify.api.clientecode}")
	private String spotifyClienteCode;

	@Override
	@Cacheable("spotify")
	public Playlist integrate(SpotifyInput input) {
		TokenMapper tokenMapper = getToken();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + tokenMapper.getAccessToken());

		HttpEntity<String> httpEntity = new HttpEntity<>("headers", httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
		Playlist playlist = null;
		String url = "";

		try {
			url = getUrlToIntegrate(input.getGenre(), input.getCountry());
			ResponseEntity<SearchMapper> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					SearchMapper.class);
			if (response.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(response.getBody())) {
				playlist = getPlaylistFromMapper(response.getBody(), input.getGenre());
			}

			logPlaylistContent(playlist);

		} catch (HttpClientErrorException ex) {
			LOG.error("Error when integrating with Spotify. Genre: {}, URL: {}.", input.getGenre(), url);
			throw new IntegrationException("Spotify", String.format("Genre: %s", input.getGenre()));
		}

		return playlist;

	}

	/**
	 * Loga o conteúdo da playlist.
	 * 
	 * @param playlist
	 */
	private void logPlaylistContent(Playlist playlist) {
		var playlistContent = playlist.getMusics().stream().map((song) -> {
			return String.format("Song name: %s. Artist(s): %s", song.getName(), song.getArtists().stream()
					.map((a) -> a.getName()).reduce((a1, a2) -> String.format("%s, %s", a1, a2)).orElse(""));
		}).reduce((m1, m2) -> String.format("%s\n%s", m1, m2)).map(str -> "Playlist content:\n" + str);
		LOG.info("A list of {} songs from the {} genre was suggested. {}", playlist.getMusics().size(),
				playlist.getGenre(), playlistContent.orElse(""));
	}

	private Playlist getPlaylistFromMapper(SearchMapper searchMapper, Genre genre) {
		return new Playlist(searchMapper.getTracks().getItems().stream().map((item) -> {
			var music = new Music();
			music.setId(item.getId());
			music.setName(item.getName());
			for (var artistItem : item.getArtists()) {
				music.getArtists().add(new Artist(artistItem.getId(), artistItem.getName()));
			}
			return music;
		}).collect(Collectors.toList()), genre);
	}

	private TokenMapper getToken() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		httpHeaders.add("Authorization", "Basic " + spotifyClienteCode);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

		RestTemplate restTemplate = new RestTemplate();

		try {
			ResponseEntity<TokenMapper> response = restTemplate.exchange(SPOTIFY_URL_TOKEN, HttpMethod.POST, request,
					TokenMapper.class);
			return response.getBody();
		} catch (HttpClientErrorException ex) {
			throw new IntegrationException("Spotify", "Application SPOTIFY_BASE64CODE code");
		}

	}

	private String getUrlToIntegrate(Genre genre, Optional<Country> country) {
		var offSet = (int) Math.floor(Math.random() * (LIMIT_SONGS_RETURNED * 10));
		var randomSearch = RandomUtils.getRandomSearch();
		var queryParam = String.format("%s+genre:%s", randomSearch, genre.getDescription());

		var url = new StringBuilder(SPOTIFY_URL_SEARCH).append("?type=track").append("&q=").append(queryParam)
				.append("&offset=").append(offSet).append("&limit=").append(LIMIT_SONGS_RETURNED);

		country.ifPresent(c -> url.append("&market=").append(c.getCode()));

		LOG.info("New url generated to fetch music recommendations. Genre {}, URL: {}", genre, url);

		return url.toString();

	}

}
