package net.lulichn.todo.controller;

import net.lulichn.todo.repository.InMemoryDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var data = req.getParameter("data");
		System.out.println(data);

		InMemoryDb.put(data);

		resp.sendRedirect("/");
	}
}

