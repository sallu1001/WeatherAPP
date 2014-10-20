package com.example.weatherapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapters.WeatherListAdapter;
import com.example.database.DatabaseHandler;

public class WeatherDetails extends Activity {

	Activity mActivity;
	Context context;
	ListView mListWeather;
	String selectedCity = "";
	ProgressBar mProg;
	NetworkCheck netChk;
	ArrayList<WeatherModel> mModel = new ArrayList<WeatherModel>();
	DatabaseHandler db;
	ArrayList<WeatherModel> getModel = new ArrayList<WeatherModel>();
	WeatherListAdapter mAdapter;
	TextView mCitySelected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather_details);

		context = this;
		mActivity = this;
		netChk = new NetworkCheck(context);
		db = new DatabaseHandler(context);
		mCitySelected = (TextView)findViewById(R.id.mCitySelected);
		mListWeather = (ListView)findViewById(R.id.mListWeather);
		mProg = (ProgressBar)findViewById(R.id.mProg);
		Bundle b = getIntent().getExtras();

		if(b!=null){
			selectedCity = b.getString("CITY_SELECTED");
			mCitySelected.setText(selectedCity);
		}

		if(!netChk.isNetworkAvailable()){
			calllWeatherApi();	
		}
		else{
			mProg.setVisibility(View.GONE);
			showToast("No network available");
		}
		
	}


	private void calllWeatherApi() {
		// TODO Auto-generated method stub


		RequestQueue queue = Volley.newRequestQueue(this);
		String url =Config.URL + "&" + Config.KEY; 


		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.d("Response ","RESPONSE " + response.toString());
				mProg.setVisibility(View.GONE);
				
				try {
					
					JSONObject jsonObj = new JSONObject(response.toString());
					JSONObject data = jsonObj.getJSONObject("data");
					JSONArray weather = data.getJSONArray("weather");
					for(int i=0;i<weather.length();i++){
						
						WeatherModel model = new WeatherModel();
						JSONObject values = weather.getJSONObject(i);
						model.setDate(values.getString("date"));
						model.setTempMaxc(values.getString("tempMaxC"));
						model.setTempMaxF(values.getString("tempMaxF"));
						model.setTempMinC(values.getString("tempMinC"));
						model.setTempMinF(values.getString("tempMinF"));
						
						JSONArray icon = values.getJSONArray("weatherIconUrl");
						for(int j=0;j<icon.length();j++){
							JSONObject iconValue = icon.getJSONObject(j);
							model.setWeatherTypeImage(iconValue.getString("value")); // for weather icon
						}
							
						
						JSONArray type = values.getJSONArray("weatherDesc");
						for(int k=0;k<icon.length();k++){
							JSONObject typeValue = type.getJSONObject(k);
							model.setWeatherType(typeValue.getString("value")); // for weather type
						}
						
						
						mModel.add(model);
						
						//adding to db
						db.addData(model);
						
						//getting data
						try {
							getModel = db.getAllData();
							mAdapter = new WeatherListAdapter(mActivity, R.drawable.ic_launcher, getModel);
							mListWeather.setAdapter(mAdapter);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}
					
					///getting count 
					showToast(""+db.getContactsCount());
							
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				mProg.setVisibility(View.GONE);
				showToast(error.getMessage());
			}
		});

		queue.add(jsObjRequest);	}


	protected void showToast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(context, msg, 0).show();
	}
}
