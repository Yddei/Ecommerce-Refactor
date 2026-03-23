import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        //login, very buggy when restarting tomcat and theres no actual session but somehow you can click orders
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login");
            return;
        }
        
        String username = (String) session.getAttribute("loggedInUser");
        List<Order> orderHistory = new ArrayList<>();

        //get all orders sort by newest
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "mysql")) {
                
                String sql = "SELECT id, total_price, order_date, status, address FROM orders WHERE username = ? ORDER BY order_date DESC";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Order order = new Order(
                        rs.getInt("id"),
                        rs.getDouble("total_price"),
                        rs.getString("order_date"),
                        rs.getString("status"),
                        rs.getString("address")
                    );
                    orderHistory.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //give data to jsp
        request.setAttribute("orderHistory", orderHistory);
        request.getRequestDispatcher("/orders.jsp").forward(request, response);
    }
}