
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mr.Kshan
 */
@WebServlet("/Productservlet")
public class Productservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Replace with your actual JDBC connection details
    private static final String url = "jdbc:mysql://localhost:3306/eleczone";
    private static final String username = "root";
    private static final String password = "";
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eleczone", "root", "");
            System.out.println("db connected");

            String sql = "SELECT * FROM products";
            st = connection.prepareStatement(sql);

            rs = st.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                
               
                productList.add(product);
            }

            request.setAttribute("productList", productList);
            request.getRequestDispatcher("products.jsp").forward(request, response);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("customer.jsp").forward(request, response);
          request.setAttribute("productList", productList);
            request.getRequestDispatcher("customer.jsp").forward(request, response);
            


            RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
            request.setAttribute("productList", productList);
            rd.forward(request, response);

        } catch (Exception e) {
           // out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                out.println("<h3>Error closing database connections: " + e.getMessage() + "</h3>");
            }
        }
    }
    

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    
    

}
