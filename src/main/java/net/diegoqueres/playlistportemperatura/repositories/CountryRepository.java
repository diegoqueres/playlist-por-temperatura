package net.diegoqueres.playlistportemperatura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.diegoqueres.playlistportemperatura.entities.Country;

/**
 * Camada de persistência dos países.
 * 
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
