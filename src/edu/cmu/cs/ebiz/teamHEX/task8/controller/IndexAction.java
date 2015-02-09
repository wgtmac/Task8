package edu.cmu.cs.ebiz.teamHEX.task8.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import edu.cmu.cs.ebiz.teamHEX.task8.model.Flickr;
import edu.cmu.cs.ebiz.teamHEX.task8.model.Model;
import edu.cmu.cs.ebiz.teamHEX.task8.model.Twitter;

public class IndexAction extends Action {
	//private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);

	private Twitter twitter;
	private Flickr flicker;
	
	public IndexAction(Model model) {
		twitter = model.getTwitter();
		flicker = model.getFlickr();
	}

	public String getName() { return "index.do"; }

	public String perform(HttpServletRequest request) {
		try {
			twitter.fetchTweetsExample();
			flicker.fetchPhotoExample();
		} catch (IOException | XPathExpressionException | XMLStreamException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return "index.jsp";
	}
}