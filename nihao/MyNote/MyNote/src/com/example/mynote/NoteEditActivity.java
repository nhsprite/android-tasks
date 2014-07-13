package com.example.mynote;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteEditActivity extends ActionBarActivity {
	String noteId;
	noteservice noteserve;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		setContentView(R.layout.activity_note_edit);
		noteserve = new noteservice(this);
		Intent intent = getIntent();
		noteId = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//		Toast.makeText(getApplicationContext(),
//				"您点击的是："+noteId, Toast.LENGTH_SHORT).show();
		String currentnote = getCurrentNote(noteId);
		String currenttitle = getCurrentTitle(noteId);
//		Toast.makeText(getApplicationContext(),
//		"您点击的是："+currentnote, Toast.LENGTH_SHORT).show();
		EditText edittextnote = (EditText) findViewById(R.id.editnote);
		edittextnote.setText(currentnote);
		EditText edittexttitle = (EditText) findViewById(R.id.title);
		edittexttitle.setText(currenttitle);
	}
	
	private String getCurrentNote(String id) {
		Cursor cursor = noteserve.findnote(id);
		cursor.moveToFirst();
		String notenow = cursor.getString(cursor.getColumnIndex("content"));	
		return notenow;
	}
	private String getCurrentTitle(String id) {
		Cursor cursor = noteserve.findnote(id);
		cursor.moveToFirst();
		String titlenow = cursor.getString(cursor.getColumnIndex("title"));	
		return titlenow;
	}

	public void updatenote(){
		EditText editTextnote = (EditText) findViewById(R.id.editnote);
		String note = editTextnote.getText().toString();
		EditText editTexttitle = (EditText) findViewById(R.id.title);
		String title = editTexttitle.getText().toString();
		if(note.length()!=0 && title.length()!=0){
			noteserve.updateNote(note,noteId,title);
			Intent intent = new Intent(NoteEditActivity.this,MainActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(),
			"标题和内容均不能为空", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note_edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_edit) {
			updatenote();
			return true;
		}
		else if(id == R.id.exit) {
        	ExitApplication.getInstance().exit(this);
        	return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
