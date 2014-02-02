package br.com.brestate.top100appitunes.view.activity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import br.com.brestate.top100appitunes.R;
import br.com.brestate.top100appitunes.jsonparsing.JSONParser;
import br.com.brestate.top100appitunes.modelo.TopApp;
import br.com.brestate.top100appitunes.modelo.TopAppList;

public class MainActivity extends Activity {
	private ListView list;
	private String error = "";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		new JSONParse().execute();

	}
	// Utilizando AsyncTask para criar uma segunda Thread para acessar a Internet
	private class JSONParse extends AsyncTask<String, String, TopAppList> {
		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			list = (ListView) findViewById(R.id.list);
			
			// Barra de Progresso
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Buscando ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected TopAppList doInBackground(String... args) {
			TopAppList apps = null;
			
			error = "";
			
			JSONParser jParser = new JSONParser();
			
			try {
				apps =  jParser.getTopApps(100);
			} catch (SocketTimeoutException e) {
				error = getString(R.string.error_timeout);
			} catch (MalformedURLException e) {
				error = getString(R.string.error_url);
			} catch (IOException e) {
				error = getString(R.string.error_timeout);
			} catch (JSONException e) {
				error = getString(R.string.error_json);
			} catch (Exception e) {
				error = getString(R.string.error_general);
			}
			
			if(!error.equals(""))
			{
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
					}
				});
			}
			
			return apps;
		}

		@Override
		protected void onPostExecute(TopAppList appList) {
			pDialog.dismiss();
			try {
				AppArrayAdapter adapter = new AppArrayAdapter(getBaseContext(), R.id.name, 
						appList.getTopApps().toArray(new TopApp[]{}));
				list.setAdapter(adapter);

				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
