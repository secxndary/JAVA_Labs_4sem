package Task_2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionInitThread extends Thread {
    Server server;
    int port;

    ServerConnectionInitThread(int port, Server server) {
        this.server = server;
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (this.server.alive) {
                Socket socket = serverSocket.accept();
                ServerConnectionThread serverConnectionThread = new ServerConnectionThread(socket, this.server);
                this.server.clients.add(serverConnectionThread);
                this.server.onClientChange(serverConnectionThread);
                serverConnectionThread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
