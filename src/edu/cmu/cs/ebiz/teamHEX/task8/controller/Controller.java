package edu.cmu.cs.ebiz.teamHEX.task8.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.ebiz.teamHEX.task8.model.Model;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());

		Action.add(new IndexAction(model));
		Action.add(new Callback(model));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		System.out.println("nextpage: " + nextPage);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		
		
//		
//		if (servletPath.indexOf("hexcallback") != -1) {
//			System.out.println("callback function is caught");
//			return Action.perform("index.do", request);
//		}

		if (action.equals("welcome")) {
			// User is logged in, but at the root of our web app
			return Action.perform("index.do", request);
		}

		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}
		
		if (nextPage.contains("://")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"	+ nextPage);
			d.forward(request, response);
			return;
		}
		
//		if (!nextPage.isEmpty()) {
//			response.sendRedirect(nextPage.startsWith("http") ? nextPage: "http://" + nextPage);
//			return;
//		}

		throw new ServletException(Controller.class.getName() + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
