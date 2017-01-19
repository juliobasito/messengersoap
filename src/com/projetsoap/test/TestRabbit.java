package com.projetsoap.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.projetsoap.messagerie.sender.Sender;
import com.projetsoap.messagerie.utils.RabbitUtils;
import com.projetsoap.srcfiles.Message;
import com.projetsoap.utils.DatabaseOperations;

import org.junit.Assert;

public class TestRabbit {
	
	@Test
	public void TestDatabaseInsert() {
		Message m = new Message(10,11,false,true, "not important",1237);
		Session session = DatabaseOperations.Connection(Message.class);
		DatabaseOperations.SaveDatas(session, m);
		DatabaseOperations.CloseConnection(session);
	}

	@Test
	public void TestDatabaseGetMessage(){
		Session session =  DatabaseOperations.Connection(Message.class).getSession();
		Query q = session.createQuery("from Message");
		List l = q.getResultList();
		List<Message> truckMessages = new ArrayList<Message>();
		for(Object o:l){
			Message m = new Message();
			m = (Message) o;
			System.out.println(m.getId() +" "+ m.getId_mission() +" "+ m.getIdReceiver() +" "+ m.getIdSender()+" "
								+m.getType());
			truckMessages.add(m);
		}
	}
	
//	@Test
//	public void createSenderAndGiveMessage(){
//		Sender sender = new Sender();
//		Message m = new Message(3216, 10, false, false, "important", 11111112);
//		sender.setSenderMessage(m);
//		assertNotNull(sender.getSenderMessage());
//		System.out.println(sender.getSenderMessage().getType().toString());
//	}
//	
//	@Test
//	public void getSenderMessageFromBDD(){
//		Sender sender = new Sender();
//		Message m = DatabaseOperations.getAllMessages().get(0);
//		sender.setSenderMessage(m);
//		assertEquals(sender.getSenderMessage().getId(), 1);
//		assertEquals(sender.getSenderMessage().getIdSender(), 0);
//		assertEquals(sender.getSenderMessage().getIdReceiver(), 1945);
//		assertEquals(sender.getSenderMessage().getType(), "mission");
//		assertEquals(sender.getSenderMessage().getId_mission(), 0);
//	}
}
