package org.arep.taller3.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que implementa un caché simple resistente a concurrencia
 * @author Camilo Fajardo
 */
public class Cache {
    private final Map<String, String> cacheMemory;
    private static Cache cache = null;

    /**
     * Método que sigue el patrón singleton para solo crear una instancia del objeto Cache
     * @return La única instancia de caché creada
     */
    public static Cache getInstance(){
        if(cache == null){
            cache = new Cache();
        }
        return cache;
    }
    /**
     * Constructor de la clase Caché
     */
    private Cache(){
        cacheMemory = new ConcurrentHashMap<>();
    }

    /**
     * Método que guarda la información de una nueva película dentro del caché
     * @param title Título de la nueva película
     * @param movieInfo Información de la película
     */
    public void saveMovie(String title, String movieInfo){
        cacheMemory.put(title, movieInfo);
    }

    /**
     * Método que búsca si una película se encuentra dentro del caché
     * @param title título de la película que se desea buscar
     * @return booleano que representa si la película se encuentra o no en el caché
     */
    public boolean searchMovie(String title){
        return cacheMemory.containsKey(title);
    }

    /**
     * Método que retorna la información de una película a partir de su título
     * @param title Título de la película que se desea buscar en el caché
     * @return Información de la película
     */
    public String getMovie(String title){
        return cacheMemory.get(title);
    }

    /**
     * Método get del atributo cacheMemory
     * @return Películas almacenadas en el caché
     */
    public Map<String, String> getCacheMemory() {
        return cacheMemory;
    }
}
