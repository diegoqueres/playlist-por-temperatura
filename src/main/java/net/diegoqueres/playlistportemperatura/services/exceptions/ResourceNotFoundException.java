package net.diegoqueres.playlistportemperatura.services.exceptions;

/**
 * 
 * Exceção para representar recursos não localizados na operação de um serviço.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object obj) {
		super(String.format("Resource not found: %s", obj));
	}

}
