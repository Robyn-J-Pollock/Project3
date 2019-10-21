
public class MesoAsciiCal extends MesoAsciiAbstract 
{
	private char[] stID;
	
	public MesoAsciiCal(MesoStation mesoStat) {
		stID = mesoStat.getStID().toCharArray();
	}
	
	
	
	@Override
	int calAverage() {
		double firstDouAvgVal = 0;
		double secondDouAvgVal = 79;
		for (char ch : stID) {
			firstDouAvgVal += (int) ch;
		}
		firstDouAvgVal = firstDouAvgVal/stID.length;
		int firstIntAvgVal = (int) firstDouAvgVal;
		if (firstDouAvgVal - firstIntAvgVal >= 0.25)
			firstIntAvgVal++;
		
		double finalDouAvg = (firstDouAvgVal + secondDouAvgVal) / 2;
		int finalIntAvg = (int) finalDouAvg;
		if (finalDouAvg - finalIntAvg != 0)
			finalIntAvg++;
			
		return finalIntAvg;
	}
   
}