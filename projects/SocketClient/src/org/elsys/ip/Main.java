package org.elsys.ip;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    var socketClient = new SocketClient();
	    socketClient.startConnection("localhost", 8888);

	    var response = socketClient.sendMessage("Hello world!!!");
	    System.out.println(response);

		response = socketClient.sendMessage("Hello 12A");
		System.out.println(response);

    }
}
