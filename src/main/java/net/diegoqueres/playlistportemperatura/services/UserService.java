package net.diegoqueres.playlistportemperatura.services;

import java.util.List;

import net.diegoqueres.playlistportemperatura.dtos.UserDto;
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
	 * Cadastro de usuário para usar a API.
	 * 
	 * @param user
	 * @return
	 */
	User signUp(User user);

	/**
	 * Valida se o e-mail já está cadastrado na base de dados.
	 * 
	 * @param email
	 */
	void validateEmail(String email);

	/**
	 * Busca um usuário por id.
	 * 
	 * @param id
	 * @return
	 */
	User findById(Integer id);

	/**
	 * Busca um usuário pelo seu endereço de e-mail.
	 * 
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * Retorna todos os usuários.
	 * 
	 * @return
	 */
	List<User> findAll();

	/**
	 * Recupera um objeto User a partir de um DTO.
	 * 
	 * @param objDto
	 * @return
	 */
	User fromDTO(UserDto objDto);

}
