package Task_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class InternetConnection {
    public void getLocalHost() {
        try {
            var currentLocalHostIP = InetAddress.getLocalHost();
            System.out.println("Local host IP:            " + currentLocalHostIP.getHostAddress());
        }
        catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getByName(String web) {
        try {
            var pageIP = InetAddress.getByName(web);
            System.out.println("BSTU IP by name:          " + pageIP.getHostAddress());
        }
        catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getByAddress(String name, byte[] ip) {
        try {
            var pageIP = InetAddress.getByAddress(name, ip);
            System.out.println("Is 127.0.0.1 reachable?   " + pageIP.isReachable(300));
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readHTML(String urlName) {
        try {
            var url = new URL(urlName);
            try (var reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String HTMLLine;
                while ((HTMLLine = reader.readLine()) != null) {
                    System.out.println(HTMLLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public void getInfo(String urlName) {
        var timeout = 10_000;
        try {
            var url = new URL(urlName);
            final var connection = url.openConnection();

            System.out.println("Content type:     " + connection.getContentType() +
                             "\nClass:            " + connection.getClass() +
                             "\nContent length:   " + connection.getContentLength());
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}























