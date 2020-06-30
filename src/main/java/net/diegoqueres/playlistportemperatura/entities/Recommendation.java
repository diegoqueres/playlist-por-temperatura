package net.diegoqueres.playlistportemperatura.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.diegoqueres.playlistportemperatura.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.Playlist;

/**
 * Representa uma recomendação de playlist por parte do usuário. Estes dados são
 * usados no endpoint com as estatísticas.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 */
@Entity
public class Recommendation implements Serializable {
	private static final long serialVersionUID = 1826513220931085327L;

	@Id
	@GeneratedValue
	private Long id;

	private City city;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	private User user;

	private Genre genre;

	@JsonIgnore
	private Float temperature;

	@Transient
	private Playlist playlist;

	public Recommendation() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
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
		Recommendation other = (Recommendation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recommendation [id=" + id + ", city=" + city + ", moment=" + moment + ", user=" + user + ", genre="
				+ genre + ", temperature=" + temperature + ", playlist=" + playlist + "]";
	}

}