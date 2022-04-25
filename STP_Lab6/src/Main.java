import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // cd "C:\Users\valda\source\repos\semester #4\STP_Labs\STP_Lab6\src"
        // javac -cp "./log4j-1.2.17.jar;" Main.java
        // java -cp "./mssql-jdbc-10.2.0.jre17.jar;./log4j-1.2.17.jar";. Main


        DAO dao = new DAO();
        Scanner sc = new Scanner(System.in);

        dao.logInfo();
        System.out.println("\n\n===========================================\n");
        dao.getConnection();
        System.out.println("\n================  QUERY 1  ================\n");
        dao.Query1();
        System.out.println("\n================  QUERY 2  ================\n");
        dao.Query2();
        System.out.println("\n================  QUERY 3  ================\n");
        System.out.print("Enter number for query: ");
        int input = sc.nextInt();
        dao.Query3(input);
        System.out.println("\n================  QUERY 4  ================\n");
        dao.Query4();
        System.out.println("\n==============  TRANSACTION  ==============\n");
        dao.executeTransaction();
        System.out.println("\n===========================================\n");
        dao.closeConnection();
        System.out.println("\n===========================================\n");


    }
}














