package vttp2022.paf.day11.models;

import com.mysql.cj.xdevapi.SqlResult;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String commentId;
    private String user;
    private Integer rating;
    private String comment;
    private Integer gameId;

    public String getCommentId() { return commentId; }
    public void setCommentId(String commentId) { this.commentId = commentId; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Integer getGameId() { return gameId; }
    public void setGameId(Integer gameId) { this.gameId = gameId; }

    public static Comment create(SqlRowSet rs) {
        Comment comment = new Comment();
        comment.setCommentId(rs.getString("c_id"));
        comment.setUser(rs.getString("user"));
        comment.setRating(rs.getInt("rating"));
        comment.setComment(rs.getString("c_text"));
        comment.setGameId(rs.getInt("gid"));
        return comment;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("commentId", commentId)
            .add("user", user)
            .add("rating", rating)
            .add("comment", comment)
            .add("gameId", gameId)
            .build();
    }
}
