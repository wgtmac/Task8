package edu.cmu.cs.ebiz.teamHEX.task8.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private final String twitterKey;
	private final String twitterSecret;
	private final String flickrKey;
	
	private final Twitter twitter;
	private final Flickr flicker;
	
	public Model(ServletConfig config) throws ServletException {
		twitterKey = config.getInitParameter("TwitterKey");
		twitterSecret  = config.getInitParameter("TwitterSecret");
		flickrKey =  config.getInitParameter("FlickrKey");
		
		twitter = new Twitter(twitterKey, twitterSecret);
		flicker = new Flickr(flickrKey);
	}

	public Twitter getTwitter () { return twitter; }
	public Flickr getFlickr () { return flicker; }
}
