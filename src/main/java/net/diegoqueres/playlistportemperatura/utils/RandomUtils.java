package net.diegoqueres.playlistportemperatura.utils;

/**
 * Funções úteis para randomização.
 * 
 * @author Diego Queres
 * @since 30 de jun de 2020
 *
 */
public abstract class RandomUtils {

	/**
	 * Para gerar um caracter para realizar uma busca genérica, com um símbolo de
	 * porcentagem. Exemplos: 1) 'a%' 2) '%a%'
	 * 
	 * Baseado na função getRandomSearch() de Perry Janssen.
	 * 
	 *
	 * @see https://shorturl.at/ikyDE
	 * @author Perry Janssen
	 *
	 * @return String para realizar a busca.
	 */
	public static String getRandomSearch() {

		// A list of all characters that can be chosen.
		var characters = "abcdefghijklmnopqrstuvwxyz";

		// Gets a random character from the characters string.
		var randomCharacter = characters.charAt((int) Math.floor(Math.random() * characters.length()));
		var randomSearch = "";

		// Places the wildcard character at the beginning, or both beginning and end,
		// randomly.
		var randomNumber = (int) Math.round(Math.random());
		switch (randomNumber) {
		case 0:
			randomSearch = randomCharacter + "%";
			break;
		case 1:
			randomSearch = "%" + randomCharacter + "%";
			break;
		}

		return randomSearch;
	}

}
