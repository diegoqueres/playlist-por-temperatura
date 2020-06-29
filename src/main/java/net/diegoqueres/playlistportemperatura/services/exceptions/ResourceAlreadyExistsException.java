package net.diegoqueres.playlistportemperatura.services.exceptions;

/**
 * 
 * Exceção para representar um novo cadastro que foi impedido porquê o recurso
 * já existe no banco de dados.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public class ResourceAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(Object obj) {
		super(String.format("Resource already exists: %s", obj));
	}

}
