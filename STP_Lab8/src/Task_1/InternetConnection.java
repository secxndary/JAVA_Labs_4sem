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
            // так можно получить адрес локального хоста (ваш айпи епта)
            var currentLocalHostIP = InetAddress.getLocalHost();
            System.out.println("Local host IP:            " + currentLocalHostIP.getHostAddress());
        }
        catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void getByName(String web) {
        try {
            // получить айпишник по имени сайта (dns вся хуйня)
            var pageIP = InetAddress.getByName(web);
            System.out.println("BSTU IP by name:          " + pageIP.getHostAddress());
        }
        catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void getByAddress(String name, byte[] ip) {
        try {
            // и по адресу тоже можно получить. проверяем, доступен ли 127.0.0.1 (спойлер: ясен хуй что да)
            var pageIP = InetAddress.getByAddress(name, ip);
            System.out.println("Is 127.0.0.1 reachable?   " + pageIP.isReachable(300));
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void readHTML(String urlName) {
        try {       // еще можно весь штмл считать через команды
            // в url закидываем адрес сайта
            var url = new URL(urlName);
            // через буферизированный поток все читаем построчно
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


    // можно еще всякую разную инфу о соединении получить
    public void getInfo(String urlName) {
        var timeout = 10_000;
        try {
            var url = new URL(urlName);
            // создали объект коннекшена к какому то сайту из параметров функции
            final var connection = url.openConnection();

            System.out.println("Content type:     " + connection.getContentType() +     // тип контента (штмл страница)
                             "\nClass:            " + connection.getClass() +           // класс
                             "\nContent length:   " + connection.getContentLength());   // длина контента
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}























