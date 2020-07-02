package net.diegoqueres.playlistportemperatura.entities.statistics;

public interface RecommendationByCityStatistics extends StatisticsInterface {
	String getCityName();

	String getCountryCode();

	Integer getUsageCount();
}
