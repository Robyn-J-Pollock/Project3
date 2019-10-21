import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class DateTimeTwo {
	private LocalDate currentDate;
	private HashMap<LocalDate, Integer> datesMap;
	
	private DateTimeTwo() {
		currentDate = LocalDate.now();
		datesMap = new HashMap<LocalDate, Integer>();
	}
	/*
	 * Uses 10th day and 18th day of current month to produce output
	 * in format
	 * "The tenth day of this month is THURSDAY and eighteenth is FRIDAY "
	 */
	public void daysOfCurrentMonth() {
		String tenthDay = currentDate.withDayOfMonth(10).getDayOfWeek().toString().toUpperCase();
		String eighteenthDay = currentDate.withDayOfMonth(18).getDayOfWeek().toString().toUpperCase();
		String output = String.format("The tenth day of this month is %s and eighteenth is %s", tenthDay, eighteenthDay);
		System.out.print(output.toString());
	}
	
	/*
	 * Takes a month and year input, outputs the day of the month
	 * for the 15th and last day of the month in the format
	 * "For the year (2019) and month (10), the fifteenth day is TUESDAY and the last day is THURSDAY"
	 */
	public void daysOfAnyMonth(int month, int year) {
		String fifteenthDay = LocalDate.of(year, month, 15).getDayOfWeek().toString().toUpperCase();
		int intLastDay = LocalDate.of(year, month, 1).lengthOfMonth();
		String lastDay = LocalDate.of(year, month, intLastDay).getDayOfWeek().toString().toUpperCase();
		String output = String.format("For the year (%d) and month (%d), the fifteenth day"
				+ "is %s and the last day is %s", year, month, fifteenthDay, lastDay);
		System.out.print(output.toString());
	}
	
	/*
	 * Reads the text file: Dates.txt
	 * Determines if the read year is a leap year.
	 * Finds the difference from today to the read year
	 * Saves dates in Hashmap of <LocalDate, Integer>
	 * Keys are in the file, values are integers starting from 1
	 * Outputs text in the form
	 * "2017 is not a leap year, and Difference: 2 years, 5 months, and 3 days."
	 */
	public void compareYear() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Dates.txt"));
		String lineRead = br.readLine();
		int x = 0;
		StringBuffer output = new StringBuffer();
		while (lineRead != null)
		{
			x++;
			String[] date = lineRead.split(".");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			LocalDate localDateRead = LocalDate.of(year, month, day);
			String isLeap = "not ";
				if (localDateRead.isLeapYear())
					isLeap = "";
			Period timeDiff = Period.between(currentDate, localDateRead);
			output.append(String.format("%s is %sa leap year, and Difference: %d years, %d months, and %3 days."
					,year, isLeap, timeDiff.getYears(), timeDiff.getMonths(), timeDiff.getDays()));
			datesMap.put(localDateRead, x);
			lineRead = br.readLine();
		}
		
		System.out.print(output);
	}
	
	/*
	 * prints the Hashmap created in compareYear in format
	 * "Key:value
	 *  2010-10-30:6
	 *  2019-05-30:3"
	 */
	public void dateHashMap() {
		Iterator<LocalDate> it = datesMap.keySet().iterator();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		StringBuffer output = new StringBuffer("Key:value");
		while (it.hasNext()) {
			LocalDate currentKey = it.next();
			output.append("/n" + currentKey.format(dtf) + ":" + datesMap.get(currentKey));
		}
		
		System.out.print(output);
	}
	
	/*
	 * Sorts the HashMap created in compareYear() in ascending order using keys.
	 * [Difference in documentation?]
	 * Prints in the format
	 * "1900-12-31:8"
	 */
	public void dateHashMapSorted() {
		Set<LocalDate> dateKeySet = datesMap.keySet();
		LinkedList<LocalDate> dateList = new LinkedList<LocalDate>();
		Iterator<LocalDate> it = dateKeySet.iterator();
		LocalDate nextDate = it.next();
		dateList.add(nextDate);
		while(it.hasNext()) {
			nextDate = it.next();
			if (nextDate.isAfter(dateList.getLast()))
				dateList.add(nextDate);
			else {
				for (int x = dateList.size() - 2; x >= 0; x--) {
					boolean added = false;
					if (nextDate.isAfter(dateList.get(x))) {
						dateList.add(x, nextDate);
						added = true;
					}
					if (added == false)
						dateList.addFirst(nextDate);
				}
			}
		}
		StringBuffer output = new StringBuffer();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (LocalDate date : dateList)
		{
			output.append(date.format(dtf) + ":" + datesMap.get(date));
			if (date != dateList.peekLast())
				output.append("\n");
		}
		System.out.print(output);
	}
}
