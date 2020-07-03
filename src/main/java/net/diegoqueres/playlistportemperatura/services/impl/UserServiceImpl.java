package net.diegoqueres.playlistportemperatura.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.dtos.UserDto;
import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.entities.enums.Role;
import net.diegoqueres.playlistportemperatura.repositories.UserRepository;
import net.diegoqueres.playlistportemperatura.security.PasswordEncoder;
import net.diegoqueres.playlistportemperatura.services.UserService;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceAlreadyExistsException;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;


	@Override
	public User signUp(User user) {
		validateEmail(user.getEmail());
		user.setRole(Role.USER);
		user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
		LOG.info("Salvando novo usuário: {}", user);
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
		LOG.info("Buscando usuário por id: {}", id);
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public List<User> findAll() {
		LOG.info("Recuperando todos os usuários.");
		return repository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		LOG.info("Buscando usuário por e-mail: {}", email);
		Optional<User> obj = repository.findByEmail(email);
		return obj.orElseThrow(() -> new ResourceNotFoundException(email));
	}
	
	@Override
	public User fromDTO(UserDto objDto) {
		return new User(objDto.getEmail(), objDto.getPassword(), objDto.getName());
	}

}
