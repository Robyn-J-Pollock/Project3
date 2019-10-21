import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class MesoEquivalent {
	public int asciiValue;
	public MesoEquivalent(String stID) {
		asciiValue = new MesoAsciiCal(new MesoStation(stID)).calAverage();
	}
	
	/*
	 * Stores values in and returns a HashMap of all equal Ascii values
	 * Outputs values in Mesonet.txt that have an equal Ascii average in the form
	 * "{NRMN=79, OKMU=79, etc}"
	 */
	public HashMap<String, Integer> calAsciiEqual() throws IOException {
		HashMap<String, Integer> asciiEquals = new HashMap<String, Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader("Mesonet.txt"));
		br.readLine();
		br.readLine();
		String nextLine = br.readLine();
		
		
		while(nextLine != null) {
			String tempStID = nextLine.trim().substring(0, 4);
			int tempVal = new MesoAsciiCal(new MesoStation(tempStID)).calAverage();
			nextLine = br.readLine();
			if (tempVal == asciiValue) {
				asciiEquals.put(tempStID, tempVal);
			}
		}
		
		StringBuffer output = new StringBuffer("{");
		Iterator<String> it = asciiEquals.keySet().iterator();
		while (it.hasNext()) {
			String next = it.next();
			output.append(next + "=" + asciiEquals.get(next));
			if (it.hasNext())
				output.append(", ");
		}
		output.append("}");
		
		System.out.print(output);
		br.close();
		return asciiEquals;
	}
}
