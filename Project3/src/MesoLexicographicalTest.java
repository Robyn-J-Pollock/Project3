import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MesoLexicographicalTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private HashMap<String, Integer> textDates;
	
	@Before
	public void setUp() {
		textDates = new HashMap<String, Integer>();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		textDates.put("DDDD", 1);
		textDates.put("CCCC", 3);
		textDates.put("EEEE", 5);
		textDates.put("BBBB", 4);
		textDates.put("AAAA", 2);
	}

	@Test
	public void testSortedMap() {
		MesoLexicographical meLe = new MesoLexicographical(textDates);
		String actual = outContent.toString();
		outContent.reset();
		
		String expected = "AAAA\nBBBB\nCCCC\nDDDD\nEEEE";
		assertEquals(expected, actual);
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
}
