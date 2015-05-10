package multicast;

import java.net.*;
import java.io.*;

public class MCSender {
	private static MulticastSocket ms;

	public static void main(String args[]) {
		try {
			ms = new MulticastSocket();
			ms.setTimeToLive(1);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			while (true) {
				int ch;
				String s = new String();
				do {
					ch = System.in.read();
					if (ch != '\n') {
						s = s + (char) ch;
					}
				} while (ch != '\n');
				System.out.println("Sending message: " + s);
				byte[] buf = s.getBytes();
				DatagramPacket dp = new DatagramPacket(buf, buf.length, ia,
						4099);
				ms.send(dp);
				
				String data = getIP();
				
				System.out.println("Received data from: " + data);
				byte[] buf3 = new byte[1024];
				InetSocketAddress server = new InetSocketAddress(data, 30000);
				DatagramPacket send = new DatagramPacket(s.getBytes(),
						s.getBytes().length, server.getAddress(),
						server.getPort());

				DatagramSocket ds = new DatagramSocket();
				ds.send(send);
				DatagramPacket receive = new DatagramPacket(buf3, buf3.length);
				ds.receive(receive);
				String data2 = new String(receive.getData());
				System.out.println(data2);
			}
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}

	private static String getIP() {
		byte[] buf2 = new byte[65536];
		DatagramPacket rp = new DatagramPacket(buf2, buf2.length);
		try {
			ms.receive(rp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = new String(rp.getData(), 0, rp.getLength());
		return data;
	}

}
