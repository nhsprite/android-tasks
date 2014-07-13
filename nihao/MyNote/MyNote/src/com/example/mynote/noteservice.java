package com.example.mynote;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class noteservice {
	private MyDBhelper mydbhelper;
	public noteservice(Context context) {
		this.mydbhelper =new MyDBhelper(context);
	}

	public void addNote(String content,String title){
		SQLiteDatabase db = mydbhelper.getWritableDatabase();
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());
		String time = formatter.format(curDate);
		time ="于" + time + "添加";
		db.execSQL("insert into notes(time, content,title) values(?, ?,?)",new Object[]{time, content,title});
		db.close();
	}
	
	public void deleteNote(String id){
		SQLiteDatabase db = mydbhelper.getWritableDatabase();
		db.execSQL("delete from notes where _id = ?",new Object[]{id});
		db.close();
	}
	
	public Cursor getnote(){
		SQLiteDatabase db = mydbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from notes", null);
		return cursor;
	}
	
	public Cursor findnote(String id){
		SQLiteDatabase db = mydbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from notes where _id = ?", new String[]{id});
		return cursor;
	}
	
	public void updateNote(String content,String id,String title){
		SQLiteDatabase db = mydbhelper.getWritableDatabase();
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());
		String time = formatter.format(curDate);
		time ="于" + time + "添加";
		db.execSQL("update notes set content = ? ,time = ?,title = ? where _id =?",new Object[]{content,time,title,id});
		db.close();
	}
}
