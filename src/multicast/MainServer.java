package multicast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MainServer {

	public static void main(String[] args) {
		MulticastSocket ms;
		try {
			ms = new MulticastSocket(4099);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
			Thread offer = new Thread(new MCServerOffer(ms));
			Thread timeserver = new Thread(new TimeServerUDP());
			offer.start();
			timeserver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
