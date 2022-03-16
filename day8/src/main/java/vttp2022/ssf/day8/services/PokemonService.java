package vttp2022.ssf.day8.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp2022.ssf.day8.models.Pokemon;

@Service
public class PokemonService {

    private static final String URL = "https://pokeapi.co/api/v2/pokemon/%s";

    public Optional<Pokemon> findPokemon(String name) {
        String toSearch = URL.formatted(name);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try {
            // Throws an exception if status code >= 400
            resp = template.getForEntity(toSearch, String.class);
        } catch (Exception ex) {
            System.err.printf("Exception: %s\n", ex.getMessage());
            return Optional.empty();
        }

        System.out.printf(">>> status: %d\n", resp.getStatusCode());

        if (resp.getStatusCodeValue() >= 400)
            return Optional.empty();

        try { 
            Pokemon pokemon = Pokemon.create(resp.getBody());
            return Optional.of(pokemon);
        // pokemon exception 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
    
}
