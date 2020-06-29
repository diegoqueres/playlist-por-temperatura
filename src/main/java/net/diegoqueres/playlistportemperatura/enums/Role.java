package net.diegoqueres.playlistportemperatura.enums;

/**
 * Representa o papel que usuários podem ter na aplicação.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public enum Role {
	USER(1),
	ADMIN(2);
    
    private int code;
	
	private Role(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
    }
	
	public static Role valueOf(int code) {
		for (Role value : Role.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Role code");
    } 
       
}