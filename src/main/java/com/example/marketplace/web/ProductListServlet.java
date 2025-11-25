package com.example.marketplace.web;

import java.io.IOException;
import java.util.List;

import com.example.marketplace.dao.ProductDao;
import com.example.marketplace.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "ProductListServlet", urlPatterns = {"/products"})
public class ProductListServlet extends HttpServlet {
	
	private final ProductDao productDao = new ProductDao();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//load from DB
		List<Product> products = productDao.findAll();
		
		// put on request scope
		req.setAttribute("products", products);
		
		// forward to jsp
		req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
	}

}
