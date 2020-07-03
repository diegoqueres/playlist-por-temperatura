package net.diegoqueres.playlistportemperatura.security.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtRequestDto implements Serializable {
	private static final long serialVersionUID = -7082643257222605875L;
	
	@JsonProperty("email")
	private String username;
	private String password;

	public JwtRequestDto() {
	}

	public JwtRequestDto(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
