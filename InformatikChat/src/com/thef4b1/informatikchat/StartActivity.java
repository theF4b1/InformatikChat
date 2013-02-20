package com.thef4b1.informatikchat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends Activity {
	private ChatClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	}
	
	public void connectToServer(View view){
		Intent intent = new Intent(this, LoginActivity.class);
		EditText editTextIp = (EditText) findViewById(R.id.editTextIp);
		EditText editTextPort = (EditText) findViewById(R.id.editTextPort);
		String ip = editTextIp.getText().toString();
		int port = Integer.parseInt(editTextPort.getText().toString());
		intent.putExtra("IP", ip);
		intent.putExtra("port", port);
		
		Object chatClient = client;
		startActivity(intent);
	}
	
}
