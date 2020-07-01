package net.diegoqueres.playlistportemperatura.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Role;
import net.diegoqueres.playlistportemperatura.repositories.UserRepository;
import net.diegoqueres.playlistportemperatura.services.UserService;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceAlreadyExistsException;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User authenticate(String email, String password) {
		return new User(1, email, password, Role.USER, "Usuário da Silva");
	}

	@Override
	public User signUp(User user) {
		validateEmail(user.getEmail());
		return repository.save(user);
	}

	@Override
	public void validateEmail(String email) {
		if (repository.findByEmail(email).isPresent()) {
			throw new ResourceAlreadyExistsException(email);
		}
	}

	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

}
