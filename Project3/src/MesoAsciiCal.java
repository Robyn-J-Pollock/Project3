
public class MesoAsciiCal extends MesoAsciiAbstract 
{
	private char[] stID;
	
	public MesoAsciiCal(MesoStation mesoStat) {
		stID = mesoStat.getStID().toCharArray();
	}
	
	
	
	@Override
	int calAverage() {
		double douAvgVal = 0;
		for (char ch : stID)
			douAvgVal = (int) ch;
		douAvgVal = douAvgVal/stID.length;
		int intAvgVal = (int) douAvgVal;
		if (douAvgVal - (int) douAvgVal >= 0.5)
			intAvgVal++;
			
		return intAvgVal;
	}
   
}