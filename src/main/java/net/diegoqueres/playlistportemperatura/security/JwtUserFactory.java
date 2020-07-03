package net.diegoqueres.playlistportemperatura.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.security.enums.SecurityRole;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(),
				mapToGrantedAuthorities(user.getSecurityRole()));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(SecurityRole role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		return authorities;
	}

}
