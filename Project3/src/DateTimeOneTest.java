import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.*;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateTimeOneTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private DateTimeOne dto;
	
	@Before
	public void setUp() {
		dto = new DateTimeOne();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	@Test
	public void testGetValueOfSecond() {
		int actual = dto.getValueOfSecond();
		LocalTime lt = LocalTime.now();
		int expected = lt.getSecond();
		assertEquals(expected, actual);		
	}
	
	@Test
	public void testDateTimeNow() {
		LocalDateTime ldt = LocalDateTime.now();
		dto.dateTimeNow();
		String actual = outContent.toString();
		outContent.reset();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
		String expected = "Current Date/Time: " + ldt.format(dtf);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSleepForFiveSec() {
		try {
			int startTime = LocalTime.now().toSecondOfDay();
			dto.sleepForFiveSec();
			int actual = LocalTime.now().toSecondOfDay();
			int expectedMin = startTime + 5;
			assertTrue(actual >= expectedMin);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDateTimeOfOtherCity() {
		LocalTime lt = LocalTime.now();
		dto.dateTimeOfOtherCity();
		String actual = outContent.toString();
		outContent.reset();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
		String expected = "Time on Server: " + lt.format(dtf) + "\nGMT: " + lt.plusHours(5).format(dtf)
			+ "\nBST (90E): " + lt.plusHours(11).format(dtf) + "\nCST (90W): " + lt.format(dtf);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDateTimeDifferentZone() {
		LocalDateTime ldt = LocalDateTime.now();
		dto.dateTimeDifferentZone();
		String actual = outContent.toString();
		outContent.reset();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		String expected = "GMT: " + ldt.plusHours(5).format(dtf) + "\nBST: "
				+ ldt.plusHours(11).format(dtf) + "\nCST: " + ldt.format(dtf);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTimeZoneHashMap() {
		LocalDateTime ldt = LocalDateTime.now();
		dto.timeZoneHashMap();
		String actual = outContent.toString();
		outContent.reset();
		DateTimeFormatter pStyle1 = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		DateTimeFormatter pStyle5 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String expected = "Print Style 1:\nAST 10/01/2020 19:59\nBST " 
				+ ldt.plusHours(11).format(pStyle1) + "\nCST " + ldt.format(pStyle1)
				+ "\nGMT " + ldt.plusHours(5).format(pStyle1) + "\nZST 11/05/2018 19:59"
				+ "\nPrint Style 3:\n10/01/2020 19:59\n" + ldt.format(pStyle1) 
				+ "\n" + ldt.plusHours(5).format(pStyle1) + "\n" + ldt.plusHours(11).format(pStyle1) + "\n11/05/2018 19:59"
				+ "\nPrint Style 5: Final sorted Array:\n2020-10-01T19:59\n" + ldt.plusHours(11).format(pStyle5) 
				+ "\n" + ldt.plusHours(5).format(pStyle5) + "\n" + ldt.format(pStyle5) + "\n2018-11-05T19:59";
		assertEquals(expected, actual);
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

}
