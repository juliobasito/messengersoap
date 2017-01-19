/**
 * Classe contenant des fonctions permettant d'intéragir avec la bdd
 */
package com.projetsoap.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.projetsoap.srcfiles.Message;

public class DatabaseOperations {

	public static Session Connection(Class<?> c){
		
		//Crée un objet SessionFactory à partir des données du fichier de config hibernate
		SessionFactory factory = new Configuration().configure(HibernateInfos.getConfigfile())
				.addAnnotatedClass(c) //Prend en paramètre la classe mappée à la bdd
				.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}
	
	public static void SaveDatas(Session session, Object o){
		//Sauvegarde et commit un insert dans la bdd
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void CloseConnection(Session session){
		SessionFactory sf = session.getSessionFactory();
		//Ferme la connectionList
		session.close();
		sf.close();
	}	
	
	public static ArrayList<Message> getAllMessages(){
		Session session =  DatabaseOperations.Connection(Message.class).getSession();
		Query q = session.createQuery("from Message");
		List l = q.getResultList();
		ArrayList<Message> truckMessages = new ArrayList<Message>();
		for(Object o:l){
			Message m = new Message();
			m = (Message) o;
			truckMessages.add(m);
		}
		return truckMessages;
	}
}
