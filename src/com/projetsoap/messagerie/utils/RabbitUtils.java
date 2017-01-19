package com.projetsoap.messagerie.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitUtils {

	public static final String QUEUE_NAME = "Messenger";

	public static Connection RabbitConnection(String host) throws IOException, TimeoutException{
		ConnectionFactory connFactory = new ConnectionFactory();
		connFactory.setHost(host); //pour l'instant localhost

		//Renvoie un objet Connection précedemment initialisé en localhost
		Connection connection = connFactory.newConnection();
		return connection;
	}

	//L’objet connection permettra de créer des Channel qui serviront à véhiculer les messages

	public static Channel GetInstantiatedMessengerChannel(Connection con) throws IOException{
		//L’objet connection permettra de créer des Channel qui serviront à véhiculer les messages
		Channel channel = con.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		return channel;
	}
	
	public static void EndChannelConections(Connection connection, Channel channel) throws IOException, TimeoutException{
		channel.close();
		connection.close();
	}

}
