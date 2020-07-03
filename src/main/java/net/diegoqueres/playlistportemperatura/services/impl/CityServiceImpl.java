package net.diegoqueres.playlistportemperatura.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.City;
import net.diegoqueres.playlistportemperatura.repositories.CityRepository;
import net.diegoqueres.playlistportemperatura.services.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository repository;

	public Optional<City> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<City> findByName(String name) {
		return repository.findByName(name);
	}

	public List<City> findAll() {
		return repository.findAll();
	}

	public City insert(City obj) {
		return repository.save(obj);
	}

}
