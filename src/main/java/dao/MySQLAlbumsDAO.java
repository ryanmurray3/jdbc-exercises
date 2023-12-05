package dao;

import com.mysql.cj.jdbc.Driver;
import config.Config;
import models.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAlbumsDAO {
    // initialize the connection to null so we know whether or not to close it when done
    private Connection connection = null;

    public void createConnection() throws MySQLAlbumsException {
        System.out.print("Trying to connect... ");
        try {
            /*
TODO: create the connection and assign it to the instance variable
 register driver
*/
            DriverManager.registerDriver(new Driver());

            // establish connection
            connection = DriverManager.getConnection(
                    Config.getUrl(),
                    Config.getUser(),
                    Config.getPassword()
            );
            System.out.println("connection created.");
        } catch (SQLException e) {
            throw new MySQLAlbumsException("connection failed!!!");
        }
    }

    public List<Album> fetchAlbums() throws SQLException, MySQLAlbumsException {
        List<Album> albums = new ArrayList<>();

        // TODO: write your code here
        try {
            PreparedStatement statement = connection.prepareStatement("select * from albums order");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM albums");
            while (resultSet.next()) {
                albums.add(new Album(
                        resultSet.getString("artist"),
                        resultSet.getString("name")
                ));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MySQLAlbumsException("Error executing query: " + e.getMessage() + "!!!");
        }

            return albums;
        }

        public Album fetchAlbumById ( long id){
            Album album = null;

            // TODO: write your code here
            try {
                PreparedStatement statement = connection.prepareStatement("select * from albums where id = ? ");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    album = new Album(
                            resultSet.getString("artist"),
                            resultSet.getString("name")
                    );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
            return (Album) album;


        }

        // Note that insertAlbum should return the id that MySQL creates for the new inserted album record
        public long insertAlbum (Album album) throws MySQLAlbumsException {
            long id = 0;

            // write your code here
            try {
                String sql = "INSERT INTO albums (artist, name, genre, sales, release_date) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, album.getArtist());
                preparedStatement.setString(2, album.getName());
                preparedStatement.setString(3, album.getGenre());
                preparedStatement.setDouble(4, album.getSales());
                preparedStatement.setInt(5, album.getReleaseDate());
                preparedStatement.executeUpdate();


                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new MySQLAlbumsException("Error executing query: " + e.getMessage() + "!!!");
            }

            return id;
        }


    public void updateAlbum (Album album) throws MySQLAlbumsException {

            // TODO: write your code here
            try {
                Statement statement = connection.createStatement();
                statement.executeQuery("UPDATE albums SET artist = '" + album.getArtist() + "', name = '" + album.getName() + "' WHERE id = " + album.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new MySQLAlbumsException("Error executing query: " + e.getMessage() + "!!!");
            }


        }

        public void deleteAlbumById ( long id) throws MySQLAlbumsException {

            // TODO: write your code here
            try {
                Statement statement = connection.createStatement();
                statement.executeQuery("DELETE FROM albums WHERE id = ? " + id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new MySQLAlbumsException("Error executing query: " + e.getMessage() + "!!!");

            }
            System.out.println("Album deleted.");


        }


        public int getTotalAlbums () throws MySQLAlbumsException {
            int count = 0;
            try {
                //TODO: fetch the total number of albums from the albums table and assign it to the local variable

                Statement statement = connection.createStatement();

                // OPTION 1: LOOP OVER RESULTSET AND INCREMENT COUNT
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM codeup_test_db.albums");
//                while (resultSet.next()){
//                    count++;
//                }

                // OPTION 2: GET THE COUNT(*) AND DISPLAY IT
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM hippo_ryan.albums");
                resultSet.next();
                // resultSet get... methods accept either a column name
                // or a column index
                count = resultSet.getInt(1);


            } catch (SQLException e) {
                throw new MySQLAlbumsException("Error executing query: " + e.getMessage() + "!!!");
            }
            return count;
        }

        public void closeConnection () {
            if (connection == null) {
                System.out.println("Connection aborted.");
                return;
            }
            try {
                //TODO: close the connection
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                // ignore this
            }
        }
    }



