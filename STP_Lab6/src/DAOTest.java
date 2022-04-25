import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {
    private static final Logger logger = Logger.getLogger(Main.class);
    private Connection con;         // соединение
    private Statement statement;    // стейтмент для запросов



    @BeforeEach
    void getProperties() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String url = resourceBundle.getString("database.url");
        String user = resourceBundle.getString("database.username");
        String password = resourceBundle.getString("database.password");
        String from = resourceBundle.getString("database.from");
        String to = resourceBundle.getString("database.to");

        List<String> expected = new ArrayList<>();
        expected.add(url);
        expected.add(user);
        expected.add(password);
        expected.add(from);
        expected.add(to);

        DAO dao = new DAO();
        List<String> actual = dao.getProperties();

        Assert.assertEquals(expected, actual);
    }


    @Test
    @Timeout(1000)
    void getConnection() {
        DAO dao = new DAO();
        Boolean checkActual;
        Boolean checkExpected;

        try
        {
            ArrayList<String> propsExpected = dao.getProperties();
            String url = propsExpected.get(0);
            String user = propsExpected.get(1);
            String password = propsExpected.get(2);

            checkExpected = dao.getConnection();
            con = DriverManager.getConnection(url, user, password);
            statement = con.createStatement();
            checkActual = true;
        }
        catch(Exception ex)
        {
            logger.error("Connection failed...");
            System.out.println(ex);
            checkActual = false;
            checkExpected = false;
        }

        Assert.assertEquals(checkActual, checkExpected);

    }


    @Test
    void query1() {
        String expected;
        String actual;

        try
        {
            DAO dao = new DAO();
            ArrayList<String> propsExpected = dao.getProperties();
            String url = propsExpected.get(0);
            String user = propsExpected.get(1);
            String password = propsExpected.get(2);
            con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();


            ResultSet expectedRS = statement.executeQuery(
                    "select Name, Year\n" +
                            "from BOOKS\n" +
                            "where YEAR(Year) between '1995' and '1999'");

            while(expectedRS.next())
            {
                String name = expectedRS.getString(1);
                Date date = expectedRS.getDate(2);
                System.out.println(name + ": " + date);
            }


            expected = expectedRS.toString();
            DAO dao1 = new DAO();
            System.out.println();
            dao1.getConnection();
            actual = dao1.Query1();
        }
        catch (Exception ex)
        {
            System.out.println("Error in query1!");
            System.out.println(ex);
            expected = "";
            actual = "";
        }

    }


    public static boolean isPalindrome(String s) {
        String str = new StringBuilder(s).reverse().toString();
        return s == null ? false : str.equals(s);
    }

    @ParameterizedTest(name = "{index} - {0} is a palindrome")
    @ValueSource(strings = { "12321", "pop" })
    void testPalindrome(String word) {
        assertTrue(isPalindrome(word));
    }


    @Ignore
    void query2() {
        try
        {
            ResultSet resultSet = statement.executeQuery(
                    "select Name, Country\n" +
                            "from AUTHORS");

            while(resultSet.next())
            {
                String name = resultSet.getString(1);
                String country = resultSet.getString(2);
                System.out.println(name + ": " + country);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }




    @AfterEach
    void closeConnection() {
        try
        {
            DAO dao = new DAO();
            ArrayList<String> propsExpected = dao.getProperties();
            String url = propsExpected.get(0);
            String user = propsExpected.get(1);
            String password = propsExpected.get(2);

            con = DriverManager.getConnection(url, user, password);
            con.close();
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

}