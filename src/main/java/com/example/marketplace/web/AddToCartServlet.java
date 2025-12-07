package com.example.marketplace.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "AddToCartServlet", urlPatterns = {"/cart/add"})
public class AddToCartServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String productIdParam = req.getParameter("productId");
		String qtyParam = req.getParameter("qty");
		
		if (productIdParam == null) {
			resp.sendRedirect(req.getContextPath() + "/products");
			return;
		}
		
		int productId;
		int qty = 1;
		
		try {
			productId = Integer.parseInt(productIdParam);
			if(qtyParam != null && !qtyParam.isBlank()) {
				qty = Integer.parseInt(qtyParam);
			}
		} catch (NumberFormatException e) {
			resp.sendRedirect(req.getContextPath() + "/products");
			return;
		}
		
		if (qty <=0) {
			qty = 1;
		}
		
		HttpSession session = req.getSession();
		
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cartMap");
		
		if (cart == null) {
			cart = new HashMap<>();
		}
		
		int currentQty = cart.getOrDefault(productIdParam, 0);
		cart.put(productId, currentQty + qty);
		
		
        // Save back to session
        session.setAttribute("cartMap", cart);

        // Option: keep track of total items in cart
        int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
        session.setAttribute("cartCount", totalItems);

        // Redirect back to products or cart
        resp.sendRedirect(req.getContextPath() + "/products");
	}
	

}
