package net.diegoqueres.playlistportemperatura.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.diegoqueres.playlistportemperatura.entities.User;

/**
 * Camada de persistência dos usuários da aplicação.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
