package vttp2022.paf.day13bff;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.day13bff.models.BFF;
import vttp2022.paf.day13bff.repositories.BFFRepository;
import vttp2022.paf.day13bff.services.BFFException;
import vttp2022.paf.day13bff.services.BFFService;

@SpringBootTest
class Day13BffApplicationTests {

	@Autowired
	private BFFService bffSvc;

	@Autowired
	private BFFRepository bffRepo;

	private BFF barney;

	public Day13BffApplicationTests() {
		barney = new BFF();
		barney.setName("barney");
		barney.setEmail("barney@gmail.com");
		barney.setPhone("555-12345");
		barney.setStatus("friend");

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.YEAR, 1970);
		barney.setDob(cal.getTime());
	}

	@BeforeEach
	public void setup() {
		bffRepo.insertBff(barney);
	}

	@AfterEach
	public void tearDown() {
		bffRepo.deleteBffByEmail(barney.getEmail());
	}

	@Test
	void insertBarneyShouldFail() {
		try {
			bffSvc.addNewBff(barney);
		} catch (BFFException ex) {
			assertTrue(true);
			return;
		}

		fail("Did not throw BFFException when email exists");
	}
}
