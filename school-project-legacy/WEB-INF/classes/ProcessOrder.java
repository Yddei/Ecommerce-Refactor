import java.io.IOException;
import java.sql.*;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class ProcessOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        //authentication
        if (session == null || session.getAttribute("loggedInUser") == null) {
            if (session != null) {
                session.setAttribute("toastMessage", "Log in first");
            }
            response.sendRedirect("login");
            return;
        }
        
        String username = (String) session.getAttribute("loggedInUser");
        
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("Shop");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "mysql")) {
                
                //industry practice to pause autocommit when performing multiple sql statements in 1 action
                conn.setAutoCommit(false);
                
                try {
                    //price calculated at back end
                    //security feature of zero trust
                    double grandTotal = 0;
                    for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                        PreparedStatement priceStmt = conn.prepareStatement("SELECT price FROM books WHERE id = ?");
                        priceStmt.setInt(1, entry.getKey());
                        ResultSet rs = priceStmt.executeQuery();
                        if (rs.next()) {
                            grandTotal += rs.getDouble("price") * entry.getValue();
                        }
                    }
                    //get address
                    String userAddress = ""; //put outside for line 73
                    PreparedStatement addressStmt = conn.prepareStatement("SELECT address FROM users WHERE username = ?");
                    addressStmt.setString(1, username);
                    ResultSet addressRs = addressStmt.executeQuery();
                    if (addressRs.next()) {
                        // Grab the address stored in the user's profile
                        userAddress = addressRs.getString("address");
                    }

                    //insert into orders table
                    String insertOrder = "INSERT INTO orders (username, total_price, address) VALUES (?, ?, ?)";
                    PreparedStatement orderStmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
                    orderStmt.setString(1, username);
                    orderStmt.setDouble(2, grandTotal);
                    orderStmt.setString(3, userAddress); // Injecting the linked address
                    orderStmt.executeUpdate();

                    //the new orders id > go into order_items table
                    ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                    int orderId = 0;
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    }

                    //insert into order_items table
                    String insertItem = "INSERT INTO order_items (order_id, book_id, quantity, price) VALUES (?, ?, ?, ?)";
                    //minus however many books they buy
                    String updateBook = "UPDATE books SET qty = qty - ? WHERE id = ?";
                    
                    PreparedStatement itemStmt = conn.prepareStatement(insertItem);
                    PreparedStatement updateBookStmt = conn.prepareStatement(updateBook);

                    for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                        int bookId = entry.getKey();
                        int qtyBought = entry.getValue();
                        
                        PreparedStatement priceStmt = conn.prepareStatement("SELECT price FROM books WHERE id = ?");
                        priceStmt.setInt(1, bookId);
                        ResultSet rs = priceStmt.executeQuery();
                        double price = 0;
                        if (rs.next()) price = rs.getDouble("price");

                        itemStmt.setInt(1, orderId);
                        itemStmt.setInt(2, bookId);
                        itemStmt.setInt(3, qtyBought);
                        itemStmt.setDouble(4, price);
                        itemStmt.executeUpdate();
                        
                        updateBookStmt.setInt(1, qtyBought);
                        updateBookStmt.setInt(2, bookId);
                        updateBookStmt.executeUpdate();
                    }

                    //success
                    conn.commit();
                    
                } catch (SQLException ex) {
                    conn.rollback();
                    throw ex; 
                } finally {
                    //reset to autocommit
                    conn.setAutoCommit(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("toastMessage", "Error occurred");
            response.sendRedirect("cart");
            return;
        }

        //reset 
        session.removeAttribute("cart"); 
        session.setAttribute("toastMessage", "Order placed successfully! Thank you.");
        response.sendRedirect("home"); 
    }
}