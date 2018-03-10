import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeStampTest {

	public static void main(String[] args) {

		
		
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

		String d = dateFormatter.format(date);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date1 = new Date();
		SimpleDateFormat dateFormatter1 = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

		String d1 = dateFormatter.format(date1);
		
		System.out.println(d);
		System.out.println(d1);
		
		boolean b = date1.after(date);
		System.out.println(b);


	}

}
