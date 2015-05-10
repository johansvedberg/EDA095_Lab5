package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MCServerOffer implements Runnable {
	private MulticastSocket ms;

	public MCServerOffer(MulticastSocket ms) {

		this.ms = ms;

	}

	@Override
	public void run() {
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();
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