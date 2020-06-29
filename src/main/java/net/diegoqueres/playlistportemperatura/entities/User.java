package net.diegoqueres.playlistportemperatura.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.diegoqueres.playlistportemperatura.enums.Role;

/**
 * Representa um usuário da aplicação.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 6748764480624690559L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;
	private Role role;
	private String email;

	public User() {
	}

	public User(Long id, String email, String password, Role role, String name) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}