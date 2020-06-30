package net.diegoqueres.playlistportemperatura.integrations.exception;

/**
 * 
 * Exceções ao se realizar integrações.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public class IntegrationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IntegrationException(String apiName, Object obj) {
		super(String.format("Integration Exception when integrate with %s, related to %s", apiName, obj));
	}

}
