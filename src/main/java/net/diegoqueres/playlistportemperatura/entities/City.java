package net.diegoqueres.playlistportemperatura.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Representa uma cidade onde foi solicitada uma recomendação de playlist.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
@Entity
public class City implements Serializable {
	private static final long serialVersionUID = -3327042720736672745L;

	@Id
	private Long id;

	private String name;

	private Country country;

	private Float latitude;

	private Float longitude;

	public City() {
	}

	public City(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
		City other = (City) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", country=" + country + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

}
