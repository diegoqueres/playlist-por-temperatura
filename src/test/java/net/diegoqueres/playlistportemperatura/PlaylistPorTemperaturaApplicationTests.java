package net.diegoqueres.playlistportemperatura;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlaylistPorTemperaturaApplicationTests {

	@Test
	@WithMockUser
	void contextLoads() {
	}

}
