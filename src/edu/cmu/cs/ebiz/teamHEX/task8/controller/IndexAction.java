package edu.cmu.cs.ebiz.teamHEX.task8.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
		
		/**
		 * Get current city and state name from ip address
		 * */
//		if (session.getAttribute("currCity") == null) {
//			System.out.println( request.getRemoteAddr());
//			Document doc = Jsoup.connect("http://www.geoplugin.net/xml.gp?ip=" + request.getRemoteAddr()).get();
//			session.setAttribute("currCity", "Pittsburgh");//doc.getElementsByTag("geoplugin_city").get(0).text());
//			session.setAttribute("currState", doc.getElementsByTag("geoplugin_region").get(0).text());				
//		}
		
		try {
			
			if (request.getParameter("hidden_city") != null && !request.getParameter("hidden_city").equals("")) {
				String city = (String) request.getParameter("hidden_city");
				if (city.indexOf(",") != -1) {
					city = city.split(",")[0];
				}
				session.setAttribute("currCity", city);
				//System.out.println(city);
				if (session.getAttribute("currCityPhoto") == null) {
					session.setAttribute("currCityPhoto", flickr.fetchPhotos((String) session.getAttribute("currCity"), 5));
					session.setAttribute("currCityTrend", new ArrayList<String>() {{add("Trend1");add("Trend2");}});//twitter.searchTrends((String) session.getAttribute("currCity")));
					
					ArrayList<String> topicList = new ArrayList<String>();
					topicList=flickr.getListOfDiscussionsForGroup("2825475%40N22");
					
					
					System.out.println(topicList);
					
					ArrayList<String> topicDisplayList = new ArrayList<String>();
					ArrayList<String> replyDisplayList = new ArrayList<String>();
//					for(int i=0;i<topicList.size();i++){
//						if(i==0){
//							replyDisplayList=flickr.getListOfRepliesForTopics("2825475%40N22", topicList.get(i));				
//						} else if(i%2==0){
//							replyDisplayList=flickr.getListOfRepliesForTopics("2825475%40N22", topicList.get(i));
//						}else{
//							topicDisplayList.add(topicList.get(i));	
//						}
//					}
					session.setAttribute("topics",topicDisplayList );
					session.setAttribute("replies", replyDisplayList);
					
				}

				return "index.do";
			}
			
			if (session.getAttribute("currCityPhoto") == null && session.getAttribute("currCity") != null) {
				session.setAttribute("currCityPhoto", flickr.fetchPhotos((String) session.getAttribute("currCity")+" city", 5));
				session.setAttribute("currCityTrend", twitter.searchTrends((String) session.getAttribute("currCity")));
				ArrayList<String> topicList = new ArrayList<String>();
				topicList=flickr.getListOfDiscussionsForGroup("2825475%40N22");
				ArrayList<String> topicDisplayList = new ArrayList<String>();
				ArrayList<String> replyDisplayList = new ArrayList<String>();
				for(int i=0;i<topicList.size();i++){
					if(i==0){
						replyDisplayList=flickr.getListOfRepliesForTopics("2825475%40N22", topicList.get(i));				
					} else if(i%2==0){
						replyDisplayList=flickr.getListOfRepliesForTopics("2825475%40N22", topicList.get(i));
					}else{
						topicDisplayList.add(topicList.get(i));	
					}
				}
				session.setAttribute("topics",topicDisplayList );
				session.setAttribute("replies", replyDisplayList);

			}
			
			if (session.getAttribute("token") == null) {
				if (session.getAttribute("frob") == null) {
					flickr.frob = flickr.getFrob();
					session.setAttribute("frob", flickr.frob);
				}
				
				flickr.token = flickr.getToken(flickr.frob);
				if (flickr.token == null) {
					request.setAttribute("authUrl", flickr.getUserAuthorizationLink(flickr.frob));
					return "index.jsp";
				}
				session.setAttribute("token", flickr.token);
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
			
			flickr.getListOfDiscussionsForGroup("2825475%40N22");

			

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
						request.setAttribute("city1sportsscore", score1);
						request.setAttribute("city1sports", city1);
						request.setAttribute("city2sportsscore", score2);
						request.setAttribute("city2sports", city2);
						request.setAttribute("sports", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "restaurants":
						score1 = twitter.getCountOfRestaurants(city1);
						score2 = twitter.getCountOfRestaurants(city2);
						request.setAttribute("city1resscore", score1);
						request.setAttribute("city1res", city1);
						request.setAttribute("city2resscore", score2);
						request.setAttribute("city2res", city2);
						request.setAttribute("restaurants", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "employment":
						score1 = twitter.getCountOfJobs(city1);
						score2 = twitter.getCountOfJobs(city2);
						request.setAttribute("city1jobscore", score1);
						request.setAttribute("city1job", city1);
						request.setAttribute("city2jobscore", score2);
						request.setAttribute("city2job", city2);
						request.setAttribute("employment", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "celebrity":
						score1 = twitter.getCountOfCelebrity(city1);
						score2 = twitter.getCountOfCelebrity(city2);
						request.setAttribute("city1celscore", score1);
						request.setAttribute("city1cel", city1);
						request.setAttribute("city2celscore", score2);
						request.setAttribute("city2cel", city2);
						request.setAttribute("celebrity", score1 >= score2 ? city2 : city1);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "education":
						score1 = twitter.getCountOfEducation(city1);
						score2 = twitter.getCountOfEducation(city2);
						request.setAttribute("city1eduscore", score1);
						request.setAttribute("city1edu", city1);
						request.setAttribute("city2eduscore", score2);
						request.setAttribute("city2edu", city2);
						request.setAttribute("education", score1 >= score2 ? city1 : city2);
						total1 += score1 * 1;
						total2 += score2 * 1;
						break;
					case "crime":
						score1 = twitter.getCountOfCrime(city1);
						score2 = twitter.getCountOfCrime(city2);
						request.setAttribute("city1criscore", score1);
						request.setAttribute("city1cri", city1);
						request.setAttribute("city2criscore", score2);
						request.setAttribute("city2cri", city2);
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
			} else if (form.getAction().equals("change")) {
				session.setAttribute("currCity", form.getLocal());
				session.setAttribute("currCityPhoto", flickr.fetchPhotos((String) session.getAttribute("currCity"), 5));
				session.setAttribute("currCityTrend", twitter.searchTrends((String) session.getAttribute("currCity")));

				return "index.jsp";
			} 
			
			return "index.jsp";
		} catch (FormBeanException | IOException | XPathExpressionException | XMLStreamException | ParserConfigurationException | SAXException e) {
        	errors.add(e.getMessage());
        	return "index.jsp";
		}
	}
}
