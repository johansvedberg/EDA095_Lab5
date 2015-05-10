package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MCServerOffer implements Runnable {
	private DatagramSocket ds;

	public MCServerOffer(DatagramSocket ds) {
		this.ds = ds;
	}

	@Override
	public void run() {

		try {
			MulticastSocket ms = new MulticastSocket(4099);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
			InetAddress local = InetAddress.getLocalHost();
			String ip = local.getHostAddress() + "/" + ds.getLocalPort();
			while (true) {
				byte[] buf = new byte[65536];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ms.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength());
				System.out.println("Received: " + s);
				byte[] buf2 = ip.getBytes();
				DatagramPacket sp = new DatagramPacket(buf2, buf2.length,
						dp.getAddress(), dp.getPort());
				ms.send(sp);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}