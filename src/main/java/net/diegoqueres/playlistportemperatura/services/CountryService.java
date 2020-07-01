package net.diegoqueres.playlistportemperatura.services;

import java.util.List;
import java.util.Optional;

import net.diegoqueres.playlistportemperatura.entities.Country;

/**
 * Serviços relacionados à entidade país (Country).
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
public interface CountryService {
	/**
	 * Busca um país por id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Country> findById(Integer id);

	/**
	 * Retorna todos os países.
	 * 
	 * @return
	 */
	List<Country> findAll();

	/**
	 * Grava um novo país.
	 * 
	 * @param obj
	 * @return
	 */
	Country insert(Country obj);

}
