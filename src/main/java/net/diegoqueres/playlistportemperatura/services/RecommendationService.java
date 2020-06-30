package net.diegoqueres.playlistportemperatura.services;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Genre;

/**
 * Serviço que realiza as recomendações e gera dados para estatísticas das
 * recomendações realizadas.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public interface RecommendationService {

	/**
	 * Solicita uma recomendação de playlist de acordo com a temperatura.
	 * 
	 * @param user
	 * @param city
	 * @return Recomendação de playlist.
	 */
	Recommendation requestRecommendation(User user, City city);

	/**
	 * Obtêm o gênero musical recomendado, de acordo com a temperatura.
	 * 
	 * @param temperature em graus celsius
	 * @return Gênero musical recomendado.
	 */
	Genre getGenreByTemperature(Float temperature);

}