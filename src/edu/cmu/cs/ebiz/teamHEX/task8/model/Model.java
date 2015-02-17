package edu.cmu.cs.ebiz.teamHEX.task8.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private final String twitterKey;
	private final String twitterSecret;
	private final String flickrKey;
	private final String flickrSecret;
	
	private final Twitter twitter;
	private final Flickr flicker;
	
	private static final HashSet<String> citySet = new HashSet<String>();	
	public static boolean isCityExist (String city) {
		return citySet.contains(city);
	}
	
	public Model(ServletConfig config) throws ServletException {
		twitterKey = config.getInitParameter("TwitterKey");
		twitterSecret  = config.getInitParameter("TwitterSecret");
		flickrKey =  config.getInitParameter("FlickrKey");
		flickrSecret =  config.getInitParameter("FlickrSecret");
		
		twitter = new Twitter(twitterKey, twitterSecret);
		flicker = new Flickr(flickrKey, flickrSecret);		
		
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(config.getServletContext().getRealPath("") + "/resources/cities.csv"));
	        String line = br.readLine();
	        while (line != null) {
	        	citySet.add(line);
	            line = br.readLine();
	        }
	        br.close();
	    } catch (Exception e) {
	    	System.out.println("Errors happened when reading csv file.");
	    }
	}

	public Twitter getTwitter () { return twitter; }
	public Flickr getFlickr () { return flicker; }
}
