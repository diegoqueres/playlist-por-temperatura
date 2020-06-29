package net.diegoqueres.playlistportemperatura.services;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.User;

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
	 * Solicita uma recomendação de playlist's de acordo com a temperatura.
	 * 
	 * @param user
	 * @param city
	 * @return
	 */
	Recommendation requestRecommendation(User user, City city);

}