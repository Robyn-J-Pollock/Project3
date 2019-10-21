import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateSortingUsingAlgorithmTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@Test
	public void testDateHashMapSortedDescending() throws IOException {
		DateSortingUsingAlgorithm dsua = new DateSortingUsingAlgorithm();
		dsua.dateHashMapSortedDescending();
		String actual = outContent.toString();
		outContent.reset();
		
		String expected = "2050-12-31\n2047-02-28\n2042-10-30\n2038-02-02\n2026-05-06\n" + 
				"2021-03-31\n2020-12-31\n2020-01-01\n2019-05-30\n2018-11-12\n2018-01-01\n" +
				"2017-05-05\n2014-02-28\n2012-10-10\n2011-03-15\n2010-11-01\n2010-10-30\n" +
				"2004-01-01\n2003-04-25\n2000-12-15";
				
		assertEquals(expected, actual);
	}

	@Test
	public void testDateHashMapSorted() throws IOException {
		DateSortingUsingAlgorithm dsua = new DateSortingUsingAlgorithm();
		dsua.dateHashMapSorted();
		String actual = outContent.toString();
		outContent.reset();
		
		String expected = "2000-12-15\n2003-04-25\n2004-01-01\n2010-10-30\n2010-11-01\n"
				+ "2011-03-15\n2012-10-10\n2014-02-28\n2017-05-05\n2018-01-01\n2018-11-12\n"
				+ "2019-05-30\n2020-01-01\n2020-12-31\n2021-03-31\n2026-05-06\n2038-02-02\n"
				+ "2042-10-30\n2047-02-28\n2050-12-31";
				
		assertEquals(expected, actual);
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
}
