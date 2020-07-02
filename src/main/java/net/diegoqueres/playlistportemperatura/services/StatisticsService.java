package net.diegoqueres.playlistportemperatura.services;

import java.time.LocalDate;
import java.util.List;

import net.diegoqueres.playlistportemperatura.entities.statistics.StatisticsInterface;
import net.diegoqueres.playlistportemperatura.services.enums.StatisticType;

/**
 * Serviço que provê estatísticas para a aplicação.
 * 
 * @author Diego Queres
 * @since 2 de jul de 2020
 *
 */
public interface StatisticsService {

	/**
	 * Gera estatísticas da aplicação.
	 * 
	 * @param type Tipo de estatística solicitada.
	 * @return 
	 */
	List<? extends StatisticsInterface> generateStatistics(StatisticType type, LocalDate startDate, LocalDate endDate);

}
