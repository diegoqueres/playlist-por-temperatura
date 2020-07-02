package net.diegoqueres.playlistportemperatura.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.diegoqueres.playlistportemperatura.services.enums.StatisticType;

/**
 * Testes para o serviço de estatísticas.
 * 
 * @author Diego Queres
 * @since 2 de jul de 2020
 *
 */
@SpringBootTest
@ActiveProfiles("test")
class StatisticsServiceTest {

	@Autowired
	private StatisticsService service;

	/**
	 * Testa o retorno das estatísticas quando a solicitação ocorre normalmente.
	 */
	@Test
	void testGenerateStatistics() {
		LocalDate startDate = LocalDate.now().plusDays(-7);
		LocalDate endDate = LocalDate.now().plusDays(1);

		var results = service.generateStatistics(StatisticType.RECOMMENDATIONS_BY_CITY, startDate, endDate);

		assertNotNull(results);
		assertTrue(!results.isEmpty());

	}

	/**
	 * Testa o retorno quando o usuário solicita um intervalo sem dados.
	 */
	@Test
	void testRequestOutOfRangeStatistics() {
		LocalDate startDate = LocalDate.now().plusDays(1);
		LocalDate endDate = LocalDate.now().plusDays(10);

		var results = service.generateStatistics(StatisticType.RECOMMENDATIONS_BY_CITY, startDate, endDate);

		assertNotNull(results);
		assertTrue(results.isEmpty());

	}

}
