package com.thef4b1.informatikchat;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import classes.netzklassen.Client;

public class ChatClient extends Client {
	//Adapter und ArrayList für die ListView
	ArrayAdapter<String> adapter;
	ArrayList<String> listItems;
	
	ListView listView;
	String ip;
	int port;
	
	public ChatClient(String pIPAdresse, int pPortNr, ListView aListView, ArrayAdapter<String> aAdapter, ArrayList<String> aListItems) {
		super(pIPAdresse, pPortNr);
		
		adapter = aAdapter;
		listItems = aListItems;
		ListView listView = aListView;
		
		listView.setAdapter(adapter);
		
		ip = pIPAdresse;
		port = pPortNr;
	}

	@Override
	public void processMessage(String pMessage) {
		String message = pMessage;int index = message.indexOf(" ");
	        if (index > -1) {
	            String cmd = pMessage.substring(0, index);
	            String msgBody = message.substring(index + 1);
	
	            if (cmd.equals("MSG")) {
	                processUserMessage(msgBody);
	            }
	            if (cmd.equals("SERVER")) {
	                processServerMessage(msgBody);
	            }
	            if (cmd.equals("USERLIST")) {
	                processUserlist(msgBody);
	            }
	            if (cmd.equals("COMMAND")) {
	                processCommand(msgBody);
	            }
		}
	}
	
	public void processUserMessage(String msgBody){
		listItems.add(msgBody);
		adapter.notifyDataSetChanged();
		//Liste zum ende scrollen:
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
	}
	
	public void processServerMessage(String msgBody){
		listItems.add("--------\n" + msgBody + "\n--------");
		adapter.notifyDataSetChanged();
		//Liste zum ende scrollen:
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
	}
	
	public void processUserlist(String msgBody){
		String[] userArray = msgBody.split("#");
		
		
		Context context = StartActivity.getAppContext();
		int duration = Toast.LENGTH_SHORT;
		
		for (int i = 0; i < userArray.length; i++) {
	            Toast toast = Toast.makeText(context, userArray[i], duration);
	            toast.show();
	        }
	}
	
	public void processCommand(String msgBody){
	        if (msgBody.equals("LOGINFALSE")) {
		        Context context = StartActivity.getAppContext();
			    int duration = Toast.LENGTH_LONG;
			    
			    Toast toast = Toast.makeText(context, "No registration or wrong password! Please try again.", duration);
		            toast.show();
	        }
	}
}
