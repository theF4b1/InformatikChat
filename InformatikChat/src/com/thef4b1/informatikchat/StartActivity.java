package com.thef4b1.informatikchat;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class StartActivity extends Activity {
	private static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartActivity.context = getApplicationContext();
		setContentView(R.layout.activity_start);
	}
	
	public void connectToServer(View view){
		EditText editTextIp = (EditText) findViewById(R.id.editTextIp);
		EditText editTextPort = (EditText) findViewById(R.id.editTextPort);
		
		if(editTextIp.length()==0 && editTextPort.length()==0){
		    int duration = Toast.LENGTH_LONG;
		    Toast toast = Toast.makeText(context, R.string.toast_noip, duration);
	        toast.show();
		} else {
			Intent intent = new Intent(this, LoginActivity.class);
			
			String ip = editTextIp.getText().toString();
			int port = Integer.parseInt(editTextPort.getText().toString());
			intent.putExtra("IP", ip);
			intent.putExtra("port", port);
			
			startActivity(intent);
		}
	}
	
	public static Context getAppContext() {
        return StartActivity.context;
    }
	
}
