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
	ROCK(1, "rock,rock-n-roll"), POP(2, "pop"), CLASSICAL(3, "classical");

	private int code;
	private String value;

	private Genre(int code, String value) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return value;
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