package vttp2020.paf.revision.tx.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Account {
    private String accountId;
    private Float balance;

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public static Account populate(SqlRowSet rs) {
        Account acct = new Account();
        acct.setAccountId(rs.getString("acct_id"));
        acct.setBalance(rs.getFloat("balance"));
        return acct;
    }
}
