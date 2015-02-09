package edu.cmu.cs.ebiz.teamHEX.task8.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Twitter extends WebAccessor {
	private final String twitterKey;
	private final String twitterSecret;
	
    public Twitter (String key, String secret){
    	twitterKey = key;
    	twitterSecret = secret;
    }  
    
    /**
     * Generate Base64 code of keys for OAuth
     */
	private String encodeKeys() {
		try {
			String encodedConsumerKey = URLEncoder.encode(twitterKey, "UTF-8");
			String encodedConsumerSecret = URLEncoder.encode(twitterSecret,"UTF-8");

			String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
			byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());

			return new String(encodedBytes);
		} catch (UnsupportedEncodingException e) {
			return new String();
		}
	}
	
    /**
     * Application-Only Authentication
     */
	public String getAuthenticationToken(String oauthUrl) throws IOException {
		HttpsURLConnection connection = null;
		String encodedCredentials = encodeKeys();

		try {
			URL url = new URL(oauthUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "Task8HEX");
			connection.setRequestProperty("Authorization", "Basic "	+ encodedCredentials);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			connection.setRequestProperty("Content-Length", "29");
			connection.setUseCaches(false);

			writeRequest(connection, "grant_type=client_credentials");

			// Parse the JSON response into a JSON mapped object to fetch fields
			JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));

			if (obj != null) {
				String tokenType = (String) obj.get("token_type");
				String token = (String) obj.get("access_token");

				return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
			}
			return new String();
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
    /**
     * @author: Team HEX
     * Get a place WOEID from http://isithackday.com instead of Yahoo!
     * WARNING: DO NOT COPY OUR CODE HERE! 
     */
	public String getWoeid(String city) throws IOException {
		Document doc = null;
		boolean  failed = true;
		while (failed) {
			try{
				doc = Jsoup.connect("http://isithackday.com/geoplanet-explorer/index.php?start=" + URLEncoder.encode(city.trim(), "UTF-8")).get();
				failed = false;
			} catch (SocketTimeoutException e) {
				System.out.println("Unfortunately, socket timeout happened but maybe no one knew...");
				continue;
			}
		}

		String[] response = doc.select(".citylist").text().split(" ");
		String ret = null;
		for (int i = 0; i < response.length; i++) {
			if (response[i].equals("WOEID:")) {
				ret = response[i + 1];
				break;
			}
		}
		
		return ret;
	}
	
    /**
     * Fetch trends for a city
     */
	public ArrayList<String> searchTrends (String city) throws IOException {
		HttpsURLConnection connection = null;
		// get OAuth Token
		String token = getAuthenticationToken("https://api.twitter.com/oauth2/token");
		
		// construct query request
		String queryUrl = "https://api.twitter.com/1.1/trends/place.json?id=" +getWoeid(city);		
		System.out.println(queryUrl);
		
		try {
			URL url = new URL(queryUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "Task8HEX");
			connection.setRequestProperty("Authorization", "Bearer " + token);
			connection.setUseCaches(false);

			ArrayList<String>resultArrayList = new ArrayList<String>();
			JSONArray msgArray = (JSONArray) JSONValue.parse(readResponse(connection));
			
			JSONObject jsonObject= (JSONObject) msgArray.iterator().next();
			msgArray = (JSONArray) jsonObject.get("trends");
			Iterator<JSONObject> iterator = msgArray.iterator();
			while (iterator.hasNext()) {
				JSONObject next = iterator.next();
				String text = (String) next.get("name");
				resultArrayList.add(text);
			}

			return resultArrayList;
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
    /**
     * Fetch tweets
     */
	public int searchTweets (HashMap<String, String> parameters, ArrayList<String> resultArrayList) throws IOException {
		HttpsURLConnection connection = null;
		// get OAuth Token
		String token = getAuthenticationToken("https://api.twitter.com/oauth2/token");
		
		// construct query request
		String queryUrl = "https://api.twitter.com/1.1/search/tweets.json?count=100";
		for (String key : parameters.keySet()) {
			queryUrl += "&" + key +"=" + URLEncoder.encode(parameters.get(key), "UTF-8");
		}
		
		System.out.println(queryUrl);
		
		try {
			URL url = new URL(queryUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "Task8HEX");
			connection.setRequestProperty("Authorization", "Bearer " + token);
			connection.setUseCaches(false);

			JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));
			
			JSONArray msg = (JSONArray) obj.get("statuses");
			Iterator<JSONObject> iterator = msg.iterator();
			while (iterator.hasNext()) {
				JSONObject next = iterator.next();
				String text = (String) next.get("text");
				resultArrayList.add(text);
			}

			return resultArrayList.size();
		} catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	public int getCount (String keyWord, int datesBefore) throws IOException {
		HashMap<String, String> parameters = new HashMap<String, String> ();
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -datesBefore);
	    
		parameters.put("q", keyWord +" since:" + formatter.format(cal.getTime()));
		parameters.put("result_type", "recent");
		parameters.put("src", "typd");
		
		ArrayList<String> resultArrayList = new ArrayList<String>();
		return searchTweets(parameters, resultArrayList);
	}
	
	public int getCountForRestaurants (String keyWord, int datesBefore) throws IOException {
		HashMap<String, String> parameters = new HashMap<String, String> ();
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -datesBefore);
	    
		parameters.put("q", keyWord +" since:" + formatter.format(cal.getTime()));
		parameters.put("q", keyWord +" since:" + formatter.format(cal.getTime()));
		parameters.put("result_type", "recent");
		parameters.put("src", "typd");
		
		ArrayList<String> resultArrayList = new ArrayList<String>();
		return searchTweets(parameters, resultArrayList);
	}

	public int getCountOfSports (String city) throws IOException {
		return getCount(city + " @espn", 1);
	}
	
	public int getCountOfEducation (String city) throws IOException {
		return getCount(city + " graduate", 1);
	}
	
	public int getCountOfCrime (String city) throws IOException {
		return getCount(city + " crime", 1);
	}
	
	public int getCountOfJobs (String city) throws IOException {
		return getCount(city + " jobs", 1);
	}
	public int getCountOfRestaurants (String city) throws IOException {
		return getCountForRestaurants(city + " restaurant OR pubs OR nightclubs OR food%3A)", 0);
	}
	
	public void fetchTweetsExample () throws IOException {	
		//System.out.println(searchTrends("Pittsburgh"));
		//System.out.println(getCountOfCrime("Los Angeles"));
//		System.out.println(getCountOfCrime("New York"));
//		System.out.println(getCountOfCrime("Pittsburgh"));
//		System.out.println(getCountOfCrime("Boston"));
		System.out.println(getCountOfCrime("Los Angeles"));
		System.out.println(getCountOfCrime("New York"));
		System.out.println(getCountOfCrime("Pittsburgh"));
		System.out.println(getCountOfCrime("Boston"));
		System.out.println(getCountOfJobs("San Francisco"));
		System.out.println(getCountOfRestaurants("Los Angeles"));
		System.out.println(getCountOfRestaurants("Pittsburgh"));
	}
}
