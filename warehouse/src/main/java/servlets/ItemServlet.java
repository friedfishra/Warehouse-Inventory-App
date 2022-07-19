package servlets;

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

import models.Item;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet{

	private static final long serialVersionUID = 904689259624737580L;
	
	ItemDAO dao = new ItemDAOImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hello Servlet");
		
		resp.setStatus(200);
		resp.sendRedirect("public/index.html");
		
		LinkedList<Item> items = dao.findAll();
		if (items != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(items));
		} else {
			resp.setStatus(404);
		}
		System.out.println(items);
		
	}
	
	

}
