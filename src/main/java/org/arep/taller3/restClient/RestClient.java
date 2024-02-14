package org.arep.taller3.restClient;

import org.arep.taller3.api.HttpConnection;
import org.arep.taller3.webclient.HttpServer;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.arep.taller3.copySpark.LBSpark.*;

public class RestClient {


    /**
     * Método principal que lanza el servidor y crea las funciones lambda que se usarán (por ahora)
     * @param args Argumentos necesarios para realizar un método main
     * @throws IOException Excepción que se lanza si se encuentra un problema con la conexión
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        get("/movie", req -> {
            try {
                String movieTitle = req.getQuery().split("=")[1];
                return HttpServer.movieInformation(movieTitle);
            } catch (IOException e){
                return "Movie not found";
            }
        });

        post("/movie", req -> {
            JSONObject body = req.getBody();
            try {
                String movieTitle = (String) body.get("name");
                return HttpServer.movieInformation(movieTitle);
            } catch (IOException e){
                return "Movie not found";
            }
        });
        start();
    }
}
