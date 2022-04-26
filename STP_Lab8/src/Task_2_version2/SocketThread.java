package Task_2_version2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketThread extends Thread {
    private Socket socket;
    private PrintStream output;
    private BufferedReader input;

    public SocketThread(Socket socket) throws IOException {
        this.socket = socket;
        output = new PrintStream(socket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                String message = input.readLine();
                if (message.equals("stop")) {
                    break;
                }
                System.out.println(socket.getInetAddress().getHostName() + ": " + message);
                send(message);
            }

        }
        catch (IOException e) {
            System.err.println("Disconnect");
        }
        finally
        {
            try {
                System.out.println(socket.getInetAddress() + " disconnected");
                Server.socketThreads.remove(socket);
                socket.close();
                output.close();
                input.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String message) throws IOException {
        for (var socketThread : Server.socketThreads)
            socketThread.output.println(message);
    }
}