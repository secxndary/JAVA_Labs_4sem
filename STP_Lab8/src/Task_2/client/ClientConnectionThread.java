package Task_2.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnectionThread extends Thread {
    Client client;
    Socket socket;

    ClientConnectionThread(Client client) {
        this.client = client;
    }

    public void run() {
        try {
            System.out.println("Подключен к серверу " + this.client.serverAddress + " порт: " + this.client.serverPort);
            socket = new Socket(this.client.serverAddress, this.client.serverPort);
            BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (this.client.alive) {
                String message = dis.readLine();
                if (message == null)
                    return;
                System.out.println(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

