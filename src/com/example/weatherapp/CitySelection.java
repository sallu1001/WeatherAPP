package com.example.weatherapp;

import com.example.database.DatabaseHandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CitySelection extends Activity {
	
	Context mContext;
	EditText cityName;
	Button btnOk;
	DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_selection);
		
		mContext = this;
		db = new DatabaseHandler(mContext);
		cityName = (EditText)findViewById(R.id.cityName);
		btnOk = (Button)findViewById(R.id.go);
		
		if(cityName.getText().toString().length()>1){
			showToast("Please enter city name");
		}
		
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.delete();
				Intent intent = new Intent(mContext,WeatherDetails.class);
				intent.putExtra("CITY_SELECTED", cityName.getText().toString());
				startActivity(intent);
			}
		});
	}

	protected void showToast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, msg, 0).show();
	}

}
