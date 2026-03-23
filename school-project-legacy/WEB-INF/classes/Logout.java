import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        
        if (currentSession != null) {
            currentSession.invalidate();
        }

        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("toastMessage", "Logged out.");
        response.sendRedirect("home");
    }
}