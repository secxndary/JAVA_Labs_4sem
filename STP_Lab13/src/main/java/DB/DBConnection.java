package DB;


import Models.Users;
import Models.Nation;

import java.sql.*;
import java.util.ArrayList;


public class DBConnection {
    private Connection connection;
    private Statement statement;

    public Connection GetConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            String url = "jdbc:sqlserver://DESKTOP-8HNL9IM;databaseName=STP_Lab9;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
            connection = (Connection) DriverManager.getConnection(url, "sa","1111");
            System.out.println("Connection succesfull!");
        }catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }

        return connection;
    }

    public ArrayList<Users> GetAllUsers() throws SQLException {
        String sqlQuery="select * from Users;";
        statement = connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sqlQuery);

        ArrayList<Users> users = new ArrayList<Users>();
        while(resultSet.next()){
            String username = resultSet.getString(1);
            String password = resultSet.getString(2);
            String role = resultSet.getString(3);

            Users user = new Users(username,password,role);
            users.add(user);
        }
        return users;
    }

    public boolean AddUser(Users user) throws SQLException {

        //проверка на существующее имя пользователя
        String select="insert Users(username,userpassword,userrole) values('"+user.getUsername() +"','"+user.getPassword()+ "','user');";
        statement = GetConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(select);

        return true;
    }

    public Users GetByUsername(String Username) throws SQLException {
        String select ="select * from Users where username = '"+Username+"';";
        statement = GetConnection().createStatement();
        ResultSet resultSet= statement.executeQuery(select);
        Users user=null;
        if(resultSet != null){
            while(resultSet.next()){
                String username = resultSet.getString(1);
                String password = resultSet.getString(2);
                String role = resultSet.getString(3);
                user = new Users(username,password,role);
            }
        }
        return  user;
    }

    public Nation GetByname(String names) throws SQLException {
        String select ="select * from [nation] where name = '"+names+"';";
        statement = GetConnection().createStatement();
        ResultSet resultSet= statement.executeQuery(select);
        Nation nations=null;
        if(resultSet != null){
            while(resultSet.next()){
                String name = resultSet.getString(1);
                String country = resultSet.getString(2);
                nations = new Nation(name,country);
            }
        }
        return  nations;
    }

    public ArrayList<Nation> GetAllNations() throws SQLException {
        String sqlQuery="select * from [nation]";
        statement = GetConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(sqlQuery);

        ArrayList<Nation> nations = new ArrayList<Nation>();
        while(resultSet.next()){
            String name = resultSet.getString(1);
            String country = resultSet.getString(2);
            Nation nation = new Nation(name,country);
            nations.add(nation);
        }
        return nations;
    }

    public boolean AddNations(Nation nat) throws SQLException {
//        logger.info("Добавление народа");
        String select="insert nation(name,number ) values('"+nat.getName()+"','"+nat.getNumber()+"')";
        statement = GetConnection().createStatement();

        ResultSet resultSet=statement.executeQuery(select);

        return true;
    }

    public boolean Remove(String names) throws SQLException {
//        logger.info("Удаление народа");
        String select="DELETE FROM nation WHERE name ='"+names+"'";
        statement = GetConnection().createStatement();

        ResultSet resultSet=statement.executeQuery(select);

        return true;
    }

    public boolean Update(String names,String number) throws SQLException {
//        logger.info("Изменение народа");
        String select="UPDATE nation SET number ='"+number+"' WHERE name ='"+names+"';";
        statement = GetConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(select);

        return true;
    }
}