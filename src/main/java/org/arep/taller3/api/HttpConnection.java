package org.arep.taller3.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Clase que genera y manja la conexión con el api de OMD
 * @author Brian Camilo Fajardo Sanchez
 * @author Daniel Benavides
 */
public class HttpConnection {

    private static final String GET_URL = "https://www.omdbapi.com/?i=tt3896198&apikey=cdacf510&t=";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Cache cache = Cache.getInstance();

    /** Método que realiza la conexión con el api externo para obtener la información de una película a partir de
     * su título
     * @param movieTitle Título de la película que se desea buscar
     * @return Información de la película
     * @throws IOException Excepción que se lanza si la URL se encuentra mal formada o si se pierde la conexión
     */
    public static String getMovie(String movieTitle) throws IOException {

        if(cache.searchMovie(movieTitle)){
            System.out.println("Se uso CACHE");
            return cache.getMovie(movieTitle);
        }

        HttpURLConnection con = makeGetRequest(movieTitle);

        StringBuffer response = new StringBuffer();
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code of API: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            response = buildResponse(con);
            System.out.println(response); // print result
        } else {
            System.out.println("GET request not worked");
        }

        cache.saveMovie(movieTitle, response.toString());
        System.out.println("GET DONE");
        return response.toString();
    }

    /**
     * Método que realiza la conexión con el api externa
     * @param movieTitle Título de la película
     * @return Conexión con el api que contiene la información de la película
     * @throws IOException Excepción que se lanza si la URL se encuentra mal formada o si se pierde la conexión
     */
    private static HttpURLConnection makeGetRequest(String movieTitle) throws IOException{
        URL obj = new URL(GET_URL + movieTitle);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        return con;
    }

    /**
     * Método que toma la respuesta sin procesar de la API y la convierte en un StringBuffer más fácil de manejar.
     * @param con Conexión con la API
     * @return Información de la película
     * @throws IOException Excepción que se lanza si la URL se encuentra mal formada o si se pierde la conexión
     */
    private static StringBuffer buildResponse(HttpURLConnection con) throws IOException {
        StringBuffer response = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

}
