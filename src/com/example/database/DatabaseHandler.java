package com.example.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.weatherapp.WeatherModel;

public class DatabaseHandler extends SQLiteOpenHelper{

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "weather_app";

	//Table Name
	private static final String TABLE_WEATHER_DETAIL = "weather_detail";

	// Table Columns names
	private static final String KEY_DATE = "date";
	private static final String KEY_TEMP_MAXC = "tempMaxc";
	private static final String KEY_TEMP_MAXF = "tempMaxF";
	private static final String KEY_TEMP_MINC = "tempMinC";
	private static final String KEY_TEMP_MINF = "tempMinF";
	private static final String KEY_WEATHER_TYPE = "weatherType";
	private static final String KEY_IMG = "weatherTypeImage";

	SQLiteDatabase db ;

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WEATHER_DETAIL + "("
				+ KEY_DATE + " TEXT," + KEY_TEMP_MAXC + " TEXT,"
				+ KEY_TEMP_MAXF + " TEXT,"
				+ KEY_TEMP_MINC + " TEXT," + KEY_TEMP_MINF + " TEXT,"
				+ KEY_WEATHER_TYPE + " TEXT,"
				+ KEY_IMG + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHER_DETAIL);
		// Create tables again
		onCreate(db);
	}

	// insert records
	public void addData(WeatherModel model) {

		try {

			open();

			ContentValues values = new ContentValues();
			values.put(KEY_DATE, model.getDate()); 
			values.put(KEY_TEMP_MAXC, model.getTempMaxc()); 
			values.put(KEY_TEMP_MAXF, model.getTempMaxF()); 
			values.put(KEY_TEMP_MINC, model.getTempMinC()); 
			values.put(KEY_TEMP_MINF, model.getTempMinF()); 
			values.put(KEY_WEATHER_TYPE, model.getWeatherType());
			values.put(KEY_IMG, model.getWeatherTypeImage());

			// Inserting Row
			db.insert(TABLE_WEATHER_DETAIL, null, values);
			close(); // Closing database connection
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<WeatherModel> getAllData(){

		ArrayList<WeatherModel> mModel = new ArrayList<WeatherModel>();
		try {
			open();
			String query = "SELECT * FROM " + TABLE_WEATHER_DETAIL;
			Cursor  cursor = db.rawQuery(query,null);
			for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){

				WeatherModel model = new WeatherModel();
				model.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
				model.setTempMaxc(cursor.getString(cursor.getColumnIndex(KEY_TEMP_MAXC)));
				model.setTempMaxF(cursor.getString(cursor.getColumnIndex(KEY_TEMP_MAXF)));
				model.setTempMinC(cursor.getString(cursor.getColumnIndex(KEY_TEMP_MINC)));
				model.setTempMinF(cursor.getString(cursor.getColumnIndex(KEY_TEMP_MINF)));
				model.setWeatherType(cursor.getString(cursor.getColumnIndex(KEY_WEATHER_TYPE)));
				model.setWeatherTypeImage(cursor.getString(cursor.getColumnIndex(KEY_IMG)));
				
				mModel.add(model);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return mModel;
	}

	// Getting contacts Count
	public int getContactsCount() {

		String countQuery = "SELECT  * FROM " + TABLE_WEATHER_DETAIL;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		//cursor.close();

		// return count
		return cursor.getCount();
	}

	// Delete
	public void delete() {
		open();
		db.execSQL("delete from "+ TABLE_WEATHER_DETAIL);
		db.close();
	}

	public void open(){
		db = getWritableDatabase();
	}

	public void close(){
		db.close();
	}
}
