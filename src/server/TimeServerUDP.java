package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeServerUDP {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(30000);
			System.out.println("Server online...");

			while (true) {
				byte[] buf = new byte[1024];
				DatagramPacket receive = new DatagramPacket(buf, buf.length);
				ds.receive(receive);

				InetAddress address = receive.getAddress();
				int port = receive.getPort();
				System.out.println("Packet received, printing information:");
				System.out.println("IP: " + address);
				System.out.println("Port: " + port);

				Date date = new Date();
				String data = new String(receive.getData(), 0, 4);
				switch (data) {

				case "date":

					DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
							Locale.ENGLISH);
					String newDate = df.format(date);
					data = newDate;

					break;

				case "time":

					DateFormat tf = DateFormat.getTimeInstance(DateFormat.FULL,
							Locale.ENGLISH);
					String newTime = tf.format(date);
					data = newTime;

					break;

				default:

					data = "Error, invalid command: " + data;

				}

				DatagramPacket send = new DatagramPacket(data.getBytes(),
						data.getBytes().length, address, port);
				System.out.println("Sending requested information...");
				ds.send(send);
				System.out.println("------------------");
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
