package net.diegoqueres.playlistportemperatura.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.diegoqueres.playlistportemperatura.entities.Recommendation;
import net.diegoqueres.playlistportemperatura.entities.statistics.RecommendationByCityStatistics;

/**
 * Camada de persistência das recomendações de playlist efetuadas.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

	/**
	 * Gera estatísticas de uso das recomendações agrupadas por cidade.
	 * 
	 * @param startDate Data início das recomendações.
	 * @param endDate   Data fim das recomendações.
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT  "
			+ "   cit.NAME as cityName, coun.CODE as countryCode, COUNT(*) as usageCount  " + "		FROM    "
			+ "			recommendations recom    " + "			INNER JOIN cities cit ON recom.CITY_ID = cit.ID    "
			+ "			INNER JOIN countries coun ON cit.COUNTRY_ID  = coun.ID   " + "		WHERE    "
			+ "			recom.CREATED_DATE BETWEEN :startDate AND :endDate   " + "		GROUP BY   "
			+ "			cit.NAME, coun.CODE " + "   ORDER BY usageCount DESC")
	List<RecommendationByCityStatistics> getRecommendationByCityStatistics(
			@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

}
