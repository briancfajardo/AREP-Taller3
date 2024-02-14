package org.arep.taller3;

import org.testng.annotations.Test;
import org.arep.taller3.api.Cache;
import org.arep.taller3.api.HttpConnection;

import java.util.*;
import java.io.*;

import static junit.framework.Assert.assertEquals;

/**
 *
 */
public class ApiTests {

    @Test
    public void testingGetMovies() throws IOException {
        // ARRANGE
        Map<String, String> correctInfo = new HashMap<>();
        correctInfo.put("Fast", "{\"Title\":\"The Fast and the Furious\",\"Year\":\"2001\",\"Rated\":\"PG-13\",\"Released\":\"22 Jun 2001\",\"Runtime\":\"106 min\",\"Genre\":\"Action, Crime, Thriller\",\"Director\":\"Rob Cohen\",\"Writer\":\"Ken Li, Gary Scott Thompson, Erik Bergquist\",\"Actors\":\"Vin Diesel, Paul Walker, Michelle Rodriguez\",\"Plot\":\"Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.\",\"Language\":\"English, Spanish\",\"Country\":\"United States, Germany\",\"Awards\":\"11 wins & 18 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"6.8/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"54%\"},{\"Source\":\"Metacritic\",\"Value\":\"58/100\"}],\"Metascore\":\"58\",\"imdbRating\":\"6.8\",\"imdbVotes\":\"413,784\",\"imdbID\":\"tt0232500\",\"Type\":\"movie\",\"DVD\":\"10 Sep 2015\",\"BoxOffice\":\"$144,745,925\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");

        // ACT
        String searchedInfo = HttpConnection.getMovie("Fast");

        // ASSERT
        assertEquals(correctInfo.get("Fast"), searchedInfo);
    }

    @Test
    public void testingCacheConcurrency() throws InterruptedException {
        // ARRANGE
        List<Thread> threads = new ArrayList<>();
        threads.add(new ThreadConnection("Fast"));
        threads.add(new ThreadConnection("Fast"));
        threads.add(new ThreadConnection("Fast"));
        threads.add(new ThreadConnection("Fast"));

        String itInfo = "{\"Title\":\"The Fast and the Furious\",\"Year\":\"2001\",\"Rated\":\"PG-13\",\"Released\":\"22 Jun 2001\",\"Runtime\":\"106 min\",\"Genre\":\"Action, Crime, Thriller\",\"Director\":\"Rob Cohen\",\"Writer\":\"Ken Li, Gary Scott Thompson, Erik Bergquist\",\"Actors\":\"Vin Diesel, Paul Walker, Michelle Rodriguez\",\"Plot\":\"Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.\",\"Language\":\"English, Spanish\",\"Country\":\"United States, Germany\",\"Awards\":\"11 wins & 18 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"6.8/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"54%\"},{\"Source\":\"Metacritic\",\"Value\":\"58/100\"}],\"Metascore\":\"58\",\"imdbRating\":\"6.8\",\"imdbVotes\":\"413,784\",\"imdbID\":\"tt0232500\",\"Type\":\"movie\",\"DVD\":\"10 Sep 2015\",\"BoxOffice\":\"$144,745,925\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";

        // ACT
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        // ASSERT
        assertEquals(itInfo, Cache.getInstance().getCacheMemory().get("Fast"));
        assertEquals(1, Cache.getInstance().getCacheMemory().size());
    }
}