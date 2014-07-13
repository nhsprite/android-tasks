package com.example.mynote;


import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.ActivityManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.example.mynote.message";
	ListView listview;
	noteservice note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        listview = (ListView) this.findViewById(R.id.listview);
        note = new noteservice(this);
        show();
        listview.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                "Äúµã»÷µÄÊÇ£º"+id, Toast.LENGTH_SHORT).show();
            	Intent intent = new Intent(MainActivity.this,NoteEditActivity.class);
            	intent.putExtra(EXTRA_MESSAGE, Long.toString(id));
            	startActivity(intent);
            }
        });
        
        listview.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo info) {
			    MenuInflater inflater = getMenuInflater();  
			    inflater.inflate(R.menu.delete_menu, menu); 
			}
        });
    }
    
    public boolean onContextItemSelected(MenuItem item){
    	AdapterView.AdapterContextMenuInfo info;
    	info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); 
    	switch (item.getItemId()) {  
        case R.id.context_delete:  
        	note.deleteNote(Long.toString(info.id));
            show();
            return true;
        default:  
            return super.onContextItemSelected(item);  
        }  
    }


    private void show() {
		// TODO Auto-generated method stub
		Cursor cursor = note.getnote();
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.note,cursor,new String[]{"title","time","content"},new int[]{R.id.showtitle,R.id.showtime,R.id.shownote},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listview.setAdapter(adapter);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_add) {
        	newNote();
            return true;
        }
        else if(id == R.id.exit) {
        	ExitApplication.getInstance().exit(this);
        	return true;
        }

        return super.onOptionsItemSelected(item);
    }


	private void newNote() {
		// TODO Auto-generated method stub
    	Intent intent = new Intent(this, NewNote.class);
    	startActivity(intent);
	}
	
}
