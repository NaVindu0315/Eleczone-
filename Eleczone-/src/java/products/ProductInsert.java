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
import java.sql.PreparedStatement;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@MultipartConfig
@WebServlet(name = "ProductInsert", urlPatterns = {"/ProductInsert"})
public class ProductInsert extends HttpServlet {
private static final long serialVersionUID = 1L;

// JDBC URL, username, and password for your MySQL database
    private static final String url = "jdbc:mysql://localhost:3306/eleczone";
    private static final String username = "root";
    private static final String password = "";
   


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //to get user input
        String name = request.getParameter("item_name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        
        
        Connection connection = null;
        PreparedStatement st = null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            connection = DriverManager.getConnection(url,username,password);
            
            
            String id = UUID.randomUUID().toString();
            
           
            String sql = "INSERT INTO products (id, name, price,category,description) VALUES (?, ?, ?, ?, ?)";
            st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, name);
            st.setDouble(3, price);
            st.setString(4, category);
            st.setString(5, description);
            
           
           
          
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected > 0) {
                out.println("<h3>Product added successfully!</h3>");
            } else {
                out.println("<h3>Error adding product!</h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                out.println("<h3>Error closing database connections: " + e.getMessage() + "</h3>");
            }
        }
       
    }


}
