package net.diegoqueres.playlistportemperatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Ponto de início da aplicação.
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
@SpringBootApplication
@EnableCaching
public class PlaylistPorTemperaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistPorTemperaturaApplication.class, args);
	}

}
