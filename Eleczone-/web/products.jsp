<%-- 
    Document   : products
    Created on : Apr 29, 2024, 6:57:34 PM
    Author     : Mr.Kshan
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Products</title>
  <style>
    /* Add your CSS styles here */
    body {
      font-family: Arial, sans-serif;
    }
    .container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 20px;
    }

    .product-grid {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
    }

    .product-card {
      width: calc(33.33% - 20px); /* Three columns with some gap */
      margin-bottom: 20px;
      background-color: #f9f9f9;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
      overflow: hidden;
      transition: transform 0.3s ease;
    }

    .product-card:hover {
      transform: translateY(-5px); /* Move up slightly on hover */
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    .product-card img {
      width: 100%;
      height: auto;
      display: block;
    }

    .card-content {
      padding: 20px;
    }

    .card-content h3 {
      margin: 0;
      font-size: 1.2em;
      color: #333;
    }

    .card-content p {
      margin: 5px 0;
      font-size: 0.9em;
      color: #666;
    }

    .buttons {
      padding: 0 20px 20px;
      display: flex;
      justify-content: space-between;
    }

    .buttons button {
      padding: 10px 20px;
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .buttons button:hover {
      background-color: #0056b3;
    }

    @media screen and (max-width: 768px) {
      .product-card {
        width: calc(50% - 20px); /* Two columns on smaller screens */
      }
    }

    @media screen and (max-width: 576px) {
      .product-card {
        width: calc(100% - 20px); /* Full width on extra small screens */
      }
    }
  </style>
</head>
<body>
  <div>
    <jsp:include page="/Productservlet"/>
    <br>
    
    <div class="container">
      <div class="content">
        <h2>Products</h2>
        <div class="product-grid">
          <c:forEach var="product" items="${productList}">
            <div class="product-card">
           
                 <img src="itemimages/${product.img}"  width="80" height="40" alt="Product Image">
              <div class="card-content">
                <h3>${product.name}</h3>
                <p>Type: ${product.category}</p>
                <p>Info: ${product.description}</p>
                <p>Price: ${product.price}</p>
              </div>
              <form action="Test" method="post">
<div class="buttons">
  <input type="hidden" name="productName" value="${product.name}">
  <input type="hidden" name="productPrice" value="${product.price}"> 
  
 
  <div> <input type="number" name="itemcount" placeholder="Select the count"></div>
  
  <br> <!-- Add a line break here -->
  <button type="submit">Add to Cart</button>
  <button>Buy Now</button>
</div>
              </form>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
    
     </div>
</body>
</html>