package edu.cmu.cs.ebiz.teamHEX.task8.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import edu.cmu.cs.ebiz.teamHEX.task8.model.Flicker;
import edu.cmu.cs.ebiz.teamHEX.task8.model.Model;
import edu.cmu.cs.ebiz.teamHEX.task8.model.Twitter;

public class LoginAction extends Action {
	//private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);

	private Twitter twitter;
	private Flicker flicker;
	
	public LoginAction(Model model) {
		twitter = model.getTwitter();
		flicker = model.getFlicker();
	}

	public String getName() { return "login.do"; }

	public String perform(HttpServletRequest request) {
		try {
			twitter.fetchTweetsExample();
			flicker.fetchPhotoExample();
		} catch (IOException | XPathExpressionException | XMLStreamException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return "login.jsp";
	}
}
