import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/cart")
public class Cart extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        
        List<Book> cartItems = new ArrayList<>();
        double total = 0;

        if (cart != null && !cart.isEmpty()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "mysql")) {
                    
                    for (Integer bookId : cart.keySet()) {
                        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
                        stmt.setInt(1, bookId);
                        ResultSet rs = stmt.executeQuery();
                        
                        if (rs.next()) {
                            int quantity = cart.get(bookId);
                            
                            // <-- FIX FOR ERROR 2: Added rs.getString("img") 
                            // *Note: Ensure the order matches exactly how your Book.java constructor is written!
                            Book b = new Book(
                                rs.getInt("id"), 
                                rs.getString("title"), 
                                rs.getString("author"), 
                                rs.getDouble("price"), 
                                quantity,
                                rs.getString("img") 
                            );
                            
                            cartItems.add(b);
                            total += (b.getPrice() * quantity);
                        }
                    }
                }
            } catch (Exception e) { 
                e.printStackTrace(); 
            }
        }

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("totalPrice", String.format("%.2f", total)); // Formats total to 2 decimal places
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}