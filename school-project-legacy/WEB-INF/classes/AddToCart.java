import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //get book id
        String bookIdStr = request.getParameter("bookId");
        if (bookIdStr == null || bookIdStr.isEmpty()) {
            response.sendRedirect("shop");
            return;
        }
        
        int bookId = Integer.parseInt(bookIdStr);

        //get session
        HttpSession session = request.getSession();

        //get cart
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        //add book += 1
        cart.put(bookId, cart.getOrDefault(bookId, 0) + 1);
        session.setAttribute("cart", cart);

        session.setAttribute("toastMessage", "Added to cart");
        response.sendRedirect("shop");
    }
}