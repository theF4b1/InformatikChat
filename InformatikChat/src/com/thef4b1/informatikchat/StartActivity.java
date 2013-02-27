package com.thef4b1.informatikchat;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class StartActivity extends Activity {
	private static Context context;
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences loginInfos;
	EditText editTextIp;
	EditText editTextPort;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartActivity.context = getApplicationContext();
		setContentView(R.layout.activity_start);
		
		editTextIp = (EditText) findViewById(R.id.editTextIp);
		editTextPort = (EditText) findViewById(R.id.editTextPort);
		
		loginInfos = getSharedPreferences(PREFS_NAME, 0);
		editTextIp.setText(loginInfos.getString("ip", ""));
	}
	
	public void connectToServer(View view){
		if(editTextIp.length()==0 || editTextPort.length()==0){
		    int duration = Toast.LENGTH_LONG;
		    Toast toast = Toast.makeText(context, R.string.toast_noip, duration);
	            toast.show();
		} else {
			Intent intent = new Intent(this, LoginActivity.class);
			
			String ip = editTextIp.getText().toString();
			int port = Integer.parseInt(editTextPort.getText().toString());
			
			SharedPreferences.Editor editor = loginInfos.edit();
			editor.putString("ip", ip);
			editor.putInt("port", port);
	                editor.commit();
	        
			startActivity(intent);
		}
	}
	
	public static Context getAppContext() {
        	return StartActivity.context;
    	}
	
}
