package edu.cmu.cs.ebiz.teamHEX.task8.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Flickr extends WebAccessor {
	private final String flickrKey;

	public Flickr(String key) {
		flickrKey = key;
	}

	public static final String SEARCH = "flickr.photos.search";

	/**
	 * Fetch photos by keyword
	 * 
	 * @param keyword
	 *            : keyword of photos
	 * @param count
	 *            : maximum amount of photos
	 * @return: list of urls of photos
	 */
	public ArrayList<String> fetchPhotos(String keyword, int count)
			throws IOException, XMLStreamException, XPathExpressionException,
			ParserConfigurationException, SAXException {
		ArrayList<String> res = new ArrayList<String>();

		HttpsURLConnection connection = null;

		URL url = new URL("https://api.flickr.com/services/rest/?method="
				+ SEARCH + "&api_key=" + flickrKey + "&per_page=" + count
				+ "&text=" + URLEncoder.encode(keyword, "UTF-8")
				+ "&tag_mode=all&content_type=1&sort=relevance");

		connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);

		writeRequest(connection, "");

		String filename = "test.xml";

		BufferedReader br = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				new File(filename)));
		String nextline;
		while ((nextline = br.readLine()) != null) {
			bw.write(nextline);// fastest the way to read and write
		}
		br.close();
		bw.close();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new FileInputStream(new File("test.xml")));

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();

		NodeList nodeList = (NodeList) xpath.evaluate("//photos/photo", doc,
				XPathConstants.NODESET);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			String id = (String) xpath.evaluate("@id", node,
					XPathConstants.STRING);
			String server = (String) xpath.evaluate("@server", node,
					XPathConstants.STRING);
			String secret = (String) xpath.evaluate("@secret", node,
					XPathConstants.STRING);

			String flickrurl = "http://static.flickr.com/" + server + "/" + id
					+ "_" + secret + ".jpg";
			System.out.println(flickrurl);

			res.add(flickrurl);
		}

		return res;
	}

	public ArrayList<String> getListOfDiscussionsForGroup(String group_id,String api_token) throws IOException {
		HttpsURLConnection connection = null;

		// get the oauth token here
		String token = api_token;

		// get the api_key
		String api_key = flickrKey;

		// put the api_sig here
		String sign = null;

		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.topics.getList&api_key="
				+ "&group_id="
				+ group_id
				+ "&format=json&nojsoncallback=1&auth_token="
				+ token
				+ "&api_sig=" + sign;
		System.out.println(queryUrl);

		try {
			URL url = new URL(queryUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("Host", "api.twitter.com");
			// connection.setRequestProperty("User-Agent", "Task8HEX");
			// connection.setRequestProperty("Authorization", "Bearer " +
			// token);
			connection.setUseCaches(false);

			ArrayList<String> resultArrayList = new ArrayList<String>();
			JSONArray msgArray = (JSONArray) JSONValue
					.parse(readResponse(connection));

			JSONObject jsonObject = (JSONObject) msgArray.iterator().next();
			msgArray = (JSONArray) jsonObject.get("topics");
			Iterator<JSONObject> iterator = msgArray.iterator();
			while (iterator.hasNext()) {
				JSONObject next = iterator.next();
				JSONArray topicArray = (JSONArray) next.get("topic");
				Iterator<JSONObject> iterator1 = topicArray.iterator();
				while (iterator1.hasNext()) {
					JSONObject next1 = iterator1.next();
					JSONArray messageArray = (JSONArray) next1.get("message");
					Iterator<JSONObject> iterator2 = messageArray.iterator();
					while (iterator2.hasNext()) {
						JSONObject last = iterator2.next();
						String text = (String) last.get("_content");
						resultArrayList.add(text);
					}

				}
				
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
	

	public ArrayList<String> getListOfRepliesForTopics(String group_id,String topic_id,
			String api_token) throws IOException {
		HttpsURLConnection connection = null;

		// get the oauth token here
		String token = api_token;

		// get the api_key
		String api_key = flickrKey;

		// put the api_sig here
		String sign = null;

		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.replies.getList&api_key="
				+ "&group_id="
				+ group_id+"&topic_id="+topic_id
				+ "&format=json&nojsoncallback=1&auth_token="
				+ token
				+ "&api_sig=" + sign;
		System.out.println(queryUrl);

		try {
			URL url = new URL(queryUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("Host", "api.twitter.com");
			// connection.setRequestProperty("User-Agent", "Task8HEX");
			// connection.setRequestProperty("Authorization", "Bearer " +
			// token);
			connection.setUseCaches(false);

			ArrayList<String> resultArrayList = new ArrayList<String>();
			JSONArray msgArray = (JSONArray) JSONValue
					.parse(readResponse(connection));

			JSONObject jsonObject = (JSONObject) msgArray.iterator().next();
			msgArray = (JSONArray) jsonObject.get("replies");
			Iterator<JSONObject> iterator = msgArray.iterator();
			while (iterator.hasNext()) {
				JSONObject next = iterator.next();
				JSONArray topicArray = (JSONArray) next.get("topic");
				Iterator<JSONObject> iterator1 = topicArray.iterator();
				while (iterator1.hasNext()) {
					JSONObject next1 = iterator1.next();
					JSONArray messageArray = (JSONArray) next1.get("message");
					Iterator<JSONObject> iterator2 = messageArray.iterator();
					while (iterator2.hasNext()) {
						JSONObject last = iterator2.next();
						String text = (String) last.get("_content");
						resultArrayList.add(text);
					}

				}
				JSONArray replyArray = (JSONArray) next.get("reply");
				Iterator<JSONObject> replyIterator = replyArray.iterator();
				while (replyIterator.hasNext()) {
					JSONObject next1 = replyIterator.next();
					JSONArray messageArray = (JSONArray) next1.get("message");
					Iterator<JSONObject> iterator2 = messageArray.iterator();
					while (iterator2.hasNext()) {
						JSONObject last = iterator2.next();
						String text = (String) last.get("_content");
						resultArrayList.add(text);
					}

				}
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
	
	public void postNewDiscussion(String group_id,String token, String subject, String message) throws IOException {
		// get the api_key
		String api_key = flickrKey;

		// put the api_sig here
		String sign = null;
		
		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.topics.add&api_key="+api_key+"&group_id="+group_id+"&subject="+subject+"&message="+message+"&format=json&nojsoncallback=1&auth_token="+token+"&api_sig="+sign;
		System.out.println(queryUrl);

	}

	public void postNewReply(String group_id,String topic_id, String token, String message) throws IOException {
		// get the api_key
		String api_key = flickrKey;

		// put the api_sig here
		String sign = null;
		
		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.replies.add&api_key="+api_key+"&group_id="+group_id+"&topic_id="+topic_id+"&message="+message+"&format=json&nojsoncallback=1&auth_token="+token+"&api_sig="+sign;
		System.out.println(queryUrl);

	}

	
	

	public void fetchPhotoExample() throws XPathExpressionException,
			IOException, XMLStreamException, ParserConfigurationException,
			SAXException {
		// fetchPhotos("Kobe Bryant", 3);
	}
}
