package edu.cmu.cs.ebiz.teamHEX.task8.model;

import java.awt.Desktop;
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
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Flickr extends WebAccessor {
	private final String flickrKey;
	private final String flickrSecret;
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
		
		URL url = new URL("https://api.flickr.com/services/rest/?method=" + SEARCH
				+ "&api_key=" + flickrKey + "&per_page=" + count
				+ "&text=" + URLEncoder.encode(keyword, "UTF-8")
				+ "&tag_mode=all&content_type=1&sort=relevance");
		
		connection = (HttpsURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);

		writeRequest(connection, "");
		
		String filename = "test.xml";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
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
		
        NodeList nodeList = (NodeList) xpath.evaluate("//photos/photo",  doc, XPathConstants.NODESET);
        
        for (int i = 0; i < nodeList.getLength(); i++) {
        	Node node = nodeList.item(i);
        	
        	String id = (String) xpath.evaluate("@id", node, XPathConstants.STRING);
        	String server = (String) xpath.evaluate("@server", node, XPathConstants.STRING);
        	String secret = (String) xpath.evaluate("@secret", node, XPathConstants.STRING);
        	
			String flickrurl = "http://static.flickr.com/" + server + "/" + id + "_"	+ secret + ".jpg";
        	System.out.println(flickrurl);
        	
        	res.add(flickrurl);
        }
        
		return res;
	}
	
	public void fetchPhotoExample () throws XPathExpressionException, IOException, XMLStreamException, ParserConfigurationException, SAXException {
		//fetchPhotos("Kobe Bryant", 3);
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
		
		
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(request));
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("xdg-open " + request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
		System.out.println("Token request: " + request);
		
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
			NodeList error = response.getElementsByTagName("err");
			// Get Flickr error code and msg
			String code = error.item(0).getAttributes().item(0).getTextContent();
			String msg = error.item(0).getAttributes().item(1).getTextContent();
			System.out.println("Flickr request failed with error code " + code + ", " + msg);
//			System.out.println("Auth token not received. Fix this before moving on.");
			return null;
		}
	}
	

}
