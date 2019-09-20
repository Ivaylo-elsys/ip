package org.elsys.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException, InterruptedException {
        serverSocket = new ServerSocket(port);
        int clientsConnected = 0;
        List<EchoClientHandler> handlers = new ArrayList<>();
        while (clientsConnected < 2) {
            var clientSocket = serverSocket.accept();
            var handler = new EchoClientHandler(clientSocket, clientsConnected);
            handler.start();
            handlers.add(handler);
            clientsConnected++;
        }

        for (EchoClientHandler handler : handlers) {
            handler.join();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private int index;

        public EchoClientHandler(Socket socket, int index) {
            this.clientSocket = socket;
            this.index = index;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if ("exit".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    System.out.println(inputLine);
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}