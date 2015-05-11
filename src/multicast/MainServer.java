package multicast;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class MainServer {

	public static void main(String[] args) {

		try {
			Random r = new Random();
			int serverport = 30000 + r.nextInt(100);
			DatagramSocket ds = new DatagramSocket(serverport);
			Thread offer = new Thread(new MCServerOffer(ds));
			Thread timeserver = new Thread(new TimeServerUDP(ds));
			offer.start();
			timeserver.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

}
