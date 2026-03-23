import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.mindrot.jbcrypt.BCrypt;
 
 
@WebServlet("/login")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class LoginServlet extends HttpServlet {
  //get jsp for login
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
      RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
      view.forward(request, response);
      //debug
      System.out.println("Performed GET /login");
   }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
        
        //form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Load sql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //compare hash
        String sqlSelect = "SELECT id, username, password FROM users WHERE username = ?";
        try (
          Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "root", "mysql");

          PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        ) {
            stmt.setString(1, username);
            
            ResultSet rset = stmt.executeQuery();
            
            //if got user
            if (rset.next()) {
                String dbHashedPassword = rset.getString("password");
                if (BCrypt.checkpw(password, dbHashedPassword)) {
                  //success
                  //session
                  HttpSession session = request.getSession();
                  session.setAttribute("loggedInUser", username);
                  session.setAttribute("loggedInUserId", rset.getInt("id"));
                  session.setAttribute("toastMessage", "Welcome back, " + username + "!");
                  response.sendRedirect("home"); 
                  //debug
                  System.out.println("Performed POST /login.jsp");
                  System.out.println("user: " + username);
                  
                  return;
                    
                } 
                //fail password
                else {
                    request.setAttribute("errorMessage", "Invalid credentials");
                }
            } else {
                //fail user
                request.setAttribute("errorMessage", "Invalid credentials");
            }
            
            //fail in whatever else method remaining
            RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
            view.forward(request, response);
            
        } catch(SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Database error");
            RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
            view.forward(request, response);
        }
    }
}