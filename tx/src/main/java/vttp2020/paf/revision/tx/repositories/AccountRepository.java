package vttp2020.paf.revision.tx.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2020.paf.revision.tx.models.Account;

import static vttp2020.paf.revision.tx.repositories.SQLStatements.*;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<Account> findAccountByAccountId(String acctId) {

        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ACCOUNT_BY_ACCT_ID, acctId);
        if (!rs.next())
            return Optional.empty();

        return Optional.of(Account.populate(rs));
    }

    public boolean deposit(String acctId, Float amount) {
        Optional<Account> opt = findAccountByAccountId(acctId);
        if (opt.isEmpty())
            throw new IllegalArgumentException("Account %s not found".formatted(acctId));
        Account acct = opt.get();
        int updateCount = template.update(SQL_UPDATE_ACCOUNT_BY_ACCT_ID, 
                amount + acct.getBalance(), acctId);
        return updateCount > 0;
    }

    public boolean withdraw(String acctId, Float amount) {
        Optional<Account> opt = findAccountByAccountId(acctId);
        if (opt.isEmpty())
            throw new IllegalArgumentException("Account %s not found".formatted(acctId));
        Account acct = opt.get();
        int updateCount = template.update(SQL_UPDATE_ACCOUNT_BY_ACCT_ID, 
                acct.getBalance() - amount, acctId);
        return updateCount > 0;
    }

}
