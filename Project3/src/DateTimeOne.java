import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DateTimeOne extends MesoDateTimeOneAbstract
{
	HashMap<String, LocalDateTime> dateTimeMap;
	/*
	 * Returns value of the current second
	 */
	@Override
	int getValueOfSecond() {
		LocalTime lt = LocalTime.now();
		return lt.getSecond();
	}
	
	/*
	 * Prints value of the current time with value
	 * "Current Date/Time: 10/08/2019 03:03 PM"
	 */
	@Override
	void dateTimeNow() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm aa");
		System.out.print("Current Date/Time: " + ldt.format(dtf));
	}
	
	/*
	 * 
	 */
	@Override
	void sleepForFiveSec() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	/*
	 * Prints the current time for other time-zones, in the format
	 * "Time on Server: 15:14
	 *  GMT: 20:14
	 *  BST (90E): 2:14
	 *  CST (90W): 15:14"
	 * With GMT == GMT
	 * BST == GMT + 6
	 * CST == GMT - 5
	 */
	@Override
	void dateTimeOfOtherCity() {
		StringBuffer output = new StringBuffer();
		LocalTime lt = LocalTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
		output.append("Time on Server: " + lt.format(dtf));
		output.append("\nGMT: " + lt.plusHours(5).format(dtf));
		output.append("\nBST: " + lt.plusHours(11).format(dtf));
		output.append("\nCST: " + lt.format(dtf));
		System.out.print(output.toString());
	}

	/*
	 * Stores the values of GMT, BST, and CST in a HashMap
	 * prints them in the format 
	 * "GMT: 10/08/2019 20:26
	 *  BST: 10/09/2019 02:26
	 *  CST: 10/08/2019 15:26"
	 */
	@Override
	void dateTimeDifferentZone() {
		dateTimeMap = new HashMap<String, LocalDateTime>();
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		dateTimeMap.put("GMT", ldt.plusHours(5));
		dateTimeMap.put("BST", ldt.plusHours(11));
		dateTimeMap.put("CST", ldt);
		StringBuffer output = new StringBuffer();
		Iterator<Entry<String, LocalDateTime>> it = dateTimeMap.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, LocalDateTime> pair = it.next();
			output.append(pair.getKey() + ": " + pair.getValue().format(dtf));
			if (it.hasNext())
				output.append("\n");
		}
		System.out.print(output.toString());
	}

	/*
	 * Stores the values of GMT, BST, CST, AST, and ZST in a HashMap
	 * Prints them in the format
	 * "Print Style 1:
		AST 10/01/2020 19:59
		BST 10/09/2019 02:48
		CST 10/08/2019 15:48
		GMT 10/08/2019 20:48
		ZST 11/05/2018 19:59
		Print Style 3:
		10/01/2020 19:59
		10/08/2019 15:48
		10/08/2019 20:48
		10/09/2019 02:48
		11/05/2018 19:59
		Print Style 5: Final sorted Array:
		2020-10-01T19:59
		2019-10-09T02:48
		2019-10-08T20:48
		2019-10-08T15:48
		2018-11-05T19:59"
	 */
	@Override
	void timeZoneHashMap() {
		// TODO Auto-generated method stub
		dateTimeMap = new HashMap<String, LocalDateTime>();
		LocalDateTime ldt = LocalDateTime.now();
		dateTimeMap.put("AST", LocalDateTime.of(2020, 10, 01, 19, 59));
		dateTimeMap.put("BST", ldt.plusHours(11));
		dateTimeMap.put("CST", ldt);
		dateTimeMap.put("GMT", ldt.plusHours(5));
		dateTimeMap.put("ZST", LocalDateTime.of(2018, 11, 05, 19, 59));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		StringBuffer output = new StringBuffer();
		Iterator<Entry<String, LocalDateTime>> it = dateTimeMap.entrySet().iterator();
		output.append("Print Style 1:\n");
		while(it.hasNext()) {
			Entry<String, LocalDateTime> pair = it.next();
			output.append(pair.getKey() + ": " + pair.getValue().format(dtf) + "\n");
		}
		output.append("Print Style 3:\n");
		output.append(dateTimeMap.get("AST").format(dtf) + "\n");
		output.append(dateTimeMap.get("CST").format(dtf) + "\n");
		output.append(dateTimeMap.get("GMT").format(dtf) + "\n");
		output.append(dateTimeMap.get("BST").format(dtf) + "\n");
		output.append(dateTimeMap.get("ZST").format(dtf) + "\n");
		dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm");
		output.append("Print Style 5: Final sorted Array\n");
		output.append(dateTimeMap.get("AST").format(dtf) + "\n");
		output.append(dateTimeMap.get("BST").format(dtf) + "\n");
		output.append(dateTimeMap.get("GMT").format(dtf) + "\n");
		output.append(dateTimeMap.get("CST").format(dtf) + "\n");
		output.append(dateTimeMap.get("ZST").format(dtf) + "\n");
	}
   
}