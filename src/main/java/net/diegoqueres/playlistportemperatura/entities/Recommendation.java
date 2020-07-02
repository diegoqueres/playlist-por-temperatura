package net.diegoqueres.playlistportemperatura.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.diegoqueres.playlistportemperatura.entities.enums.Genre;
import net.diegoqueres.playlistportemperatura.integrations.spotify.entities.Playlist;

/**
 * Representa uma recomendação de playlist por parte do usuário. Estes dados são
 * usados no endpoint com as estatísticas.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 */
@Entity
@Table(name = "recommendations")
public class Recommendation implements Serializable {
	private static final long serialVersionUID = 1826513220931085327L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant createdDate;

	private Float temperature;

	@Transient
	private Playlist playlist;

	@NotNull
	private Integer genre;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@Nullable
	@JoinColumn(name = "city_id")
	private City city;

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

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Genre getGenre() {
		return Genre.valueOf(this.genre);
	}

	public void setGenre(Genre genre) {
		if (genre != null)
			this.genre = genre.getCode();
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	@JsonIgnore
	public Country getCountry() {
		return (getCity() != null ? getCity().getCountry() : null);
	}

	@PrePersist
	public void prePersist() {
		setCreatedDate(Instant.now());
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
		return "Recommendation [id=" + id + ", city=" + city + ", moment=" + createdDate + ", user=" + user + ", genre="
				+ genre + ", temperature=" + temperature + ", playlist=" + playlist + "]";
	}

}