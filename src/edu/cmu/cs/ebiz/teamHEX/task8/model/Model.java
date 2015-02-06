package edu.cmu.cs.ebiz.teamHEX.task8.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Model {
	private final String twitterKey;
	private final String twitterSecret;
	private final String flickerKey;
	
	private final Twitter twitter;
	private final Flicker flicker;
	
	public Model(ServletConfig config) throws ServletException {
		twitterKey = config.getInitParameter("TwitterKey");
		twitterSecret  = config.getInitParameter("TwitterSecret");
		flickerKey =  config.getInitParameter("FlickerKey");
		
		twitter = new Twitter(twitterKey, twitterSecret);
		flicker = new Flicker(flickerKey);
	}

	public Twitter getTwitter () { return twitter; }
	public Flicker getFlicker () { return flicker; }
}
