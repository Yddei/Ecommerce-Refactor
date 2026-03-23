import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.mindrot.jbcrypt.BCrypt;
 
@WebServlet("/register")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class RegisterServlet extends HttpServlet {
//GET the jsp page for register
  @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {  
        //forward jsp page
        RequestDispatcher view = request.getRequestDispatcher("/register.jsp");
        view.forward(request, response);
        // For testing and debugging - Print a message to Tomcat's console
        System.out.println("Performed GET /register.jsp");
   }
//POST register details, will use bcrypt to hash password first
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
        //Form data
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String address = request.getParameter("address");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

      // Basic Validation
      if (password == null || !password.equals(confirmPassword)) {
        out.println("<h3>Error: Passwords do not match!</h3>");
        return;
      }

      // Salt + Hash
      String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

      //Parameterised queries for anti sql injection
      String sqlInsert = "insert into users (username, email, password, address) values (?, ?, ?, ?)";

      //Load sql driver
      try {
          Class.forName("com.mysql.cj.jdbc.Driver"); 
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
          out.println("check jar file");
          return;
      }
      try (
        Connection conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
          "root", "mysql");

        PreparedStatement stmt = conn.prepareStatement(sqlInsert);
      ) {
        stmt.setString(1, username);
        stmt.setString(2, email);
        stmt.setString(3, hashedPassword);
        stmt.setString(4, address);

        int countInserted = stmt.executeUpdate();
        
        //Success
        if (countInserted > 0) {
            request.getSession().setAttribute("toastMessage", "Registration Successful!");
            response.sendRedirect("home");

            //debug
            System.out.println("Performed POST /register.jsp");
            System.out.println("The SQL statement is: " + sqlInsert + "\n");
            System.out.println(countInserted + " records inserted.\n");
            
            return; 
        }
      } catch(SQLException ex) {
        ex.printStackTrace();
        out.println("<h3>Database Error</h3>");
      }
    }
}