package net.diegoqueres.playlistportemperatura.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.diegoqueres.playlistportemperatura.services.UserService;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	/**
	 * Carrega o usuário através do seu e-mail. Nesta aplicação, o e-mail é
	 * considerado o username.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final String email = username;
		try {
			var user = userService.findByEmail(email);
			var jwtUser = JwtUserFactory.create(user);
			return jwtUser;
		} catch (ResourceNotFoundException e) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}

	}

}