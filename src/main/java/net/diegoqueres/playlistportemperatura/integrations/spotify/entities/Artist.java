package net.diegoqueres.playlistportemperatura.integrations.spotify.entities;

/**
 * Representa o artista de uma música sugerida pela aplicação (em memória).
 * @author Diego Queres
 * @since 1 de jul de 2020
 *
 */
public class Artist {
	private String id;
	private String name;
	
	public Artist() {}
	
	public Artist(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
		
}
