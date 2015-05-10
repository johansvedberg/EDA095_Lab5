package random;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class TimeServer1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Date date = new Date();
		switch (args[0]) {

		case "date":

			DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
					Locale.ENGLISH);
			String newDate = df.format(date);
			System.out.println(newDate);
			break;

		case "time":

			DateFormat tf = DateFormat.getTimeInstance(DateFormat.FULL,
					Locale.ENGLISH);
			String newTime = tf.format(date);
			System.out.println(newTime);
			break;

		}

	}

}
