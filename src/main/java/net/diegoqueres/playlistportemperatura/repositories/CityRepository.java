package net.diegoqueres.playlistportemperatura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.diegoqueres.playlistportemperatura.entities.City;

/**
 * Camada de persistência das cidades.
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
public interface CityRepository extends JpaRepository<City, Long> {

}
