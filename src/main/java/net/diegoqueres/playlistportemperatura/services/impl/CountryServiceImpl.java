package net.diegoqueres.playlistportemperatura.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.Country;
import net.diegoqueres.playlistportemperatura.repositories.CountryRepository;
import net.diegoqueres.playlistportemperatura.services.CountryService;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository repository;

	public Optional<Country> findById(Integer id) {
		return repository.findById(id);
	}

	public List<Country> findAll() {
		return repository.findAll();
	}

	public Country insert(Country obj) {
		return repository.save(obj);
	}

}
