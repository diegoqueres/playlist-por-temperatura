package net.diegoqueres.playlistportemperatura.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import net.diegoqueres.playlistportemperatura.entities.User;
import net.diegoqueres.playlistportemperatura.enums.Role;
import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceAlreadyExistsException;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired
	private UserService service;

	@Test
	public void testAuthenticate() {
		var user = service.authenticate("user@user.com", "dkf39493jk");
		assertNotNull(user);
		assertNotNull(user.getId());
	}

	@Test
	public void testSignUp() {
		var user = new User(null, "user@user.com", "dkf39493jk", Role.USER, "Usuário da Silva");
		var newUser = service.signUp(user);
		assertNotNull(newUser);
		assertNotNull(newUser.getId());
	}

	@Test
	public void testValidateEmail() {
		var email = "johnd343432sw@gmail.com";
		assertDoesNotThrow(() -> service.validateEmail(email));
	}

	@Test
	public void testValidateEmailWhenItExists() {
		var email = "email.duplicado@gmail.com";
		assertThrows(ResourceAlreadyExistsException.class, () -> {
			service.validateEmail(email);
		});
	}

}
