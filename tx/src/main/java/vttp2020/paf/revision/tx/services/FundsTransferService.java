package vttp2020.paf.revision.tx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2020.paf.revision.tx.repositories.AccountRepository;

@Service
public class FundsTransferService {

    @Autowired
    private AccountRepository acctRepo;

    @Transactional
    public void transfer(String srcAcct, String destAcct, Float amount) {

        try {
            acctRepo.withdraw(srcAcct, amount);

            if (amount > 100)
                throw new IllegalArgumentException("You cannot transfer more that $100");

            acctRepo.deposit(destAcct, amount);
        } catch (Exception ex) {
            ex.printStackTrace();
            // if it throws ex, then the transaction will rollback
            throw ex;
        }

        // if it exits here, then the transaction will commit
    }
    
}
