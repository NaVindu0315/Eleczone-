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
import java.sql.ResultSet;
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
@WebServlet(name = "displaycart", urlPatterns = {"/displaycart"})
public class displaycart extends HttpServlet {
    
    private static final String url = "jdbc:mysql://localhost:3306/eleczone";
    private static final String username = "root";
    private static final String password = "";

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);

        String sql = "SELECT * FROM cart";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        out.println("<h2>Cart Contents:</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Price</th><th>Count</th><th>Total</th></tr>");

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            double count = resultSet.getDouble("count");
            double total = resultSet.getDouble("total");

            out.println("<tr>");
            out.println("<td>" + id + "</td>");
            out.println("<td>" + name + "</td>");
            out.println("<td>" + price + "</td>");
            out.println("<td>" + count + "</td>");
            out.println("<td>" + total + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
    } catch (Exception e) {
        out.println("<h3>Error: " + e.getMessage() + "</h3>");
    } finally {
        try {
            if (resultSet!= null) resultSet.close();
            if (statement!= null) statement.close();
            if (connection!= null) connection.close();
        } catch (Exception e) {
            out.println("<h3>Error closing database connections: " + e.getMessage() + "</h3>");
        }
    }
}

}
