package vttp2020.paf.revision.tx;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2020.paf.revision.tx.models.Account;
import vttp2020.paf.revision.tx.repositories.AccountRepository;

@SpringBootTest
class TxApplicationTests {

	@Autowired
	private AccountRepository acctRepo;

	@Test
	void shouldFindFred() {
		Optional<Account> opt = acctRepo.findAccountByAccountId("fred");
		assertTrue(opt.isPresent());
	}

	@Test
	void shouldFindWilma() {
		Optional<Account> opt = acctRepo.findAccountByAccountId("wilma");
		assertTrue(opt.isEmpty());
	}

	@Test
	public void shouldNotBeAbleToDeposit() {
		assertThrows(IllegalArgumentException.class, 
			() -> acctRepo.deposit("wilma", 100f));
	}

	@Test void shouldBeAbleToWithdraw() {
		Float amount = 50f;

		acctRepo.deposit("fred", amount);

		assertTrue(acctRepo.withdraw("fred", amount));
	}

}
