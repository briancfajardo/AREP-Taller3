package org.arep.taller3.copySpark;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class Request {
    private String verb;
    private JSONObject body;
    private String path;
    private String query;

    /**
     * Constructor de la clase Request
     * @param rawRequest String que contiene la solicitud HTTP sin formato realizada al servidor
     * @throws URISyntaxException Mala creación del URI
     */
    public Request(String rawRequest) throws URISyntaxException {
        this.verb = rawRequest.split(" ")[0];
        URI uri = new URI(rawRequest.split(" ")[1]);
        this.path = uri.getPath();
        this.query = uri.getQuery();
        buildBody(rawRequest);
    }

    /**
     * Método que toma la solicitud sin procesar y obtiene el cuerpo si lo tiene.
     * @param rawRequest String que contiene la solicitud HTTP sin formato realizada al servidor
     */
    private void buildBody(String rawRequest){
        try {
            String[] requestLines = rawRequest.split("\n");
            this.body = new JSONObject(requestLines[requestLines.length - 1]);
        } catch (JSONException e){
            this.body = null;
        }
    }

    /**
     * Get del verbo de la petición
     * @return verbo de la petición
     */
    public String getVerb() {
        return verb;
    }


    /**
     * Get del cuerpo de la petición
     * @return cuerpo de la petición
     */
    public JSONObject getBody() {
        return body;
    }

    /**
     * Get del path de la petición
     * @return path de la petición
     */
    public String getPath() {
        return path;
    }

    /**
     * Get de la consulta de la petición
     * @return consulta de la petición
     */
    public String getQuery() {
        return query;
    }
}
