package org.arep.taller3.copySpark;

public interface Function {
    /**
     * Método que se llamará cuando se llame una function
     * @param request Objeto request que representa un request HTTP
     * @return String que contiene la respuesta del cliente
     */
    String handle(Request request);
}
