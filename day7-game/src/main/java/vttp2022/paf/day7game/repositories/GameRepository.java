package vttp2022.paf.day7game.repositories;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.paf.day7game.models.Game;

@Repository
public class GameRepository {

    @Autowired @Qualifier("games")
    private RedisTemplate<String, String> template;

    public Set<String> getKeys(String key) {
        return template.keys(key);
    }

    public Optional<Game> getGameByGid(String gid) {
        String rec = (String)template.opsForHash().get(gid, "rec");
        if (null != rec)
            return Optional.of(Game.create(rec));
        return Optional.empty();
    }
    
}
