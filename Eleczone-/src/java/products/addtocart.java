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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "addtocart", urlPatterns = {"/addtocart"})
public class addtocart extends HttpServlet {

private static final String url = "jdbc:mysql://localhost:3306/eleczone";
    private static final String username = "root";
    private static final String password = "";
 
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String productName = request.getParameter("productName");
    String productPrice = request.getParameter("productPrice");
        String itemcount = request.getParameter("itemcount");
        double intcount = Double.parseDouble(itemcount);
        double price =  Double.parseDouble(productPrice);
        
        
        double total = intcount*price;
        
        
        
        ///to set data
        
          Connection connection = null;
        PreparedStatement st = null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            
            connection = DriverManager.getConnection(url,username,password);
            
            
            String id = UUID.randomUUID().toString();
            
           
            String sql = "INSERT INTO cart (id,name,price,count,total) VALUES (?, ?, ?, ?,?)";
            st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, productName);
            st.setDouble(3, intcount);
            st.setDouble(4, price);
            st.setDouble(5,total);
            
            
           
           
          
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
        response.sendRedirect("index.jsp");
    }

 

}
