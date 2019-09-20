package org.elsys.ip;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
	    var socketClient = new SocketClient();
	    socketClient.startConnection("localhost", 8888);

	    var readThread = new Thread(() -> {
			try {
				String receivedLine;
				do {
					receivedLine = socketClient.readLine();
					System.out.println(receivedLine);
				} while (receivedLine != null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		readThread.start();

	    var scanner = new Scanner(System.in);
	    String line;
	    do {
			line = scanner.nextLine();
			socketClient.sendMessage(line);

		} while (!line.equals("exit"));
		readThread.interrupt();
		readThread.join();
		socketClient.stopConnection();
    }
}
