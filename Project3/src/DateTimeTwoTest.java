import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateTimeTwoTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private DateTimeTwo dtt;
	private HashMap<Integer, LocalDate> textDates;
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		dtt = new DateTimeTwo();
		textDates.put(1, LocalDate.of(2017, 05, 05));
		textDates.put(2, LocalDate.of(2018, 11, 12));
		textDates.put(3, LocalDate.of(2019, 05, 30));
		textDates.put(4, LocalDate.of(2011, 03, 15));
		textDates.put(5, LocalDate.of(2000, 12, 15));
		textDates.put(6, LocalDate.of(2010, 10, 30));
		textDates.put(7, LocalDate.of(2004, 01, 01));
		textDates.put(8, LocalDate.of(1900, 12, 31));
		textDates.put(9, LocalDate.of(2014, 02, 28));
		textDates.put(10, LocalDate.of(2012, 10, 10));
	}
	
	@Test
	public void testDaysOfCurrentMonth() {
		LocalDate ld = LocalDate.now();
		String tenthDay = ld.withDayOfMonth(10).getDayOfWeek().toString().toUpperCase();
		String etDay = ld.withDayOfMonth(18).getDayOfWeek().toString().toUpperCase();
		String expected = "The tenth day of this month is " + tenthDay + "and eighteenth is " + etDay;
		dtt.daysOfCurrentMonth();
		String actual = outContent.toString();
		outContent.reset();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDaysOfAnyMonth() {
		dtt.daysOfAnyMonth(12, 2012);
		String actual = outContent.toString();
		outContent.reset();
		
		String expected = "For the year (2012) and month (12), the fifteenth day is SATURDAY and the last day is MONDAY";
		assertEquals(expected, actual);
	}
	
	@Test
	public void compareYear() {
		dtt.compareYear();
		String actual = outContent.toString();
		outContent.reset();
		
		StringBuffer expected = new StringBuffer();
		LocalDate today = LocalDate.now();
		for(Integer x = 1;  x < 11; x++)
		{
			Period interval = Period.between(today, textDates.get(x));
			expected.append(textDates.get(x) + " is ")
			if (!textDates.get(x).isLeapYear()) 
				expected.append("not ");
			expected.append("a leap year, and Difference: " + interval.getYears() + " years, " + interval.getMonths() + " months, and " + interval.getDays() +".");
			if (x != 10)
				expected.append("\n");
		}
		assertEquals(expected, actual);
	}
	
	@Test
	public void dateHashMap() {
		
	}
	
	@Test
	public void dateHashMapSorted() {
		
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

}
