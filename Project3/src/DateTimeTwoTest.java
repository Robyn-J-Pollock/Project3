import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
	public void compareYear() throws IOException {
		dtt.compareYear();
		String actual = outContent.toString();
		outContent.reset();
		
		StringBuffer expected = new StringBuffer();
		LocalDate today = LocalDate.now();
		for(Integer x = 1;  x < 11; x++)
		{
			Period interval = Period.between(today, textDates.get(x));
			expected.append(textDates.get(x) + " is ");
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
		dtt.dateHashMap();
		String actual = outContent.toString();
		outContent.reset();
		
		StringBuffer expected = new StringBuffer();
		Iterator<Integer> it = textDates.keySet().iterator();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		expected.append("Key:value");
		while (it.hasNext())
		{
			Integer keyValue = it.next();
			expected.append("\n" + textDates.get(keyValue).format(dtf) + ":" + keyValue);
		}
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void dateHashMapSorted() {
		dtt.dateHashMapSorted();
		String actual = outContent.toString();
		outContent.reset();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		StringBuffer expected = new StringBuffer();
		expected.append(textDates.get(8).format(dtf) + ":8\n");
		expected.append(textDates.get(5).format(dtf) + ":5\n");
		expected.append(textDates.get(7).format(dtf) + ":7\n");
		expected.append(textDates.get(6).format(dtf) + ":6\n");
		expected.append(textDates.get(4).format(dtf) + ":4\n");
		expected.append(textDates.get(10).format(dtf) + ":10\n");
		expected.append(textDates.get(9).format(dtf) + ":9\n");
		expected.append(textDates.get(1).format(dtf) + ":1\n");
		expected.append(textDates.get(2).format(dtf) + ":2\n");
		expected.append(textDates.get(3).format(dtf) + ":3");
		
		assertEquals(expected, actual);
		
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

}
