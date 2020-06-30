package net.diegoqueres.playlistportemperatura.enums;

/**
 * 
 * Representa os gêneros musicais do Spotify, que a aplicação pode usar ao
 * recomendar a playlist.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public enum Genre {
	ROCK(1, "rock"), POP(2, "pop"), CLASSICAL(3, "classical");

	private int code;
	private String description;

	private Genre(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		return description;
	}

	public static Genre valueOf(int code) {
		for (Genre value : Genre.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Genre code");
	}

}