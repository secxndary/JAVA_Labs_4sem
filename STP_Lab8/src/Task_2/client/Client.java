package Task_2.client;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Client {
    public boolean alive = true;
    public String serverAddress;
    public int serverPort;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        ClientConnectionThread clientConnectionThread = new ClientConnectionThread(this);
        clientConnectionThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.nextLine();
            try {
                PrintStream printStream = new PrintStream(clientConnectionThread.socket.getOutputStream());
                printStream.println(cmd);
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
        }
    }
}

