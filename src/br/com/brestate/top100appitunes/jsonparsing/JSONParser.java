package br.com.brestate.top100appitunes.jsonparsing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.brestate.top100appitunes.modelo.Artist;
import br.com.brestate.top100appitunes.modelo.Author;
import br.com.brestate.top100appitunes.modelo.Category;
import br.com.brestate.top100appitunes.modelo.Id;
import br.com.brestate.top100appitunes.modelo.TopApp;
import br.com.brestate.top100appitunes.modelo.TopAppList;

public class JSONParser {

	private String url = "http://itunes.apple.com/br/rss/topfreeapplications/limit=";
	private String format = "json";
	private static int CONNECTION_TIMEOUT = 180000;
	private static int DATARETRIEVAL_TIMEOUT = 180000;


	public TopAppList getTopApps(int count) throws MalformedURLException, SocketTimeoutException, IOException,
	JSONException, Exception
	{
		TopAppList topApps = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.US);

		String finalURL = url + count + "/" + format;

		//Tentando parsear um Json de Multiplos Arrays
		try {
			JSONObject json = downloadJSON(finalURL).getJSONObject("feed");

			JSONObject authorJson = json.getJSONObject("author");
			Author author = new Author(authorJson.getJSONObject("name").getString("label"), 
					authorJson.getJSONObject("uri").getString("label"));
			String rights = json.getJSONObject("rights").getString("label");
			Date updated = sdf.parse(json.getJSONObject("updated").getString("label"));

			JSONArray array = json.getJSONArray("entry");

			List<TopApp> apps = new ArrayList<TopApp>();

			for(int i = 0; i < array.length(); i++)
			{
				JSONObject appJson = array.getJSONObject(i);

				JSONObject artJson = appJson.getJSONObject("im:artist");
				Artist artist = new Artist(artJson.getString("label"), 
						artJson.getJSONObject("attributes").getString("href"));
				JSONObject artCat = appJson.getJSONObject("category").getJSONObject("attributes");
				Category category = new Category(artCat.getInt("im:id"), artCat.getString("term"), 
						artCat.getString("scheme"), artCat.getString("label"));

				JSONArray urlJson = appJson.getJSONArray("im:image");
				String[] imgUrls = new String[urlJson.length()];

				for(int j = 0; j < urlJson.length(); j++)
				{
					imgUrls[j] = urlJson.getJSONObject(j).getString("label");
				}

				JSONObject idJson = appJson.getJSONObject("id");
				Id id = new Id(idJson.getString("label"), 
						idJson.getJSONObject("attributes").getInt("im:id"), 
						idJson.getJSONObject("attributes").getString("im:bundleId"));

				TopApp app = new TopApp(appJson.getJSONObject("im:name").getString("label"), 
						imgUrls, appJson.getJSONObject("summary").getString("label"), 
						appJson.getJSONObject("im:price").getJSONObject("attributes").getDouble("amount"), 
						appJson.getJSONObject("im:contentType").getJSONObject("attributes").getString("label"), 
						appJson.getJSONObject("rights").getString("label"), 
						appJson.getJSONObject("title").getString("label"), 
						appJson.getJSONObject("link").getJSONObject("attributes").getString("href"), 
						id,	artist, category, 
						sdf.parse(appJson.getJSONObject("im:releaseDate").getString("label")));

				apps.add(app);
			}

			topApps = new TopAppList(apps, author, updated, rights);

		} catch (JSONException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			throw e;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return topApps;
	}


	public JSONObject downloadJSON(String url) throws MalformedURLException, SocketTimeoutException, IOException,
	JSONException, Exception
	{
		HttpURLConnection urlConnection = null;
		try {
			// create connection
			URL urlToRequest = new URL(url);
			urlConnection = (HttpURLConnection) 
					urlToRequest.openConnection();
			urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
			urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

			// handle issues
			int statusCode = urlConnection.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
				// handle unauthorized (if service requires user login)
			} else if (statusCode != HttpURLConnection.HTTP_OK) {
				// handle any other errors, like 404, 500,..
			}

			// create JSON object from content
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			return new JSONObject(getResponseText(in));

		} catch (MalformedURLException e) {
			throw e;
		} catch (SocketTimeoutException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (JSONException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}       

	}

	private String getResponseText(InputStream inStream)  {
		String text = "";


		Scanner s = new Scanner(inStream);
		s.useDelimiter("\\A");
		text = s.next();

		s.close();


		return text;
	}

	//	private JSONObject getJSONFromUrl(String url) {
	//
	//		// Obtendo o HTTP 
	//		try {
	//			// defaultHttpClient
	//			DefaultHttpClient httpClient = new DefaultHttpClient();
	//			HttpPost httpPost = new HttpPost(url);
	//
	//			HttpResponse httpResponse = httpClient.execute(httpPost);
	//			HttpEntity httpEntity = httpResponse.getEntity();
	//			is = httpEntity.getContent();			
	//
	//		} catch (UnsupportedEncodingException e) {
	//			e.printStackTrace();
	//		} catch (ClientProtocolException e) {
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//
	//		try {
	//			BufferedReader reader = new BufferedReader(new InputStreamReader(
	//					is, "iso-8859-1"), 8);
	//			StringBuilder sb = new StringBuilder();
	//			String line = null;
	//			while ((line = reader.readLine()) != null) {
	//				sb.append(line + "\n");
	//			}
	//			is.close();
	//			json = sb.toString();
	//		} catch (Exception e) {
	//			Log.e("Buffer Error", "Error converting result " + e.toString());
	//		}
	//
	//		JSONObject jObj  =null;
	//
	//		// Parseando a string para um JSON object
	//		try {
	//			jObj = new JSONObject(json);
	//		} catch (JSONException e) {
	//			Log.e("JSON Parser", "Error parsing data " + e.toString());
	//		}
	//
	//		// retornando a JSON String
	//		return jObj;
	//
	//	}
}
