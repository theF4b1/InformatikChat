package com.thef4b1.informatikchat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatClientMessageActivity extends Activity{
	ArrayAdapter<String> adapter;
	ArrayList<String> listItems = new ArrayList<String>();
	
	EditText editTextMessage;
	
	ListView listView;
	private ChatClient client;
	
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences loginInfos;
	
	//-------------------------------------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_client_message);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		
		editTextMessage = (EditText) findViewById(R.id.editTextMessage);
		
		loginInfos = getSharedPreferences(PREFS_NAME, 0);
		String ip = loginInfos.getString("ip", "");
		int port = loginInfos.getInt("port", 9999);
		String username = loginInfos.getString("username", "");
		String password = loginInfos.getString("password", "");
		
		try {
			client = new ChatClient(ip, port, listView, adapter, listItems);
			if(loginInfos.getBoolean("register?", false)){
				client.send("LOGIN " + username + " " + password);
			} else {
				client.send("REGISTER " + username + " " + password);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Context context = StartActivity.getAppContext();
		    int duration = Toast.LENGTH_LONG;
		    Toast toast = Toast.makeText(context, e.toString(), duration);
		    toast.show();
		}
	}
	//-------------------------------------------------------------------------------------------
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_chat_client_login, menu);
		menu.add("Hello");
		return true;
	}
	
	public void sendMessage(View view){
		String pMessage = editTextMessage.getText().toString();
		editTextMessage.setText("");
		client.send("MSG " + pMessage);
	}

}
