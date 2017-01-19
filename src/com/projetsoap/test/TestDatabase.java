package com.projetsoap.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.projetsoap.srcfiles.Message;
import com.projetsoap.utils.DatabaseOperations;

public class TestDatabase {

	public void UploadMessage(Message message){
		//Creation du message
		Session session =  DatabaseOperations.Connection(Message.class).getSession();
		
		DatabaseOperations.SaveDatas(session, message);
		DatabaseOperations.CloseConnection(session);
	}
	
	public static void main(String[] args) {
		TestDatabase test = new TestDatabase();
		//test.createAndUploadMessage();
		//test.getMessageFromBDD();
		
	}

}
