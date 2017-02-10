package service;

import domain.User;
import utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Babu Sabin on 2/7/2017.
 */
public class UserService {

    public User getUser(String username, String password) throws SQLException {
        User user = null;
        String query = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setMname(rs.getString("mname"));
                user.setLname(rs.getString("lname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCategory(rs.getString("category"));
                user.setContact(rs.getString("contact"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUserList(){
        List<User> userList = new ArrayList<User>();

        String query = "select * from user";

        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);

        ResultSet rs = null;
        try {
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setMname(rs.getString("mname"));
                user.setLname(rs.getString("lname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCategory(rs.getString("category"));

                userList.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public boolean addUser(User user){

        Boolean status = false;

        String query = "insert into user (id,fname,mname,lname,email,contact,category,username,password) values(?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);


        try{
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getFname());
            preparedStatement.setString(3,user.getMname());
            preparedStatement.setString(4,user.getLname());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setString(6,user.getContact());
            preparedStatement.setString(7,user.getCategory());
            preparedStatement.setString(8,user.getUsername());
            preparedStatement.setString(9,user.getPassword());

            if(preparedStatement.execute()){
                status = true;
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;

    }

    public int getNewId() throws SQLException {
        int newId = 0;
        String query = "select max(id) as id from user";

        DatabaseConnection dbConnection = new DatabaseConnection();

        PreparedStatement preparedStatement = dbConnection.getPreparedStatement(query);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                newId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return(newId+1);
    }

    public void editUserInfo(User user){

       // boolean status = false;

        String query = "update user set fname = ?, mname = ?, lname = ?, email = ?, contact = ?, category = ?, username = ?, password = ? where id = ? ";

        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);

        try {
            preparedStatement.setString(1,user.getFname());
            preparedStatement.setString(2,user.getMname());
            preparedStatement.setString(3,user.getLname());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getContact());
            preparedStatement.setString(6,user.getCategory());
            preparedStatement.setString(7,user.getUsername());
            preparedStatement.setString(8,user.getPassword());
            preparedStatement.setInt(9,user.getId());

            preparedStatement.execute();
            /*if(preparedStatement.execute()){
                status = true;
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        return status;

    }

    public User getUserByID(int id){

        User user = null;
        String query = "select * from user where id = ? ";
        PreparedStatement preparedStatement = new DatabaseConnection().getPreparedStatement(query);
        try {
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFname(rs.getString("fname"));
                user.setMname(rs.getString("mname"));
                user.setLname(rs.getString("lname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCategory(rs.getString("category"));
                user.setContact(rs.getString("contact"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;

    }





}
