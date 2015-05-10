package multicast;

import java.net.*;
import java.io.*;

public class MCSender {
	private static MulticastSocket ms;
	private static InetAddress ia;

	public static void main(String args[]) {
		try {
			ms = new MulticastSocket();
			ms.setTimeToLive(1);
			ia = InetAddress.getByName("experiment.mcast.net");
			while (true) {
				String data = getIP();
				String[] stuff = data.split("/");
				String inet = stuff[0];
				int port = Integer.valueOf(stuff[1]);
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

				System.out.println("Received data from: " + data);
				byte[] buf3 = new byte[1024];
				InetSocketAddress server = new InetSocketAddress(inet, port);
				DatagramPacket send = new DatagramPacket(buf, buf.length,
						server.getAddress(), server.getPort());

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
		String request = "Anyone here?";
		DatagramPacket dp = new DatagramPacket(request.getBytes(),
				request.getBytes().length, ia, 4099);
		try {
			ms.send(dp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] buf = new byte[65536];
		DatagramPacket rp = new DatagramPacket(buf, buf.length);
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
