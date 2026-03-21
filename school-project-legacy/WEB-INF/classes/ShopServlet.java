import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        //Get all books
        //list of books
        List<Book> bookList = new ArrayList<>();
        //sql
        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch (Exception e) {}
        String sqlSelect = "SELECT id, title, author, price, qty, img FROM books";        
        try (
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root", "mysql");
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            ResultSet rset = stmt.executeQuery();
        ) {
            
            while(rset.next()) {
                //book class 
                Book book = new Book(
                    rset.getInt("id"),
                    rset.getString("title"),
                    rset.getString("author"),
                    rset.getDouble("price"),
                    rset.getInt("qty"),
                    rset.getString("img")
                );
                // Add it to our list
                bookList.add(book);
            }
            
        } catch(SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Database error");
        }

        //for front end
        request.setAttribute("books", bookList);
        RequestDispatcher view = request.getRequestDispatcher("/shop.jsp");
        view.forward(request, response);
        //debug
        System.out.println("Performed GET /shop.jsp");
    }
}