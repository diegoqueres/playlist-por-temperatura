package net.diegoqueres.playlistportemperatura.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Representa um país associado à cidade.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
@Entity
public class Country {
	@Id
	private Integer id;

	private String code;

	public Country() {
	}

	public Country(Integer id, String code) {
		this.id = id;
		this.code = code;
	}

	public Country(String id, String code) {
		this(Integer.parseInt(id), code);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		Country other = (Country) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", code=" + code + "]";
	}

}
