import java.util.HashMap;
import java.util.Map;

public class MesoLexicographical extends MesoSortedAbstract
{
	private Map<String, Integer> newMap;
	public MesoLexicographical(HashMap<String, Integer> hashMap) {
		newMap = sortedMap(hashMap);
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
		return null;
	}

}