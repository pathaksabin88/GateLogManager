package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Babu Sabin on 2/7/2017.
 */

// Database Connection class
public class DatabaseConnection {

    Connection connection = null;

    String user = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/dlms";

    public DatabaseConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String query){
        PreparedStatement pstm = null;
        try{
            pstm = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstm;
    }

}
