package edu.cmu.cs.ebiz.teamHEX.task8.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs.ebiz.teamHEX.task8.model.Model;

public class AboutAction extends Action {

	public String getName() { return "about.do"; }
	

	public AboutAction(Model model) {
	}

	public String perform(HttpServletRequest request) {		
		return "about.jsp";
	}
}
