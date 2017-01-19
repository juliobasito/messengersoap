package com.projetsoap.messagerie.servlet;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.projetsoap.messagerie.sender.ConsumerMessage;
import com.projetsoap.messagerie.sender.Sender;
import com.projetsoap.srcfiles.Message;
import com.projetsoap.srcfiles.Mission;
import com.projetsoap.srcfiles.User;
import com.projetsoap.utils.DatabaseOperations;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class Messenger extends HttpServlet{

	private String urlTruckMissions, urlCreationMissions,urlLOL, urlCreationMessage, action;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		//Récupération des cookies utilisateur !
		//les lignes suivantes sont à changer
//		Cookie[] c = req.getCookies();
//		Cookie cookie = null;
//		for(Cookie cc:c){
//			if(cc.getName().equals("JSESSIONID")){
//				cookie =cc;
//			}
//		}
//		System.out.println(cookie.getValue());
		
		Session session = DatabaseOperations.Connection(User.class);
		Query getUser = session.createQuery("from User where Id_Role=1");
		User u = (User) getUser.list().get(0);
		ConsumerMessage cm = new ConsumerMessage();
		cm.start();
		
		action = req.getParameter("action");
		if(action==null){
			if(u.getIdRole()==0){
				System.out.println("connecté en tant qu'utilisateur + écoute des messages");
				action="toTruckMissions";	
			}
			if(u.getIdRole()==1){
				action="toMissionsCreation";
				System.out.println("connecté en tant qu'admin");
			}
		}
		if(action.equals("toTruckMissions")){
			req.setAttribute("user", u);
			getServletContext().getRequestDispatcher(urlTruckMissions).forward(req, rep);
		}
		if(action.equals("toMissionsCreation")){
			req.setAttribute("user", u);
			getServletContext().getRequestDispatcher(urlCreationMissions).forward(req, rep);
		}
		if(action.equals("uploadMission")){
			String title = req.getParameter("Title");
			int idTruck = Integer.parseInt(req.getParameter("Id_Truck"));
			String content = req.getParameter("Content");
			String lieuDepart = req.getParameter("Start");
			String lieuArrivee = req.getParameter("End");
			int idDepart = Integer.parseInt(req.getParameter("Id_Start"));
			int idArrivee = Integer.parseInt(req.getParameter("Id_End"));
			String chargement = req.getParameter("ShipmentCode");
			
			Mission m = new Mission(title, idTruck, content, lieuDepart, lieuArrivee, idDepart, idArrivee, chargement);
			Session sMission = DatabaseOperations.Connection(Mission.class);
			DatabaseOperations.SaveDatas(sMission, m);
			DatabaseOperations.CloseConnection(sMission);
			req.setAttribute("missionId", m.getId());
			getServletContext().getRequestDispatcher(urlCreationMessage).forward(req, rep);
		}
		
		if(action.equals("uploadMessage")){
			long id_camioneur = Long.parseLong(req.getParameter("Id_camioneur"));
			String type = req.getParameter("Type");
			long id_mission = Long.parseLong(req.getParameter("Id_Mission"));
			
			Session sessionUploadMessage = DatabaseOperations.Connection(Message.class);
			Message message = new Message(u.getId(), id_camioneur, false, false, type, id_mission);
			DatabaseOperations.SaveDatas(sessionUploadMessage, message);
			DatabaseOperations.CloseConnection(sessionUploadMessage);
			
			Session sessionReceiver = DatabaseOperations.Connection(User.class);
			Query getReceiver = sessionReceiver.createQuery("from User where Id="+id_camioneur);//doit être 2
			User receiver = (User)getReceiver.list().get(0);
			DatabaseOperations.CloseConnection(sessionReceiver);
			
			Session sessionMission = DatabaseOperations.Connection(Mission.class);
			Query getMission = sessionMission.createQuery("from Mission where Id="+id_mission);
			Mission mission = (Mission)getMission.list().get(0);
			DatabaseOperations.CloseConnection(sessionMission);
			
			StringBuilder textMessage = new StringBuilder();
			textMessage.append("Mission n° "+id_mission+"\n");
			textMessage.append("Envoyé par "+u.getId()+"\n");
			textMessage.append("À "+receiver.getFirstName()+" "+receiver.getLastName() +"\n");
			textMessage.append("Niveau "+message.getType()+"\n");
			textMessage.append("Description : "+mission.getContent()+"\n");
			
			Sender sender = new Sender();
			sender.setSenderMessage(textMessage);
			try {
				sender.sendMessage();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
			System.out.println("envoie du message");
		}
	}
	


	@Override
	public void init() throws ServletException {
		this.urlCreationMissions = getInitParameter("urlCreationMission");
		this.urlTruckMissions = getInitParameter("urlTruckMissions");
		this.urlLOL = getInitParameter("urlLol");
		this.urlCreationMessage = getInitParameter("urlCreationMessage");
		}

}
