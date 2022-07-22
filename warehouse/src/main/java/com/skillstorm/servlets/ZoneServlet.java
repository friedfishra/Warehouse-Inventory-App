package com.skillstorm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.daos.ZoneDAO;
import com.skillstorm.daos.ZoneDAOImpl;
import com.skillstorm.models.NotFound;
import com.skillstorm.services.URLParserService;

@WebServlet(urlPatterns="/zones/*")
public class ZoneServlet extends HttpServlet{


	private static final long serialVersionUID = -7504123315932862652L;
	
	ZoneDAO dao = new ZoneDAOImpl();
	ObjectMapper mapper = new ObjectMapper();
	URLParserService urlService = new URLParserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Zones working!");
		try {
			int id = urlService.extractIdFromURL(req.getPathInfo());
			int itemCount = dao.getItemCountInZone(id);
			if(itemCount != -1) {
				resp.setStatus(200);
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(itemCount));
				
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("Incorrect Zone")));
			}
		} catch (Exception e) {
			
		}
	}

}
