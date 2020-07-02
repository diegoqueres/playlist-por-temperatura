package net.diegoqueres.playlistportemperatura.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.diegoqueres.playlistportemperatura.entities.City;

/**
 * Camada de persistência das cidades.
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	Optional<City> findByName(String name);

}
