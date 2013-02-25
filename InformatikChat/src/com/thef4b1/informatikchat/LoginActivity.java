package com.thef4b1.informatikchat;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

@SuppressLint("NewApi")
public class LoginActivity extends Activity {
	String ip;
	int port;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		ip = intent.getStringExtra("IP");
		port = intent.getIntExtra("Port", 9999);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void login(View view){
		EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
		EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		
		if(editTextUsername.length()==0 && editTextPassword.length()==0){
		    int duration = Toast.LENGTH_LONG;
		    Toast toast = Toast.makeText(getBaseContext(), R.string.toast_noip, duration);
	        toast.show();
		} else {
			String username = editTextUsername.getText().toString();
			String password = editTextPassword.getText().toString();
			
			Intent intent = new Intent(this, ChatClientMessageActivity.class);
			intent.putExtra("IP", ip);
			intent.putExtra("Port", port);
			intent.putExtra("Username", username);
			intent.putExtra("Password", password);
			startActivity(intent);
		}
		
	}

}
