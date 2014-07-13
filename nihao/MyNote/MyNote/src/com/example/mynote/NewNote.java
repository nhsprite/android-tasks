package com.example.mynote;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends ActionBarActivity {
	noteservice noteserve;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		setContentView(R.layout.activity_new_note);
		noteserve = new noteservice(this);		
	}
	
	public void savenote(){
		EditText editText = (EditText) findViewById(R.id.addnote);
		String note = editText.getText().toString();
		EditText editText1 = (EditText) findViewById(R.id.addtitle);
		String title = editText1.getText().toString();
		if(note.length()!=0 && title.length()!=0){
			noteserve.addNote(note,title);
	    	Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
	    	Toast.makeText(getApplicationContext(),
	    			"笔记保存成功", Toast.LENGTH_SHORT).show();
		} 
		else{
			Toast.makeText(getApplicationContext(),
			"标题和内容均不能为空", Toast.LENGTH_SHORT).show();
			editText.setText(note);
			editText1.setText(title);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_save) {
			savenote();
			return true;
		}
		else if(id == R.id.exit) {
        	ExitApplication.getInstance().exit(this);
        	return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
