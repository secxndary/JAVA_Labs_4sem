import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DAO implements IConnection, IQuery
{
    private String url;             // url основной бд
    private String user;            // юзер для подключения
    private String password;        // его пароль
    private String from;            // url бд для транзакции (откуда)
    private String to;              // url бд для транзакции (куда)
    private Connection con;         // соединение
    private Statement statement;    // стейтмент для запросов
    private static final Logger logger = Logger.getLogger(Main.class);


    // а еще нам нехуй делать и мы опять будем писать эти ебливые логи
    public void logInfo()
    {
        BasicConfigurator.configure();
        logger.info("Started main");
    }


    public ArrayList<String> getProperties()
    {
        // сканируем файл database.properties с помощью ResourceBundle
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        url = resourceBundle.getString("database.url");
        user = resourceBundle.getString("database.username");
        password = resourceBundle.getString("database.password");
        from = resourceBundle.getString("database.from");
        to = resourceBundle.getString("database.to");

        ArrayList<String> ret = new ArrayList<>();
        ret.add(url);
        ret.add(user);
        ret.add(password);
        ret.add(from);
        ret.add(to);

        return ret;
    }


    public Boolean getConnection()
    {
        // Код для подключения к БД (всем привет кто смотрел гайд)
        try
        {
            logger.debug("Trying to connect...");
            // Получаем из файлика юрл, юзера и пассворд
            getProperties();
            // Устанавливаем соединение с помощью полей юрл юзер пассворд
            con = DriverManager.getConnection(url, user, password);
            // Через стейтмент выполняем любые операции с БД: переменная statement далее используется вкаждом запросе
            statement = con.createStatement();
            logger.info("Connected!");
            return true;
        }
        catch(Exception ex)
        {
            logger.error("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }


    public void closeConnection()
    {
        try
        {
            // В конце закрываем соединение и выводим сообщение
            con.close();
            logger.info("Connection closed!");
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }


    public String Query1()
    {
        try
        {
//            logger.info("Query #1");
            // executeQuery - метод statement для выполнения запросов
            ResultSet resultSet = statement.executeQuery(
                    "select Name, Year\n" +
                        "from BOOKS\n" +
                        "where YEAR(Year) between '1995' and '1999'");

            // пока в ResultSet (результирующий набор строк) есть строки, выбираем и выводим как нам угодно нужные столбцы
            while(resultSet.next())
            {
                // Получаем данные по колонкам: в первой колонке - стринги, во второй - даты
                String name = resultSet.getString(1);
                Date date = resultSet.getDate(2);
                System.out.println(name + ": " + date);
            }

            return resultSet.toString();
        }
        catch (Exception ex)
        {
            System.out.println("Error in query1!");
            System.out.println(ex);
            return "";
        }

    }


    public void Query2()
    {
        try
        {
            logger.info("Query #2");
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
            System.out.println("Error in query2!");
            System.out.println(ex);
        }
    }


    public void Query3(int x)
    {
        try
        {
            logger.info("Query #3");
            // а тут я ебать крутой и сделал запрос не через джоины а через подзапросы
            String preparedQuery =
                    "select a.*, b.Count\n" +
                    "from AUTHORS as a,\n" +
                    "(select Author, count(*) as Count\n" +
                    "from BOOKS group by Author\n" +
                    "having count(*) >= ?) as b\n" +        // знак вопроса означает, что вместо него мы подставим что то
                    "where a.Name = b.Author";
            // Для таких изменемых запросов нужен PreparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(preparedQuery);
            // и вместо первого знака вопроса вставляем параметр х, который вводится с клавиатуры
            preparedStatement.setInt(1, x);
            // Точно так же выводим запросик в ResultSet
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                String name = resultSet.getString(1);
                String country = resultSet.getString(2);
                int count = resultSet.getInt(3);
                System.out.println(name + " (" + country + ") - " + count);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error in query3!");
            System.out.println(ex);
        }
    }


    public void Query4()
    {
        try
        {
            logger.info("Query #4");
            // запросы на добавление, удаление и редактирование строк выполняется в executeUpdate
            int affectedRows = statement.executeUpdate("delete from BOOKS where YEAR(Year) > '2000'");
            // тупа удалили и вывели сколько строк затронуто
            System.out.println("Deleted " + affectedRows + " rows");
        }
        catch (Exception ex)
        {
            System.out.println("Error in query4!");
            System.out.println(ex);
        }
    }


    // код для совершения транзакции
    public void executeTransaction()
    {
        logger.info("Transaction");
        try
        {
            // коннектимся к двум бд: from и to
            int sum = 300;
            Connection connectionFrom = DriverManager.getConnection(from, user, password);
            // ставим автокоммиты в состояние false
            connectionFrom.setAutoCommit(false);
            Connection connectionTo = DriverManager.getConnection(to, user, password);
            connectionTo.setAutoCommit(false);

            try
            {
                if (sum <= 0)
                    throw new NumberFormatException("less or equals zero");
                Statement stFrom = connectionFrom.createStatement();
                Statement stTo = connectionTo.createStatement();


                // сама транзакция
                ResultSet rsFrom = stFrom.executeQuery("select Balance from Table_from");
                ResultSet rsTo = stTo.executeQuery("select Balance from Table_to");


                int accountFrom = 0;
                while (rsFrom.next())
                    accountFrom = rsFrom.getInt(1);
                int resultFrom= 0;
                if (accountFrom >= sum)
                    resultFrom = accountFrom - sum;
                else
                    throw new SQLException("Invalid balance!");


                int accountTo = 0;
                while (rsTo.next())
                    accountTo = rsTo.getInt(1);
                int resultTo = accountTo + sum;
                stFrom.executeUpdate("update Table_from set Balance = " + resultFrom);
                stTo.executeUpdate("update Table_to set Balance = " + resultTo);


                // завершение транзакции: как только прописали коммит, результаты одновременно навсегда изменятся
                connectionFrom.commit();
                connectionTo.commit();

                System.out.println("Transacted:    " + sum + " dollars");
                System.out.println("Balance_from:  " + resultFrom + " dollars");
                System.out.println("Balance_to:    " + resultTo + " dollars");
            }

            // если что то пошло не так:
            catch (SQLException e)
            {
                System.out.println("Error in transaction: " + e.getMessage());
                logger.error("Error!");
                // откат транзакции при ошибке до последнего коммита
                connectionFrom.rollback();
                connectionTo.rollback();
            }
        }



        catch (Exception e)
        {
            System.out.println("Exception in transaction!");
        }
    }
}
