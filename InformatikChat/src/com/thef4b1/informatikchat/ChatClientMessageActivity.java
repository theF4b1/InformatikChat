package com.thef4b1.informatikchat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import classes.netzklassen.Client;

public class ChatClientMessageActivity extends Activity{
	ArrayAdapter<String> adapter;
	ArrayList<String> listItems = new ArrayList<String>();
	
	ListView listView;
	private ChatClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_client_message);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		
		
		Intent intent = getIntent();
		String ip = intent.getStringExtra("IP");
		int port = intent.getIntExtra("Port", 9999);
		String username = intent.getStringExtra("Username");
		String password = intent.getStringExtra("Password");
		
		
		try {
			client = new ChatClient(ip, port, listView, adapter, listItems);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Context context = StartActivity.getAppContext();
		    int duration = Toast.LENGTH_LONG;
		    Toast toast = Toast.makeText(context, e.toString(), duration);
	        toast.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_chat_client_login, menu);
		menu.add("Hello");
		menu.add("This");
		menu.add("is");
		menu.add("a");
		menu.add("Test");
		return true;
	}
	
	public void sendMessage(View view){
		Context context = StartActivity.getAppContext();
	    int duration = Toast.LENGTH_LONG;
	    Toast toast = Toast.makeText(context, "This is not yet implemented...", duration);
        toast.show();
	}

}
