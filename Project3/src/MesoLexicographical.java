import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MesoLexicographical extends MesoSortedAbstract
{
	private TreeMap<String, Integer> newMap;
	public MesoLexicographical(HashMap<String, Integer> hashMap) {
		newMap = new TreeMap<String, Integer>();
		sortedMap(hashMap);
	}

	/*
	 * Creates and prints a sorted HashMap in alphabetical order of the form 
	 * "NRMN
	 *  OKMU
	 *  etc"
	 */
	@Override
	Map<String, Integer> sortedMap(HashMap<String, Integer> unsorted) {
		// TODO Auto-generated method stub
		for (String key : unsorted.keySet()) {
			newMap.put(key, unsorted.get(key));
		}
		
		StringBuffer output = new StringBuffer();
		Iterator<String> it = newMap.keySet().iterator();
		while (it.hasNext()) {
			String next = it.next();
			output.append(next + " " + newMap.get(next));
			if (it.hasNext())
				output.append("\n");
		}
		
		System.out.print(output.toString());
			
		return newMap;
	}

}