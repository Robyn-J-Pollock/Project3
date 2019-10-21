import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class DateSortingUsingAlgorithm {
	private HashMap<Integer, LocalDate> datesMap;
	
	public DateSortingUsingAlgorithm() throws IOException {
		datesMap = new HashMap<Integer, LocalDate>();
		hashMapCreator();
	}
	
	public void hashMapCreator() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("SortingDates.txt"));
		String nextLine = br.readLine();
		Integer x = 0;
		while(nextLine != null) {
			nextLine = nextLine.trim();
			String[] splitLine = nextLine.split("-");
			int year = Integer.parseInt(splitLine[0].trim());
			int month = Integer.parseInt(splitLine[1].trim());
			int day = Integer.parseInt(splitLine[2].trim());
			
			LocalDate tempDate = LocalDate.of(year, month, day);
			datesMap.put(x, tempDate);
			
			nextLine = br.readLine();
			x++;
		}
		
		br.close();
	}
	
	public void dateHashMapSortedDescending() {
		LinkedList<LocalDate> decOrderDates = new LinkedList<LocalDate>();
		decOrderDates.add(datesMap.get(0));
		for(Integer x = 1; x < datesMap.size(); x++) {
			if (datesMap.get(x).isBefore(decOrderDates.peekLast())) {
				decOrderDates.add(datesMap.get(x));
			}
			else {
				boolean added = false;
				for (int elseInt = decOrderDates.size() - 1; elseInt >= 0; elseInt--) {
					if (datesMap.get(x).isBefore(decOrderDates.get(elseInt))) {
						decOrderDates.add(elseInt + 1, datesMap.get(x));
						added = true;
						break;
					}
				}
				if (!added)
					decOrderDates.addFirst(datesMap.get(x));
			}
		}
		
		StringBuffer output = new StringBuffer();
		Iterator<LocalDate> it = decOrderDates.iterator();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		while (it.hasNext()) {
			output.append(it.next().format(dtf));
			if (it.hasNext())
				output.append("\n");
		}
		
		System.out.print(output);
	}
	
	public void dateHashMapSorted() {
		LinkedList<LocalDate> decOrderDates = new LinkedList<LocalDate>();
		decOrderDates.add(datesMap.get(0));
		for(Integer x = 1; x < datesMap.size(); x++) {
			if (datesMap.get(x).isAfter(decOrderDates.peekLast())) {
				decOrderDates.add(datesMap.get(x));
			}
			else {
				boolean added = false;
				for (int elseInt = decOrderDates.size() - 1; elseInt >= 0; elseInt--) {
					if (datesMap.get(x).isAfter(decOrderDates.get(elseInt))) {
						decOrderDates.add(elseInt + 1, datesMap.get(x));
						added = true;
						break;
					}
				}
				if (!added)
					decOrderDates.addFirst(datesMap.get(x));
			}
		}
		
		StringBuffer output = new StringBuffer();
		Iterator<LocalDate> it = decOrderDates.iterator();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		while (it.hasNext()) {
			output.append(it.next().format(dtf));
			if (it.hasNext())
				output.append("\n");
		}
		
		System.out.print(output);
	}
}
