package Task_2.server;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Server {
    public boolean alive = true;
    public ArrayList<ServerConnectionThread> clients = new ArrayList<>();

    public Server(int port) {
        System.out.println("Запуск сервера: " + port);
        new ServerConnectionInitThread(port, this).start();
    }

    public void onClientChange(ServerConnectionThread clientThread) {
        System.out.println("onClientChange: " + clientThread);
        System.out.println("Active client list:");

        StringBuilder clientsList = new StringBuilder();
        for (int i = 0; i < clients.size(); i++) {
            clientsList.append(i).append(".").append(clients.get(i)).append("\n");
        }

        for (ServerConnectionThread c : clients) {
            try {
                PrintStream printStream = new PrintStream(c.socket.getOutputStream());
                c.sendMessage("Client count = " + clients.size());
                c.sendMessage(clientsList.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(clientsList);
    }

    public void onClientMessage(ServerConnectionThread clientThread, String message) {
        System.out.println("Сообщение от (" + clientThread + "): " + message);
        if (message.startsWith("send:")) {
            int messagePos = message.indexOf(":", 5);
            int clientIndex = Integer.parseInt(message.substring(5, messagePos));
            clients.get(clientIndex).sendMessage(message.substring(messagePos + 1));
        }
    }
}
