package vttp2022.paf.day6cli.respositories;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class GameRepository {

	@Autowired
	@Qualifier("games")
	private RedisTemplate<String, String> redisTemplate;

	public void save(JsonObject obj) {
		Integer gid = obj.getInt("gid");
		redisTemplate.opsForHash().put(
			"%d".formatted(gid), 
			"rec", 
			obj.toString());
	}

	public Set<String> findKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}
}
