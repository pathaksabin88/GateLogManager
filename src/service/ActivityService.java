package service;

import domain.ActivityLog;
import domain.User;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Babu Sabin on 2/8/2017.
 */
public class ActivityService {

    // returns all activity from the activitylog table
    public List<ActivityLog> getActivity(){
        ArrayList<ActivityLog> activityLogArrayList = new ArrayList<ActivityLog>();

        String query = "select * from activitylog";

        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ActivityLog activityLog = new ActivityLog();

                activityLog.setId(resultSet.getInt("id"));
                activityLog.setEntryDateTime(resultSet.getString("entryDateTime"));
                activityLog.setReason(resultSet.getString("reason"));

                activityLogArrayList.add(activityLog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityLogArrayList;
    }

    // add new activity in the activitylog table
    public boolean addActivity(ActivityLog activityLog){
        boolean status = false;
        String query = "insert into activitylog (id,entryDateTime,reason) values(?,?,?)";
        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setInt(1,activityLog.getId());
            preparedStatement.setString(2,activityLog.getEntryDateTime());
            preparedStatement.setString(3,activityLog.getReason());
            if(preparedStatement.execute()){
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // update Exit date and time in activity log when user punchedOut
    public boolean updateExitDateTime(ActivityLog activityLog){
        boolean status = false;
        String query = "update activitylog set exitDateTime = ? where id = ? and entryDateTime = ?";
        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setString(1,activityLog.getExitDateTime());
            preparedStatement.setInt(2,activityLog.getId());
            preparedStatement.setString(3,activityLog.getEntryDateTime());
            if(preparedStatement.execute()){
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // providing list of activity of a particular user
    public List<ActivityLog> getUserHistory(User user){

        ArrayList<ActivityLog> activityLogArrayList = new ArrayList<ActivityLog>();

        String query = "select * from activitylog where id = ? ";
        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ActivityLog activityLog = new ActivityLog();
                activityLog.setId(resultSet.getInt("id"));
                activityLog.setEntryDateTime(resultSet.getString("entryDateTime"));
                activityLog.setExitDateTime(resultSet.getString("exitDateTime"));
                activityLog.setReason(resultSet.getString("reason"));
                activityLogArrayList.add(activityLog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityLogArrayList;
    }

}
