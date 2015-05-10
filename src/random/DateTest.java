package random;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTest {

	public static void main(String[] args) {
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.FRENCH);
		String newDate = df.format(date);
		System.out.println(newDate);

	}

}
