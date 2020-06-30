package net.diegoqueres.playlistportemperatura.integrations;

/**
 * 
 * Interface padronização do comportamento das integrações.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 * @param <R> Entidade que representa o resultado da integração.
 * @param <I> Entidade com dados de entrada para processar a integração.
 */
public interface Integration<I, R> {

	/**
	 * Realiza uma integração passando valores de entrada.
	 * 
	 * @param input
	 * @return Resultado da integração como objeto.
	 */
	R integrate(I input);

}
