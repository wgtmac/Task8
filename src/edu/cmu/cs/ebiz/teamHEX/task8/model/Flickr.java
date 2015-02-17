package edu.cmu.cs.ebiz.teamHEX.task8.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
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
	private final String flickrSecret;
	
	public String token = null;
	public String frob = null;
	
    public Flickr (String key, String secret){ 
    	flickrKey = key; 
    	flickrSecret = secret;
    }  
    
    public static final String SEARCH = "flickr.photos.search";
    public static final String FROB = "flickr.auth.getFrob";
    public static final String TOKEN = "flickr.auth.getToken";
    
    /**
     * Fetch photos by keyword
     * @param keyword:  keyword of photos
     * @param count:      maximum amount of photos
     * @return:	list of urls of photos
     */
    
    
	public ArrayList<String> fetchPhotos(String keyword, int count) throws  IOException, XMLStreamException, XPathExpressionException, ParserConfigurationException, SAXException {
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

			String id = (String) xpath.evaluate("@id", node, XPathConstants.STRING);
			String server = (String) xpath.evaluate("@server", node, XPathConstants.STRING);
			String secret = (String) xpath.evaluate("@secret", node, XPathConstants.STRING);

			String flickrurl = "http://static.flickr.com/" + server + "/" + id 	+ "_" + secret + ".jpg";
			//System.out.println(flickrurl);

			res.add(flickrurl);
		}

		return res;
	}

	public ArrayList<String> getListOfDiscussionsForGroup(String group_id) throws IOException, XMLStreamException, XPathExpressionException, ParserConfigurationException, SAXException {
		
		ArrayList<String> resultArrayList = new ArrayList<String>();

		HttpsURLConnection connection = null;

		// get the oauth token here
		String token = "72157650868466245-318784e142f3bb6c";

		// get the api_key
		String api_key = "c168d339a5f68a399b43b8a12cf6d5d1";

        String sign = "8568b58571bb8fb50e3706aef34cf0bd";
		
		/**
		 * The API signature must be MD5 encoded and appended to the request
		 */
		
		

		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.topics.getList&api_key="+api_key
				+ "&group_id="
				+ group_id
				+ "&format=rest&auth_token="
				+ token
				+ "&api_sig=" + sign;
		System.out.println(queryUrl);

		try {
			URL url = new URL(queryUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			
			

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

			NodeList nodeList = (NodeList) xpath.evaluate("//topics/topic", doc,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				String id = (String) xpath.evaluate("@id", node,
						XPathConstants.STRING);

				resultArrayList.add(id);


				String subject = (String) xpath.evaluate("@subject", node,
						XPathConstants.STRING);

				resultArrayList.add(subject);
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
	

	public ArrayList<String> getListOfRepliesForTopics(String group_id,String topic) throws IOException, XMLStreamException, XPathExpressionException, ParserConfigurationException, SAXException {
		HttpsURLConnection connection = null;

		// get the oauth token here
		String token = "72157650868466245-318784e142f3bb6c";

		// get the api_key
		String api_key = "c168d339a5f68a399b43b8a12cf6d5d1";

        String sign = "823ab8f9f8e4d25f15993ddc0628f6bb";
        
        String topic_id="72157650826742592";
		
		/**
		 * The API signature must be MD5 encoded and appended to the request
		 */ 

		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.replies.getList&api_key="+api_key
				+ "&group_id="
				+ group_id+"&topic_id="+topic_id
				+ "&format=rest&auth_token="
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

			NodeList nodeList = (NodeList) xpath.evaluate("//replies/topic", doc,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				String message = (String) xpath.evaluate("//replies/topic/message", node,
						XPathConstants.STRING);
				System.out.println("message="+message);

				resultArrayList.add(message);
			}
			NodeList nodeList1 = (NodeList) xpath.evaluate("//replies/reply", doc,
					XPathConstants.NODESET);

			for (int i = 0; i < nodeList1.getLength(); i++) {
				Node node = nodeList1.item(i);

				String message = (String) xpath.evaluate("//replies/reply/message", node,
						XPathConstants.STRING);
				System.out.println("message="+message);

				resultArrayList.add(message);
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
	
	public void postNewDiscussion(String group_id, String subject, String message) throws IOException {
		
		HttpsURLConnection connection = null;
		
		// get the api_key
		String api_key = flickrKey;
		
		// get the oauth token here
		String token = this.token;


        String sig = flickrSecret + "api_key" + flickrKey + "method" + "flickr.groups.discuss.topics.add";
		
		/**
		 * The API signature must be MD5 encoded and appended to the request
		 */
		String sign = MD5(sig); 
		
		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.topics.add&api_key="+api_key+"&group_id="+group_id+"&subject="+subject+"&message="+message+"&format=json&nojsoncallback=1&auth_token="+token+"&api_sig="+sign;
		System.out.println(queryUrl);
		try{
		URL url = new URL(queryUrl);
		connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		}catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

	}

	public void postNewReply(String group_id,String topic_id, String message) throws IOException {
		
		HttpsURLConnection connection = null;
		// get the api_key
		String api_key = flickrKey;
		// get the oauth token here
		String token = this.token;


        String sig = flickrSecret + "api_key" + flickrKey + "method" + "flickr.groups.discuss.replies.add";
		
		/**
		 * The API signature must be MD5 encoded and appended to the request
		 */
		String sign = MD5(sig); 
		
		String queryUrl = "https://api.flickr.com/services/rest/?method=flickr.groups.discuss.replies.add&api_key="+api_key+"&group_id="+group_id+"&topic_id="+topic_id+"&message="+message+"&format=json&nojsoncallback=1&auth_token="+token+"&api_sig="+sign;
		System.out.println(queryUrl);
		
		try{URL url = new URL(queryUrl);
		connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		}catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}


	}

	
	

	
	///////////////////////////////////////////////////////////////////////
	
	/**
	 * Get the MD5 hash of a text string
	 */
	public String MD5(String text)
	{
		String md5Text = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			md5Text = new BigInteger(1, digest.digest((text).getBytes())).toString(16);
		} catch (Exception e) {
			System.out.println("Error in call to MD5");
		}
		
        if (md5Text.length() == 31) {
            md5Text = "0" + md5Text;
        }
		return md5Text;
	}
	
	public String getFrob () throws SAXException, IOException, ParserConfigurationException {
		/**
		 * Request a frob to identify the login session. This call requires 
		 * a signature. The signature starts with your shared secret and
		 * is followed by your API key and the method name. The API key and
		 * method name are prepended by the words "api_key" and "method" as
		 * shown in the following line.
		**/
		String methodGetFrob = FROB;
		String sig = flickrSecret + "api_key" + flickrKey + "method" + methodGetFrob;
		
		/**
		 * The API signature must be MD5 encoded and appended to the request
		 */
		String signature = MD5(sig); 
			
		String request = "https://api.flickr.com/services/rest/?method=" + methodGetFrob + "&api_key=" + flickrKey + "&api_sig=" + signature;
		//System.out.println("GET frob request: " + request);
		
		URL url = new URL(request);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);
		writeRequest(connection, "");
		
		// Get the response body
		InputStream rstream = connection.getInputStream();
		
		/**
		 * Retrieve the XML response to the frob request and get the frob value.
		 */
		Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
		String frob = null;
		
		// Check if frob is in the response
		NodeList frobResponse = response.getElementsByTagName("frob");
		Node frobNode = frobResponse.item(0);
		if (frobNode != null) {
			frob = frobNode.getTextContent();
			System.out.println("Successfully retrieved frob: " + frob);
			return frob;
		} else {
			// Get Flickr error code and msg
			//NodeList error = response.getElementsByTagName("err");
			//String code = error.item(0).getAttributes().item(0).getTextContent();
			//String msg = error.item(0).getAttributes().item(1).getTextContent();
			//System.out.println("Flickr request failed with error code " + code + ", " + msg);
			return null;
		}
	}
	
	public String getUserAuthorizationLink(String frob) throws IOException, SAXException, ParserConfigurationException {
		/**
		 * Create a Flickr login link
		 * http://www.flickr.com/services/auth/?api_key=[api_key]&perms=[perms]&frob=[frob]&api_sig=[api_sig]
		 * We are using "write" for the perms value because we will be rotating an image. 
		 */
		String sig = flickrSecret + "api_key" + flickrKey + "frob" + frob + "permswrite";
		String signature = MD5(sig);
		String request = "http://www.flickr.com/services/auth/?api_key=" + flickrKey + "&perms=write&frob=" + frob + "&api_sig=" + signature;
		
		//System.out.println("Browse to the following flickr url to authenticate yourself and then press enter.");
		//System.out.println(request);
		
		
//		if (Desktop.isDesktopSupported()) {
//			Desktop desktop = Desktop.getDesktop();
//			try {
//				desktop.browse(new URI(request));
//			} catch (IOException | URISyntaxException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			Runtime runtime = Runtime.getRuntime();
//			try {
//				runtime.exec("xdg-open " + request);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		return request;
	}
	
	public String getToken (String frob) throws IOException, SAXException, ParserConfigurationException {
		/**
		 * Get auth token using frob. Once again, a signature is required
		 * for authenticated calls to the Flickr API.  
		 */
		String methodGetToken = TOKEN;
		String sig = flickrSecret + "api_key" + flickrKey + "frob" + frob + "method" + methodGetToken;
		String signature = MD5(sig);
		String request = "https://api.flickr.com/services/rest/?method=" + methodGetToken + "&api_key=" + flickrKey + "&frob=" + frob + "&api_sig=" + signature;
		//System.out.println("Token request: " + request);
		
		URL url = new URL(request);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);
		writeRequest(connection, "");
		
		// Get the response body
		InputStream rstream = connection.getInputStream();
		
		/**
		 * Retrieve the XML response to the frob request and get the frob value.
		 */
		Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
		
		String token = null;		
		// Check if token is in the response
		NodeList tokenResponse = response.getElementsByTagName("token");
		Node tokenNode = tokenResponse.item(0);
		if (tokenNode != null) {
			token = tokenNode.getTextContent();
			System.out.println("Successfully retrieved token: " + token);
			//System.out.println("Auth token successfully received.");
			return token;
		} else {
//			NodeList error = response.getElementsByTagName("err");
			// Get Flickr error code and msg
//			String code = error.item(0).getAttributes().item(0).getTextContent();
//			String msg = error.item(0).getAttributes().item(1).getTextContent();
//			System.out.println("Flickr request failed with error code " + code + ", " + msg);
//			System.out.println("Auth token not received. Fix this before moving on.");
			return null;
		}
		
	}
/*	public void fetchPhotoExample() throws XPathExpressionException,
	IOException, XMLStreamException, ParserConfigurationException,
	SAXException {
		
                String group_id="2825475%40N22";
                String topic_id="72157650826742592";
                System.out.println(getListOfDiscussionsForGroup(group_id));
                System.out.println(getListOfRepliesForTopics(group_id, topic_id));
          fetchPhotos("Kobe Bryant", 3);
}*/
	
	 


}
