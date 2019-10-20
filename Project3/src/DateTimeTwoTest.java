import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateTimeTwoTest {

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
	public void testDaysOfCurrentMonth() {
		
	}
	
	@Test
	public void testDaysOfAnyMonth() {
		
	}
	
	@Test
	public void compareYear() {
		
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
