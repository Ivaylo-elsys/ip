package org.elsys.ip;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        var echoServer = new EchoServer();
        echoServer.start(8888);
        echoServer.stop();
    }
}
