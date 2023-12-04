package dao;

import com.mysql.cj.jdbc.Driver;
import config.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQuotesDAO {


        public List<String> getQuotes(){
            Connection connection = null;
            List<String> quotes = new ArrayList<>();
            try {
                //register the driver
                DriverManager.registerDriver(new Driver());

                //establish connection
                connection = DriverManager.getConnection(
                        Config.getUrl(),
                        Config.getUser(),
                        Config.getPassword()
                );

                //create statement object
                Statement statement = connection.createStatement();

                //execute statement
                ResultSet resultSet = statement.executeQuery("SELECT content from quotes");
                while(resultSet.next()){
                    quotes.add(resultSet.getString("content"));
                }

            } catch (SQLException sqlx) {
                System.out.println(sqlx.getMessage());
            } finally {
                if  (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException sqlx) {
                        System.out.println(sqlx.getMessage());
                    }
                }

            }
            return quotes;
        }



}
