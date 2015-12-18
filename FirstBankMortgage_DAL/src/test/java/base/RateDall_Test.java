package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class RateDall_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTables() {
		ArrayList<RateDomainModel> rows = RateDAL.getTable();
		 
		// Assert that there are more than 0 rows in table
		System.out.print(rows);
		assertTrue(rows.size() > 0);
		
		// Assert that a row has RateID, MinCreditScore, and InterestRate
		assertNotNull(rows.get(0).getRateID());
		assertNotNull(rows.get(0).getMinCreditScore());
		assertNotNull(rows.get(0).getInterestRate());
		
	}

	@Test
	public void testValues() {
		/** 			MinCreditScore | InterestRate
						600-649					5
						650-699					4.5
						700-749					4
						750-799					3.75
						800+					3.5
		**/
		
		// Examples
		assertTrue(RateDAL.getRate(601) == 5);
		assertTrue(RateDAL.getRate(651) == 4.5);
		assertTrue(RateDAL.getRate(701) == 4);
		assertTrue(RateDAL.getRate(775) == 3.75);
		assertTrue(RateDAL.getRate(850) == 3.5);

	}
}
