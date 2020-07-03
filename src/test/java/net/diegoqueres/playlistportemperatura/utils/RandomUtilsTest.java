package net.diegoqueres.playlistportemperatura.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

class RandomUtilsTest {

	/**
	 * Testa se a geração de string para busca randômica ocorre dentro do padrão esperado: %[caracter]% ou [caracter]%.
	 * 
	 */
	@Test
	@WithMockUser
	void testGetRandomSearch() {
		assertDoesNotThrow(() -> {
			var result = RandomUtils.getRandomSearch();
			assertNotNull(result);
			assertTrue(result.matches("\\%??[a-z]\\%"));	
		});
	}

}
