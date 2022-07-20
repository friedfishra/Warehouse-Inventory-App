package com.skillstorm.servlets;

import java.io.IOException;
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
import com.skillstorm.services.URLParserService;

@WebServlet(urlPatterns = "/items/*")
public class ItemIdServlet extends HttpServlet{

	private static final long serialVersionUID = 2018470646713236311L;
	
	ItemDAO dao = new ItemDAOImpl();
	ObjectMapper mapper = new ObjectMapper();
	URLParserService urlService = new URLParserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int id = urlService.extractIdFromURL(req.getPathInfo());
			Item item = dao.findById(id);
			if(item != null) {
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(item));
				
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No item with the provided Id found")));
			}
		} catch (Exception e) {
			LinkedList<Item> items = dao.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(items));
		}
		
	}
	
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int id = urlService.extractIdFromURL(req.getPathInfo());
			dao.deleteById(id);
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString("Successfully deleted item"));
			resp.setStatus(200);
			
		} catch (Exception e) {
			resp.setStatus(404);
			resp.getWriter().print(mapper.writeValueAsString("Item could not be deleted"));
			
		}
		
	}

}
