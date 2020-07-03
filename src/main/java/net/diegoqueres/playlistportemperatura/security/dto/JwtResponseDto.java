package net.diegoqueres.playlistportemperatura.security.dto;

import java.io.Serializable;

public class JwtResponseDto implements Serializable {
	private static final long serialVersionUID = 6890001597283897942L;
	private final String jwttoken;

	public JwtResponseDto(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}