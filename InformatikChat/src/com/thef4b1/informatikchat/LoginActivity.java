package com.thef4b1.informatikchat;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
	
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences loginInfos;
	
	EditText editTextUsername;
	EditText editTextPassword;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		editTextUsername = (EditText) findViewById(R.id.editTextUsername);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		
		loginInfos = getSharedPreferences(PREFS_NAME, 0);
		editTextUsername.setText(loginInfos.getString("username", ""));
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
		
		
		if(editTextUsername.length()==0 || editTextPassword.length()==0){
		    int duration = Toast.LENGTH_LONG;
		    Toast toast = Toast.makeText(getBaseContext(), R.string.toast_nousername, duration);
	        toast.show();
		} else {
			String username = editTextUsername.getText().toString();
			String password = editTextPassword.getText().toString();
			
			SharedPreferences.Editor editor = loginInfos.edit();
			editor.putString("username", username);
			editor.putString("password", password);
			editor.putBoolean("register?", false);
			editor.commit();
			
			Intent intent = new Intent(this, ChatClientMessageActivity.class);
			startActivity(intent);
		}
		
	}
	
	public void register(View view){
		if(editTextUsername.length()==0 || editTextPassword.length()==0){
		    int duration = Toast.LENGTH_LONG;
		    Toast toast = Toast.makeText(getBaseContext(), R.string.toast_nousername, duration);
	        toast.show();
		} else {
			String username = editTextUsername.getText().toString();
			String password = editTextPassword.getText().toString();
			
			SharedPreferences.Editor editor = loginInfos.edit();
			editor.putString("username", username);
			editor.putString("password", password);
			editor.putBoolean("register?", true);
			editor.commit();
			
			Intent intent = new Intent(this, ChatClientMessageActivity.class);
			startActivity(intent);
		}
	}

}
