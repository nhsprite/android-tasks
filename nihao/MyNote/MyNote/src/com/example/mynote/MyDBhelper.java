package com.example.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBhelper extends SQLiteOpenHelper {

	public MyDBhelper(Context context) {
		super(context, "mynotes.db", null, 1);
	}

	@Override
	//�������ݿ�;
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table notes(_id integer primary key autoincrement,time,content,title)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
