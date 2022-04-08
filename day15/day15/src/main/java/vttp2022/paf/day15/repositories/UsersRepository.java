package vttp2022.paf.day15.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp2022.paf.day15.repositories.Queries.*;

@Repository
public class UsersRepository {

    @Autowired
    private JdbcTemplate template;

    public int countUsersByNameAndPassword(String username, String password) {
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_AND_COUNT_USERS_BY_NAME, username, password);
        if (!rs.next())
            return 0;
        return rs.getInt("user_count");
    }
    
}
