package by.valdaitsevv.main;
import by.valdaitsevv.cars.*;
import by.valdaitsevv.taxipark.TaxiPark;
import by.valdaitsevv.taxitypes.*;
import by.valdaitsevv.xmlparser.StaxStreamProcessor;

import java.util.*;
import javax.xml.validation.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;



public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);  /// сам логгер как статическое поле

    public static void main(String[] args)
    {
        try
        {
            /// подключение логгера
            BasicConfigurator.configure();
            logger.info("Started main");
            logger.warn("Be careful with input data!\n");
//
//
//            /// создание объектов с помощью конструктора без параметров
//            Economy polo = new Economy();
//            Comfort lancer = new Comfort();
//            ComfortPlus M3 = new ComfortPlus();
//            Business mercedes = new Business();
//            Truck transporter = new Truck();
//            Child logan = new Child();
//
//
//            /// создание списка, куда будут записаны все объекты и позже добавится инфа из xml
//            List<Taxi> taxiList = Arrays.asList(polo, lancer, M3, mercedes, transporter, logan);
//
//
//            // списки, куда будут записываться разные данные из xml и потом этими значениями проинициализируем объекты
//            ArrayList<String> taxiNames = new ArrayList<>();
//            ArrayList<String> taxiSpeeds = new ArrayList<>();
//            ArrayList<String> taxiFuels = new ArrayList<>();
//            ArrayList<String> taxiTypes = new ArrayList<>();
//
//
//            /// считываем в списки названий, скоростей, расхода топлива и типов такси инфу из xml (ф-ия из класса)
//            StaxStreamProcessor.addNamesToList("Name", taxiNames);
//            StaxStreamProcessor.addNamesToList("Speed", taxiSpeeds);
//            StaxStreamProcessor.addNamesToList("Fuel", taxiFuels);
//            StaxStreamProcessor.addNamesToList("Type", taxiTypes);
//
//
//            /// вводим через цикл в объекты машин из списка инфу из списков с инфой о скорости, названии, etc.
//            for (int i = 0; i < taxiList.size(); i++)
//            {
//                taxiList.get(i).setName(taxiNames.get(i));
//                taxiList.get(i).setSpeed(Integer.parseInt(taxiSpeeds.get(i)));
//                taxiList.get(i).setFuelRate(Double.parseDouble(taxiFuels.get(i)));
//                taxiList.get(i).ride(Math.random() * 10);  /// применяем метод ride на рандомнео число от 0 до 10
//            }
//
//
//            /// выводим на экран проинициализированные машины
//            System.out.println("\n\nПроиницализированные XML-документом машины:");
//            for (Taxi taxi: taxiList)
//                System.out.println("Название:  " + taxi.getName() + "\nРасход:    " + taxi.getFuelRate() +
//                        " л/км\nСкорость:  " + taxi.getSpeed() + " км/ч\nТип такси: " + taxi.getType() + "\n");
//
//
//            /// создание таксопарка
//            TaxiPark yandex = new TaxiPark();
//            yandex.setTaxiList(taxiList);
//            System.out.println("\n\nМашины в таксопарке: ");
//            for (Taxi t: yandex.getTaxiList())
//                System.out.println(t.getName());
//
//
//            /// создание менеджера и вызов его методов
//            TaxiPark.Manager hudaverdyan = yandex.new Manager();
//            String genProfit = String.format("%.3f", hudaverdyan.getGeneralProfit());   /// форматированный вывод после запятой
//            System.out.println("\nОбщая прибыль за прошедший час без вычета зарплаты: " + genProfit + " р.");
//            String companyProfit = String.format("%.3f", hudaverdyan.getCompanyProfit());
//            System.out.println("Общая прибыль за прошедший час с вычетом зарплаты:  " + companyProfit + " р.");
//            logger.debug("Debug method getCompanyProfit() to understand it");
//            hudaverdyan.searchSpeed(170, 220);
//            logger.error("This method can cause an error");
//            hudaverdyan.sortFuel();
//
//
//            /// сериализация в JSON лежит в проекте STP_Lab4_XMLTest, т.к. Maven-библиотеки сюда криво подключаются
//
//
//            /// валидация xml с xsd
//            File schemaFile = new File("files/validation.xsd");
//            Source xmlFile = new StreamSource(new File("files/taxilist.xml"));
//            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); // нужно для считывания схемы
//            Schema schema = schemaFactory.newSchema(schemaFile);    // здесь лежит прочитанная и понятная джаве сама xsd-схема
//            Validator validator = schema.newValidator();            // запуск xsd-валидатора из javax.xml.validation.Validator
//            validator.validate(xmlFile);                            // сама валидация
//            System.out.println("\nXML-файл успешно прошёл валидацию с XSD!");
//
//
//            /// завершение программы
//            logger.info("You successfully ran this program!");


            /// чтобы запустить в коммандной строке:
            // java -jar "C:\Users\valda\source\repos\semester #4\STP_Labs\STP_Lab3\out\artifacts\STP_Lab4_testXML_jar\STP_Lab4_testXML.jar"







              // КОД НИЖЕ — ЭТО ПОЛНОЕ РЕШЕНИЕ 3-ЕЙ ЛАБЫ.
              // ПРОСТО РАСКОММЕНТИРУЙТЕ ТО ЧТО НАПИСАНО НИЖЕ И ЗАКОММЕНТИРУЙТЕ ТО ЧТО ВЫШЕ ДЛЯ 3-ЕЙ ЛАБЫ

            /// инициализация такси разных типов и вызов метода ride()
            Economy polo = new Economy("Volkswagen Polo", 0.11, 130);
            polo.ride(3);
            polo.ride(2.4);

            Comfort lancer = new Comfort("Mitsubishi Lancer", 0.13, 190);
            lancer.ride(5.3);

            ComfortPlus M3 = new ComfortPlus("BMW M3", 0.15, 200);
            M3.ride(5.2);

            Business mercedes = new Business("Mercedes SLS AMG", 0.19, 220);
            mercedes.ride(4.2);
            mercedes.ride(5.7);

            Truck transporter = new Truck("Volkswagen Transporter", 0.25, 120);
            transporter.ride(8);

            Child logan = new Child("Renault Logan", 0.12, 110);
            logan.ride(2.5);
            logger.debug("Check all input data...");



            /// создание таксопарка
            TaxiPark yandex = new TaxiPark();
            yandex.add(polo);
            yandex.add(lancer);
            yandex.add(M3);
            yandex.add(mercedes);
            yandex.add(transporter);
            yandex.add(logan);
            System.out.println("\nМашины в таксопарке: ");
            for (Taxi t: yandex.getTaxiList())
                System.out.println(t.getName());



            /// создание менеджера и вызов его методов
            TaxiPark.Manager hudaverdyan = yandex.new Manager();
            String genProfit = String.format("%.3f", hudaverdyan.getGeneralProfit());   /// форматированный вывод после запятой
            System.out.println("\nОбщая прибыль за прошедший час без вычета зарплаты: " + genProfit + " р.");
            String companyProfit = String.format("%.3f", hudaverdyan.getCompanyProfit());
            System.out.println("Общая прибыль за прошедший час с вычетом зарплаты:  " + companyProfit + " р.");
            logger.debug("Debug method getCompanyProfit() to understand it");
            hudaverdyan.searchSpeed(170, 220);
            logger.error("An error has occured with searchSpeed() method");
            hudaverdyan.sortFuel();
            logger.info("You successfully ran this program!");
        }
        catch (Exception e)
        {
            logger.fatal("Fatal error! " + e.getMessage());
        }
    }
}
