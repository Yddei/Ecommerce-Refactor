// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\HelloServlet.java"
import java.io.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;        // Tomcat 10
import jakarta.servlet.annotation.*;  // Tomcat 10
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;        // Tomcat 9
//import javax.servlet.annotation.*;  // Tomcat 9
 
@WebServlet("/home")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class HomeServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
 
      // Set the response's MIME type of the response message
        response.setContentType("text/html");
      // Allocate an output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();  // throw IOException
      
      // Write the response message, in an HTML page
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
      // For testing and debugging - Print a message to Tomcat's console
        System.out.println("hello world, to Tomcat!");   // Check Tomcat's console for this message
   }
}