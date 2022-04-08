package vttp2022.paf.day15.repositories;

public interface Queries {
    public static final String SQL_SELECT_AND_COUNT_USERS_BY_NAME =
        "select count(*) as user_count from users where username = ? and password = sha1(?)";
}
