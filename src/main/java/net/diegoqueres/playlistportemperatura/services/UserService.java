package net.diegoqueres.playlistportemperatura.services;

import net.diegoqueres.playlistportemperatura.entities.User;

/**
 * 
 * Classe de serviços relativos a entidade de usuários.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public interface UserService {

	/**
	 * Autenticação de usuário para usar a API.
	 * @param email
	 * @param password
	 * @return
	 */
	User authenticate(String email, String password);

	/**
	 * Cadastro de usuário para usar a API.
	 * @param user
	 * @return
	 */
	User signUp(User user);

	/**
	 * Valida se o e-mail já está cadastrado na base de dados.
	 * @param email
	 */
	void validateEmail(String email);

}
