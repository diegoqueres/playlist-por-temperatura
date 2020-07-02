package net.diegoqueres.playlistportemperatura.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.statistics.StatisticsInterface;
import net.diegoqueres.playlistportemperatura.repositories.RecommendationRepository;
import net.diegoqueres.playlistportemperatura.services.StatisticsService;
import net.diegoqueres.playlistportemperatura.services.enums.StatisticType;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private RecommendationRepository recommendationRepository;

	@Override
	public List<? extends StatisticsInterface> generateStatistics(StatisticType type, LocalDate startDate, LocalDate endDate) {
		switch (type) {
			case RECOMMENDATIONS_BY_CITY:
			default:
				return recommendationRepository.getRecommendationByCityStatistics(startDate, endDate);
		}

	}

}
