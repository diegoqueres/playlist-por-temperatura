package net.diegoqueres.playlistportemperatura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.diegoqueres.playlistportemperatura.entities.Recommendation;

/**
 * Camada de persistência das recomendações de playlist efetuadas.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

}
