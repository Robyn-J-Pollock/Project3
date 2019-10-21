import java.io.IOException;

public class TestRunner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MesoEquivalent meEq = new MesoEquivalent("NRMN");
		meEq.calAsciiEqual();
		System.out.println();
		System.out.println(new MesoAsciiCal(new MesoStation("WOOD")).calAverage());
	}

}
