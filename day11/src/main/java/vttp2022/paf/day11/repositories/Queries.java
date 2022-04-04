package vttp2022.paf.day11.repositories;

public interface Queries {

    public static final String SQL_SELECT_GAME_BY_GID = "select * from game where gid = ?";

    public static final String SQL_SELECT_COMMENT_BY_GID 
        = "select * from comment where gid = ? limit ? offset ?";
    
}
