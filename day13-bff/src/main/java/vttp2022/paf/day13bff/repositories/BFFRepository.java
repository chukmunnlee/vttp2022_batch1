package vttp2022.paf.day13bff.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.day13bff.models.BFF;

import static vttp2022.paf.day13bff.models.ConversionUtils.*;
import static vttp2022.paf.day13bff.repositories.Queries.*;

@Repository
public class BFFRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<BFF> findBffByEmail(String email) {
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_BFF_BY_EMAIL, email);
        if (!rs.next())
            return Optional.empty();

        return Optional.of(convert(rs));
    }

    public boolean insertBff(BFF bff) {
        // (email, name, phone, status pass_phrase, dob) 
        int count = template.update(SQL_INSERT_BFF, bff.getEmail(), bff.getName()
                , bff.getPhone(), bff.getStatus(), bff.getPassphrase(), bff.getDob());
        return 1 == count;
    }

    public boolean deleteBffByEmail(String email) {
        int count = template.update(SQL_DELETE_BFF_BY_EMAIL, email);
        return 1 == count;
    }

    public List<BFF> selectAllBffs() {
        List<BFF> bffs = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_BFF);
        while (rs.next()) {
            BFF bff = convert(rs);
            bffs.add(bff);
        }
        return bffs;
    }
}
