package net.diegoqueres.playlistportemperatura.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RandomUtilsTest {

	@Test
	void testGetRandomSearch() {
		assertDoesNotThrow(() -> {
			var result = RandomUtils.getRandomSearch();
			assertNotNull(result);
			assertTrue(result.matches("\\%??[a-z]\\%"));	
		});
	}

}
