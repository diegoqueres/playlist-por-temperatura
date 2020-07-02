package net.diegoqueres.playlistportemperatura.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.diegoqueres.playlistportemperatura.entities.statistics.StatisticsInterface;
import net.diegoqueres.playlistportemperatura.services.StatisticsService;
import net.diegoqueres.playlistportemperatura.services.enums.StatisticType;

/**
 * Controller que trata requisições relacionadas à estatísticas e reporting.
 * 
 * @author Diego Queres
 * @since 2 de jul de 2020
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/statistics")
public class StatisticsController {
	private static final Logger LOG = LoggerFactory.getLogger(StatisticsController.class);

	@Autowired
	private StatisticsService service;

	/**
	 * Trata as requisições de estatísticas para a aplicação.
	 * @param statisticType Tipo de estatística.
	 * @param startDate Data de início para consulta.
	 * @param endDate Data fim para consulta.
	 * @return Estatísticas geradas.
	 */
	@GetMapping(value = "/{statisticType}")
	public ResponseEntity<List<? extends StatisticsInterface>> requestStatistics(@PathVariable String statisticType,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "start_date", required=true) LocalDate startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "end_date", required=true) LocalDate endDate) 
	{		
		LOG.info("Gerando estatísticas solicitadas pelo usuário. Tipo: {}", statisticType);
		var type = StatisticType.valueOf(statisticType.toUpperCase());
		var statistics = service.generateStatistics(type, startDate, endDate);
		return ResponseEntity.ok().body(statistics);		

	}

}
