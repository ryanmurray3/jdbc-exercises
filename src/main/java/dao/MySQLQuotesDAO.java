package dao;

import com.mysql.cj.jdbc.Driver;
import config.Config;
import models.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLQuotesDAO {

    private Connection connection = null;


    public List<Quote> getQuotes(){
        List<Quote> quotes = new ArrayList<>();
        try {
            // Create statement object
            Statement statement = connection.createStatement();

            // Execute statement
            ResultSet resultSet = statement.executeQuery("SELECT * FROM quotes");
            while(resultSet.next()){
                quotes.add(new Quote(
                        resultSet.getString("author"),
                        resultSet.getString("content")
                ));
            }
        } catch (SQLException sqlx){
            System.out.println(sqlx.getMessage());
        }

        return quotes;
    }




    public long insertQuote(){

        // create a connection

        // create and execute statement

        // close connection

        return 0;
    }

    public void createConnection() {
    }

    public void closeConnection() {
    }
}