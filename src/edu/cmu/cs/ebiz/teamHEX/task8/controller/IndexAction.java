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

public class IndexAction extends Action {
	private FormBeanFactory<IndexForm> formBeanFactory = FormBeanFactory.getInstance(IndexForm.class);

	private Twitter twitter;
	private Flickr flickr;
	
	public IndexAction(Model model) {
		twitter = model.getTwitter();
		flickr = model.getFlickr();
	}

	public String getName() { return "index.do"; }

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		String frob = null;
		String token = null;
		
		try {
			if (session.getAttribute("token") == null) {
				if (session.getAttribute("frob") == null) {
					frob = flickr.getFrob();
					session.setAttribute("frob", frob);
				} else {
					frob = (String) session.getAttribute("frob");
				}
				
				token = flickr.getToken(frob);
				if (token == null) {
					request.setAttribute("authUrl", flickr.getUserAuthorizationLink(frob));
					return "index.jsp";
				}
				session.setAttribute("token", token);
			} else {
				token = (String) session.getAttribute("token");
			}
			
			IndexForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "index.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "index.jsp";
			}
			
			if (form.getAction().equals("compare")) {
				String city1 = form.getCities1();
				String city2 = form.getCities2();
				int score1 = 0, score2 = 0;
				int total1 = 0, total2 = 0;
				
				for (String opt : form.getComparisonList()) {
					switch (opt) {
					case "sports":
						score1 = twitter.getCountOfSports(city1);
						score2 = twitter.getCountOfSports(city2);
						request.setAttribute("sports", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "restaurants":
						score1 = twitter.getCountOfRestaurants(city1);
						score2 = twitter.getCountOfRestaurants(city2);
						request.setAttribute("restaurants", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "employment":
						score1 = twitter.getCountOfJobs(city1);
						score2 = twitter.getCountOfJobs(city2);
						request.setAttribute("employment", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "celebrity":
						score1 = twitter.getCountOfCelebrity(city1);
						score2 = twitter.getCountOfCelebrity(city2);
						request.setAttribute("celebrity", score1 >= score2 ? city2 : city1);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "education":
						score1 = twitter.getCountOfEducation(city1);
						score2 = twitter.getCountOfEducation(city2);
						request.setAttribute("education", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "crime":
						score1 = twitter.getCountOfCrime(city1);
						score2 = twitter.getCountOfCrime(city2);
						request.setAttribute("crime", score1 >= score2 ? city2 : city1);
						total1 += (100 - score1) * 2;
						total2 += (100 - score2) * 2;
						break;
					}
				}
				
				request.setAttribute("city1", total1 >= total2 ? city1 : city2);
				request.setAttribute("city2", total1 >= total2 ? city2 : city1);
				
				score1 = (int) ((0.0 +total1) / (total1 +total2 + 0.1) * 100);
				score2 = 100 - score1;
				
				request.setAttribute("cityScore1", total1 >= total2 ? Integer.toString(score1) : Integer.toString(score2));
				request.setAttribute("cityScore2", total1 >= total2 ? Integer.toString(score2) : Integer.toString(score1));
				
				city1 = (String) request.getAttribute("city1");
				city2 = (String) request.getAttribute("city2");
				
				request.setAttribute("trend1", twitter.searchTrends(city1));
				request.setAttribute("trend2", twitter.searchTrends(city2));
				
				request.setAttribute("flickrpic1", flickr.fetchPhotos(city1, 5));
				request.setAttribute("flickrpic2", flickr.fetchPhotos(city2, 5));
				
				return "results.jsp";
			} else {
				
				return "index.jsp";
			}
		} catch (FormBeanException | IOException | XPathExpressionException | XMLStreamException | ParserConfigurationException | SAXException e) {
        	errors.add(e.getMessage());
        	return "index.jsp";
		}
	}
}
