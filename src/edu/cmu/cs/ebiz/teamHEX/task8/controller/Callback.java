package edu.cmu.cs.ebiz.teamHEX.task8.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.xml.sax.SAXException;

import edu.cmu.cs.ebiz.teamHEX.task8.formbean.IndexForm;
import edu.cmu.cs.ebiz.teamHEX.task8.model.Flickr;
import edu.cmu.cs.ebiz.teamHEX.task8.model.Model;
import edu.cmu.cs.ebiz.teamHEX.task8.model.Twitter;

public class Callback extends Action {

	public String getName() { return "Callback.do"; }
	
	private Twitter twitter;
	private Flickr flickr;
	
	public Callback(Model model) {
		twitter = model.getTwitter();
		flickr = model.getFlickr();
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		

		try {
			String frob = (String) session.getAttribute("frob");
			String token;
			
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			token = flickr.getToken(frob);

//			if (token == null) {
//				return flickr.getUserAuthorizationLink(frob);
//			} else {
//				session.setAttribute("token", token);
//			}

		
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "tt.jsp";
	}
}
