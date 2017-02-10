package controller;

import domain.ActiveUser;
import domain.ActivityLog;
import domain.User;
import service.ActiveUserService;
import service.ActivityService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Babu Sabin on 2/6/2017.
 */
public class UserServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // getting the value assigned to page parameter to decide which page to go
        String page = request.getParameter("page");

        // if page == register, then redirect to register.jsp page
        if(page.equalsIgnoreCase("register")){
            UserService userService = new UserService();

            // declare newId to assign unique id to new each member
            int newId = 0;
            try {
                // extracting the newId from user table (one greater than the maximum id in user table)
                newId = userService.getNewId();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // passing newId to register page
            request.setAttribute("newId",newId);

            // redirectig to register.jsp page
            RequestDispatcher rd = request.getRequestDispatcher("user/register.jsp");
            rd.forward(request,response);
        }

        // if page = processRegister, then go for finalRegister.jsp page to ask for password
        if(page.equalsIgnoreCase("processRegister")){
            String username;
            User user = new User();

            // starting a session to store user object and access it in finalRegister.jsp page
            HttpSession httpSession = request.getSession(true);

            // creating username for user based on firstname and id of user
            username = request.getParameter("fname")+request.getParameter("id");

            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setFname((request.getParameter("fname")).toUpperCase());
            user.setMname((request.getParameter("mname")).toUpperCase());
            user.setLname((request.getParameter("lname")).toUpperCase());
            user.setEmail((request.getParameter("email")).toLowerCase());
            user.setContact(request.getParameter("contact"));
            user.setCategory((request.getParameter("category")).toUpperCase());
            user.setUsername(username.toLowerCase());

            // store user object in session
            httpSession.setAttribute("user",user);

            // redirecting to finalRegister.jsp page
            RequestDispatcher rd = request.getRequestDispatcher("user/finalRegister.jsp");
            rd.forward(request,response);
        }


        // if page = processRegisterFinal, then store new user info in user table and redirect to index.jsp page
        if(page.equalsIgnoreCase("processRegisterFinal")){

            // starting a session and getting new user info in user object
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            // setting user input password to password attribute of user object
            user.setPassword(request.getParameter("password"));

            // adding userinfo in database
            UserService userService = new UserService();
            if(userService.addUser(user)){
                // removing user info from session
                session.removeAttribute("user");
            }

            // setting register parameter to true to display user successfully registered pop up message
            request.setAttribute("register","true");


            // redirecting to index.jsp page for login
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);

        }

        // if page = login, it process for valid login
        if(page.equalsIgnoreCase("login")){

            // getting user-input username and password
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = new User();

            // getting user with the above username and password from user table
            UserService userService = new UserService();
            try {
                user = userService.getUser(username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // checking if the user is valid or not
            if(user!=null){
                // starting a session to store the user object with valid login
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedInUser",user);

                // check if user has already punched-in or not
                ActiveUserService activeUserService = new ActiveUserService();
                if(activeUserService.isActive(user.getId())){
                    // if user is already punched-in, redirect to punchOut page
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/punchOutHome.jsp");
                    requestDispatcher.forward(request,response);
                }else{
                    // if user isnot punched-in, redirect to punchIn page
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/punchInHome.jsp");
                    requestDispatcher.forward(request,response);
                }
            } else{
                // setting loginerror to true if user-input is invalid
                request.setAttribute("loginerror","true");

                // redierct the user again to index.jsp with proper message
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request,response);
            }
        }

        // if page = processPunchIn, then store the user id and punchIn time activitylog table and activeuser table in database
        if(page.equalsIgnoreCase("processPunchIn")){

            // getting current time and date of server
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String reason;

            // starting a session and get loggedInUser info in user object
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("loggedInUser");

            reason = request.getParameter("reason");

            // setting user id and entry time
            ActiveUser activeUser = new ActiveUser();
            activeUser.setId(user.getId());
            activeUser.setEntryDateTime(sdf.format(dt));
            activeUser.setReason(reason);

            // adding active user info in activeuser table
            ActiveUserService activeUserService = new ActiveUserService();
            if(activeUserService.addStatus(activeUser)){

                System.out.println("Active Users added in activeuser");

            }

            // setting user id and entry time
            ActivityLog activityLog = new ActivityLog();
            activityLog.setId(user.getId());
            activityLog.setEntryDateTime(sdf.format(dt));
            activityLog.setReason(reason);

            session.setAttribute("punchedInActivity",activityLog);

            // adding active user info in activitylog table
            ActivityService activityService = new ActivityService();
            if(activityService.addActivity(activityLog)){

                System.out.println("Activity added in activitylog");

            }

            // redirecting to punchOut.jsp page to punchOut
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/punchOutHome.jsp");
            requestDispatcher.forward(request,response);
        }

        // if page = processPunchOut, then store punchOut time in activitylog table and remove user from activeuser table in database
        if(page.equalsIgnoreCase("processPunchOut")){

            // starting a session and getting activity of user who is currently punchedIn
            HttpSession session = request.getSession(true);
            ActivityLog activityLog = (ActivityLog) session.getAttribute("punchedInActivity");

            // getting current server time and date
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            activityLog.setExitDateTime(sdf.format(dt));

            // updating exit time and date of punchedOut user in activitylog table
            ActivityService activityService = new ActivityService();
            if(activityService.updateExitDateTime(activityLog)){
                System.out.println("Exit date and time of "+activityLog.getId()+" successfully registered");
                // removing session attribute of  punchedInActivity
                session.removeAttribute("punchedInActivity");
            }

            // Removing the user from activeuser table in database
            ActiveUserService activeUserService = new ActiveUserService();
            if(activeUserService.deactivateUser(activityLog.getId())){
                System.out.println("User Deactivated Successfully");
            }

            // redirecting to punchInHome page
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/punchInHome.jsp");
            requestDispatcher.forward(request,response);
        }

        // if page = logout, then redirect to index.jsp page and remove the loggedInUser attribute from session
        if(page.equalsIgnoreCase("logout")){

            HttpSession session = request.getSession(true);
            session.removeAttribute("loggedInUser");
            request.setAttribute("logout","true");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);
        }

        // if page = userHistory, fetch and display all data from activitylog table with the loggedInUser id
        if(page.equalsIgnoreCase("userHistory")){

            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("loggedInUser");

            ArrayList<ActivityLog> activityLogArrayList;
            ActivityService activityService = new ActivityService();
            activityLogArrayList = (ArrayList<ActivityLog>) activityService.getUserHistory(user);

            request.setAttribute("userHistoryList",activityLogArrayList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userHistory.jsp");
            requestDispatcher.forward(request,response);

        }

        // if page = userHome, redirect user to appropriate home page
        if(page.equalsIgnoreCase("userHome")){

            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("loggedInUser");

            // calling function redirectuserHome that redirect to appropriate home page
            redirectUserHome(request,response,user);

        }

        // getting current user info and redirect to editUserInfo.jsp page and display it to user for editing
        if(page.equalsIgnoreCase("editUserInfo")){

            HttpSession session = request.getSession(true);

            // getting info of loggedInUser
            User user = (User) session.getAttribute("loggedInUser");

            session.setAttribute("user",user);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/editUserInfo.jsp");
            requestDispatcher.forward(request,response);

        }

        // updating new user info in the user table of database and redirect to appropriate home page
        if(page.equalsIgnoreCase("updateEditUserInfo")){

            HttpSession session = request.getSession();

            User user = new User();

            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setFname((request.getParameter("fname")).toUpperCase());
            user.setMname((request.getParameter("mname")).toUpperCase());
            user.setLname((request.getParameter("lname")).toUpperCase());
            user.setEmail((request.getParameter("email")).toLowerCase());
            user.setContact(request.getParameter("contact"));
            user.setCategory((request.getParameter("category")).toUpperCase());
            user.setUsername((request.getParameter("username")).toLowerCase());
            user.setPassword(request.getParameter("password"));

            UserService userService = new UserService();
            userService.editUserInfo(user);
            session.removeAttribute("loggedInUser");
            request.setAttribute("update", "true");
            user = userService.getUserByID(user.getId());
            session.setAttribute("loggedInUser",user);

            redirectUserHome(request,response,user);

        }


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    // function to redirect user to appropriate homepage
    public void redirectUserHome(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response,User user) throws ServletException, IOException {
        if(user==null){
            // if user not logged in, the redirect to index.jsp page
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);
        }else{
            // if user is logged in and also punched in, then redirect to punchOutHome.jsp
            ActiveUserService activeUserService = new ActiveUserService();
            if(activeUserService.isActive(user.getId())){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/punchOutHome.jsp");
                requestDispatcher.forward(request,response);
            }else{
                // if user is logged in but not punched in, then redirect to punchInHome.jsp
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/punchInHome.jsp");
                requestDispatcher.forward(request,response);
            }
        }
    }

}
