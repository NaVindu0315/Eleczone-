/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "clearcart", urlPatterns = {"/clearcart"})
public class clearcart extends HttpServlet {

      private static final String url = "jdbc:mysql://localhost:3306/eleczone";
    private static final String username = "root";
    private static final String password = "";

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String sql = "DELETE FROM cart";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            response.sendRedirect("displaycart"); // redirect back to the cart page
        } catch (Exception e) {
            // handle exception
        } finally {
            try {
                if (statement!= null) statement.close();
                if (connection!= null) connection.close();
            } catch (Exception e) {
                // handle exception
            }
        }
      //  response.sendRedirect("displaycart");
    }

 
}
