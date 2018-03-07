import java.util.Date;
import java.util.Scanner;

public class TimeStampTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date date = new Date();
		System.out.println(date);
		
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		Date d = new Date(s);
		
		System.out.println(d.getHours());
		
	}

}
