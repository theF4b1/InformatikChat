package com.thef4b1.informatikchat;

import android.widget.EditText;
import classes.netzklassen.Client;

public class ChatClient extends Client {
	//Adapter und ArrayList für die ListView
	ArrayAdapter<String> adapter;
	ArrayList<String> listItems=new ArrayList<String>();
	
	public ChatClient(String pIPAdresse, int pPortNr) {
		super(pIPAdresse, pPortNr);
		
		//Adapter Konstruktor für listview:
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}

	@Override
	public void processMessage(String pMessage) {
		//view ändern auf texteingabefeld:
		//EditText editText = (EditText) findViewById(***);
		ListView listView = (ListView) findViewById(R.id.listView1);
		String message = editText.getText().toString();
		
		
		
		int index = message.indexOf(" ");

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
		
		
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		
		for (int i = 0; i < userArray.length; i++) {
	            Toast toast = Toast.makeText(context, userArray[i], duration);
	            toast.show();
	        }
	}
	
	public void processCommand(String msgBody){
	        if (msgBody.equals("LOGINFALSE")) {
	            setContentView(R.layout.activity_login);
	            Context context = getApplicationContext();
		    int duration = Toast.LENGTH_LONG;
		    
		    Toast toast = Toast.makeText(context, "No registration or wrong password!", duration);
	            toast.show();
	        }
	}
