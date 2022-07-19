package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.ItemDAO;
import com.skillstorm.daos.ItemDAOImpl;
import com.skillstorm.models.Item;
import com.skillstorm.models.NotFound;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet{

	private static final long serialVersionUID = 904689259624737580L;
	
	ItemDAO dao = new ItemDAOImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hello Servlet");
		
//		resp.setStatus(200);
//		resp.sendRedirect("public/index.html");
		
		LinkedList<Item> items = dao.findAll();
		if (items != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(items));
		} else {
			resp.setStatus(404);
			resp.getWriter().print(mapper.writeValueAsString("No item with the provided Id found"));
		}
		System.out.println(items);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST WORKING!");
		InputStream reqBody = req.getInputStream();
		Item newItem = mapper.readValue(reqBody, Item.class);
		
		newItem = dao.save(newItem);
		
		if(newItem != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(newItem));
			resp.setStatus(201);
			
		} else {
			resp.setStatus(400);
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("Unable to create item")));
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PUT WORKING!");
		InputStream reqBody = req.getInputStream();
		Item item = mapper.readValue(reqBody, Item.class);
		if(item != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(item));
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("Unable to update item")));
		}
		
	}
	
	

}
