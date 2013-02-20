package com.thef4b1.informatikchat;

import android.widget.EditText;
import classes.netzklassen.Client;

public class ChatClient extends Client {

	public ChatClient(String pIPAdresse, int pPortNr, EditText messages) {
		super(pIPAdresse, pPortNr);
		messages.setText("This works so well...");
	}

	@Override
	public void processMessage(String pMessage) {
		
	}
	
	

}
