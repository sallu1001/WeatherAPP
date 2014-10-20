package com.example.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCheck {
	Context contex;
	public NetworkCheck(Context ctx)
	{
		this.contex=ctx;
	}
	public boolean isNetworkAvailable() {
		try
		{
			ConnectivityManager cm = (ConnectivityManager)contex.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			// if no network is available networkInfo will be null
			// otherwise check if we are connected
			if (networkInfo != null && networkInfo.isConnected()) {
				return true;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	} 
}
