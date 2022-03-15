package vttp2022.paf.day7game.models;

import java.io.ByteArrayInputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Game {
    private Integer gameId;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("gid", gameId)
            .add("name", name)
            .add("year", year)
            .add("ranking", ranking)
            .add("users_rated", usersRated)
            .add("url", url)
            .add("image", image)
            .build();
    }

    public static Game create(String rec) {
        JsonReader r = Json.createReader(new ByteArrayInputStream(rec.getBytes()));
        JsonObject o = r.readObject();
        Game g = new Game();
        g.setGameId(o.getInt("gid"));
        g.setName(o.getString("name"));
        g.setImage(o.getString("image"));
        g.setRanking(o.getInt("ranking"));
        g.setUsersRated(o.getInt("users_rated"));
        g.setYear(o.getInt("year"));
        g.setUrl(o.getString("url"));
        return g;
    }
}
