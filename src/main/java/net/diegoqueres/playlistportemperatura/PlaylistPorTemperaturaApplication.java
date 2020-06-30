package net.diegoqueres.playlistportemperatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.diegoqueres.playlistportemperatura.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.spotify.SpotifyIntegration;

@SpringBootApplication
public class PlaylistPorTemperaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistPorTemperaturaApplication.class, args);
	}

}
