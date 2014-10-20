package com.example.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherModel;
import com.squareup.picasso.Picasso;

public class WeatherListAdapter extends ArrayAdapter<WeatherModel>{

	Activity mContext;
	ArrayList<WeatherModel> mArrModel;
	LayoutInflater mLayoutInflater;

	public WeatherListAdapter(Activity mContext, int resource, ArrayList<WeatherModel> mArrModel) {
		super(mContext, resource, mArrModel);
		// TODO Auto-generated constructor stub

		this.mContext = mContext;
		this.mArrModel  = mArrModel;
		mLayoutInflater = mContext.getLayoutInflater();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArrModel.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub


		if(convertView == null){

			ViewHolder viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.custom_layout, parent, false);

			viewHolder.tmpImg = (ImageView) convertView.findViewById(R.id.img);
			viewHolder.tmpMaxC = (TextView) convertView.findViewById(R.id.maxC);
			viewHolder.tmpMinC = (TextView) convertView.findViewById(R.id.minC);
			viewHolder.WeatherType = (TextView) convertView.findViewById(R.id.mType);
			viewHolder.mDate = (TextView) convertView.findViewById(R.id.mDate);

			convertView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.mDate.setText("Date " + mArrModel.get(position).getDate());
		holder.tmpMaxC.setText("Max " + mArrModel.get(position).getTempMaxc());
		holder.tmpMinC.setText("Min " + mArrModel.get(position).getTempMinC());
		holder.WeatherType.setText(mArrModel.get(position).getWeatherType());

		Picasso.with(mContext).load(mArrModel.get(position).getWeatherTypeImage()).into(holder.tmpImg);

		return convertView;
	}

	public static class ViewHolder{

		public TextView WeatherType;
		public TextView tmpMaxC;
		public TextView tmpMinC;
		public ImageView tmpImg;
		public TextView mDate;

	}
}
