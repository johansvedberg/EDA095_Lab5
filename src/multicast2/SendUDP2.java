package multicast2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class SendUDP2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String command = scan.nextLine();
		byte[] buf = new byte[1024];
		InetSocketAddress server = new InetSocketAddress("localhost", 30000);
		DatagramPacket send = new DatagramPacket(command.getBytes(),
				command.getBytes().length, server.getAddress(),
				server.getPort());
		try {
			DatagramSocket ds = new DatagramSocket();
			ds.send(send);
			DatagramPacket receive = new DatagramPacket(buf, buf.length);
			ds.receive(receive);
			String data = new String(receive.getData());
			System.out.println(data);
		} catch (SocketException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
