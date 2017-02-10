package service;

import domain.ActiveUser;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Babu Sabin on 2/8/2017.
 */
public class ActiveUserService {

    // check if user is punchedIn or nor, if yes return true, else return false
    public boolean isActive(int id){

        boolean status = false;

        String query = "select * from activeuser where id = ?";

        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                status = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // add record to activeuser table, record is added when user is punchedIn
    public boolean addStatus(ActiveUser activeUser){
        boolean status = false;
        String query = "insert into activeuser (id,entryDateTime,reason) values(?,?,?)";
        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setInt(1,activeUser.getId());
            preparedStatement.setString(2,activeUser.getEntryDateTime());
            preparedStatement.setString(3,activeUser.getReason());
            if(preparedStatement.execute()){
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // remove user from activeuser table when punchedOut
    public boolean deactivateUser(int id){

        boolean status = false;

        String query = "delete from activeuser where id = ?";

        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setInt(1,id);
            if(preparedStatement.execute()){
                System.out.println("successfully executed");
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;

    }



}
