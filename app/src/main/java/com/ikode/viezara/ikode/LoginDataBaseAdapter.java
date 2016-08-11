package com.ikode.viezara.ikode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter 
{

		static final String DATABASE_NAME = "ikode.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"app_user"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";

		//Product Create
		static final String DATABASE_SCAN_CREATE = "create table "+"scan_docs"+
				"( " +"ID"+" integer primary key autoincrement,"+ "type  text,desc text, scan_at DATETIME DEFAULT CURRENT_TIMESTAMP); ";


		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String userName,String password)
		{
	       ContentValues newValues = new ContentValues();
			RequestData.storedEmail = userName;
			// Assign values for each row.
			newValues.put("USERNAME", userName);
			newValues.put("PASSWORD",password);
			
			// Insert the row into your table
			db.insert("app_user", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		//Scan Docs Insert
		public String insertScan(String id, String type, String desc)
		{
			ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("ID", id);
			newValues.put("type", type);
			newValues.put("desc", desc);

			// Insert the row into your table
			db.insert("scan_docs", null, newValues);

			String selectQuery = "SELECT  * FROM scan_docs where ID='" + id + "'";
			Cursor cursor=db.rawQuery(selectQuery, null);
			if(cursor.getCount()>0) // UserName Not Exist
			{
				cursor.moveToFirst();
				String return_desc= cursor.getString(cursor.getColumnIndex("desc"));

				cursor.close();
				return "Data Inserted " + return_desc;
			}
			else{
				return "Data is invalid, something is wrong!";
			}

		}
		//List View of scan docs
		public Cursor fetchAllDocs() {

			Cursor mCursor = db.rawQuery("SELECT "
					+ "ID" + " AS _id" + ", type " + ", desc, scan_at from scan_docs", null);
			if (mCursor != null) {
				mCursor.moveToFirst();

			}
			return mCursor;
		}


		public int deleteEntry(String UserName)
		{
			//String id=String.valueOf(ID);
		    String where="USERNAME=?";
		    int numberOFEntriesDeleted= db.delete("app_user", where, new String[]{UserName}) ;
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		public String getSingleEntry(String userName)
		{
			Cursor cursor=db.query("app_user", null, " USERNAME=?", new String[]{userName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
			cursor.close();
			return password;
		}
		public String getUserEmail()
		{
			//Cursor cursor=db.query("app_user", null, null, null, null, null, null);
			String selectQuery = "SELECT  * FROM app_user";
			Cursor cursor=db.rawQuery(selectQuery, null);
			if(cursor.getCount()<1) // UserName Not Exist
			{
				cursor.close();
				return "Sqlite Database is empty!";
			}
			cursor.moveToFirst();
			String password= cursor.getString(cursor.getColumnIndex("USERNAME"));
			cursor.close();
			return password;
		}
		public void  updateEntry(String userName,String password)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("USERNAME", userName);
			updatedValues.put("PASSWORD",password);
			
	        String where="USERNAME = ?";
		    db.update("app_user",updatedValues, where, new String[]{userName});
		}		
}

