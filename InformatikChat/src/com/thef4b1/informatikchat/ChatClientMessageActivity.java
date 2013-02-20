package com.thef4b1.informatikchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import classes.netzklassen.Client;

public class ChatClientMessageActivity extends Activity{
	private ChatClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_client_message);
		
		Intent intent = getIntent();
		String ip = intent.getStringExtra("IP");
		int port = intent.getIntExtra("Port", 9999);
		
		client = new ChatClient(ip, port);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_chat_client_login, menu);
		return true;
	}
	
	public void sendMessage(View view){
		
	}

}
