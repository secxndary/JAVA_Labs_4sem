package Task_2.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerConnectionThread extends Thread {
    boolean connected = true;
    Socket socket;
    Server server;

    public ServerConnectionThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        System.out.println("Ожидание сообщения от клиента (" + this + ")");
        while (this.server.alive) {
            try {
                BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = dis.readLine();
                if (message == null) {
                    server.clients.remove(this);
                    this.connected = false;
                    this.server.onClientChange(this);
                    return;
                } else {
                    server.onClientMessage(this, message);
                }
            } catch (IOException e) {
                server.clients.remove(this);
                this.connected = false;
                this.server.onClientChange(this);
                return;
            }
        }
    }

    public String toString() {
        return "Клиент: " + this.socket.getInetAddress().toString() +
                " порт: " + this.socket.getPort() + " подключен: " + this.connected;
    }

    public void sendMessage(String message) {
        try {
            PrintStream ps = new PrintStream(this.socket.getOutputStream());
            ps.println(message);
            ps.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
