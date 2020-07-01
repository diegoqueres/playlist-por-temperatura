package net.diegoqueres.playlistportemperatura.services;

import java.util.List;
import java.util.Optional;

import net.diegoqueres.playlistportemperatura.entities.City;

/**
 * Serviços relacionados à entidade cidade (City).
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
public interface CityService {
	/**
	 * Busca uma cidade por id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<City> findById(Long id);

	/**
	 * Retorna todas as cidades.
	 * 
	 * @return
	 */
	List<City> findAll();

	/**
	 * Grava uma nova cidade.
	 * 
	 * @param obj
	 * @return
	 */
	City insert(City obj);

}
