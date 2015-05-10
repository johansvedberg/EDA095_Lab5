package multicast;



public class MainServer {

	public static void main(String[] args) {

		Thread offer = new Thread(new MCServerOffer());
		Thread timeserver = new Thread(new TimeServerUDP());
		offer.start();
		timeserver.start();

	}

}
