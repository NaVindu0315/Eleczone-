/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String productName = request.getParameter("productName");
    String productPrice = request.getParameter("productPrice");
        String itemcount = request.getParameter("itemcount");
        double intcount = Double.parseDouble(itemcount);
        double price =  Double.parseDouble(productPrice);
        
        
        double total = intcount*price;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                      
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>pako</h1>");
            out.println("<h2>Name = "+productName);
            out.println("price = "+price);
            out.println("item count = "+intcount);
            out.println("total = "+total);
            out.println("</h2>");
            out.println("</body>");
            out.println("</html>");
    }

 

}
