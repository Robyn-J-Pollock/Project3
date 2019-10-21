import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MesoEquivalentTest {
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
	public void testCalAsciiEqual() throws IOException {
		MesoEquivalent meEq = new MesoEquivalent("NRMN");
		HashMap<String, Integer> actualHash = meEq.calAsciiEqual();
		String actualPrint = outContent.toString();
		outContent.reset();
		
		String expectedPrint = "{NRMN=79, OKMU=79, STIL=79, JAYX=79, NEWP=79, STUA=79, WATO=79, MRSH=79}";
		assertNotNull(actualHash.get("NRMN"));
		assertNotNull(actualHash.get("OKMU"));
		assertNotNull(actualHash.get("STIL"));
		assertNotNull(actualHash.get("JAYX"));
		assertNotNull(actualHash.get("NEWP"));
		assertNotNull(actualHash.get("STUA"));
		assertNotNull(actualHash.get("WATO"));
		assertNotNull(actualHash.get("MRSH"));
		assertNull(actualHash.get("NOWA"));
		assertEquals(expectedPrint, actualPrint);
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
}
