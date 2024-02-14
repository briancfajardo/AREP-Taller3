package org.arep.taller3.copySpark;

import org.arep.taller3.webclient.HttpServer;

import java.io.*;

import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.Map;


public class LBSpark {
    private static final Map<String, Function> GET_SERVICES = new HashMap<>();
    private static final Map<String, Function> POST_SERVICES = new HashMap<>();

    /**
     * Método que guarda una función GET y lo mapea en un endpoint
     * @param endpoint Endpoint de la función
     * @param lambda Función que se retornará cuando se llame al endpoint
     */
    public static void get(String endpoint, Function lambda){
        GET_SERVICES.put(endpoint, lambda);
    }
    /**
     * Método que guarda una función POST y lo mapea en un endpoint
     * @param endpoint Endpoint de la función
     * @param lambda Función que se retornará cuando se llame al endpoint
     */
    public static void post(String endpoint, Function lambda){
        POST_SERVICES.put(endpoint, lambda);
    }

    /**
     * Método que busca dentro del diccionario de funciones lambda
     * @param endpoint EndPoint de la función
     * @param verb Método que se usó en la declaración de la función, ejemplo GET, POST
     * @return La función mapeada del endpoint si fue encontrada
     */
    public static Function search(String endpoint, String verb){
        switch (verb){
            case "GET":
                return GET_SERVICES.get(endpoint);
            case "POST":
                return POST_SERVICES.get(endpoint);
        }
        return null;
    }

    /**
     * Método que inicia el servidor
     * @throws IOException En caso de que alguna funcionalidad I/O falle
     * @throws URISyntaxException En el caso de que la creación de URI falle
     */
    public static void start() throws IOException, URISyntaxException {
        HttpServer.runServer();
    }

}