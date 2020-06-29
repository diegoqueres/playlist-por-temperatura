package net.diegoqueres.playlistportemperatura.utils;

/**
 * Funções utilitárias para lidar com localização.
 * 
 * @author Diego Queres
 * @since 29 de jun de 2020
 *
 */
public abstract class LocationUtils {
	
	/**
	 * Retorna se as coordenadas de localização são válidas.
	 * @param lat Latitude
	 * @param lng Longitude
	 * @return
	 */
	public static boolean isValidCoordinates(Float lat, Float lng) {
		var isLat = Math.abs(lat) <= 90;
		var isLng = Math.abs(lng) <= 180;
		return (isLat && isLng);
	}
	
}
