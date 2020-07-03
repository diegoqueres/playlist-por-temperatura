package net.diegoqueres.playlistportemperatura.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.diegoqueres.playlistportemperatura.entities.User;

public class UserDto implements Serializable {
	private static final long serialVersionUID = -7606894337621806978L;
	
	private Integer id;
	private String name;
	private String email;
	private String password;

	public UserDto() {
	}

	public UserDto(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void Integer(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
