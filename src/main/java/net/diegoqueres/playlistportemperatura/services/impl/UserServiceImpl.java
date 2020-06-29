package net.diegoqueres.playlistportemperatura.services.impl;

import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Role;
import net.diegoqueres.playlistportemperatura.services.UserService;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User authenticate(String email, String password) {
		return new User(1L, email, password, Role.USER, "Usuário da Silva");
	}

	@Override
	public User signUp(User user) {
		validateEmail(user.getEmail());
		user.setId(1L);
		return user;
	}

	@Override
	public void validateEmail(String email) {
		if (email.equalsIgnoreCase("email.duplicado@gmail.com")) {
			throw new ResourceAlreadyExistsException(email);
		}			
	}

}
