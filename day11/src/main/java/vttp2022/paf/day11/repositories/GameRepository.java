package vttp2022.paf.day11.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.day11.models.Comment;
import vttp2022.paf.day11.models.Game;

import static vttp2022.paf.day11.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Comment> getCommentsByGid(Integer gid) {
        return getCommentsByGid(gid, Integer.MAX_VALUE, 0);
    }
    public List<Comment> getCommentsByGid(Integer gid, Integer limit) {
        return getCommentsByGid(gid, limit, 0);
    }
    public List<Comment> getCommentsByGid(Integer gid, Integer limit, Integer offset) {

        final List<Comment> comments = new LinkedList<>();

        final SqlRowSet result = template.queryForRowSet(
            SQL_SELECT_COMMENT_BY_GID, gid, limit, offset
        );

        while (result.next()) {
            Comment comment = Comment.create(result);
            comments.add(comment);
        }

        return comments;
    }
    
    public Optional<Game> getGameByGid(Integer queryGid) {
        final SqlRowSet result = template.queryForRowSet(
            // select * from game where gid = <gid>
            SQL_SELECT_GAME_BY_GID, queryGid
        );

        if (!result.next())
            return Optional.empty();

        return Optional.of(Game.create(result));
    }
    
}
