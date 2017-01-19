package com.projetsoap.messagerie.sender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.projetsoap.messagerie.utils.RabbitUtils;
import com.projetsoap.srcfiles.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Sender {
	
	private String message;
	
	public Sender() {
		message = null;
	}
	
	public void setSenderMessage(StringBuilder _message){
		this.message = _message.toString();
	}
	public String getSenderMessage(){
		return this.message;
	}
	public void sendMessage() throws IOException, TimeoutException{
		Connection connection = RabbitUtils.RabbitConnection("localhost");
		Channel channel = RabbitUtils.GetInstantiatedMessengerChannel(connection);
		channel.queueDeclare("queueMission1", false, false, false, null);
		channel.basicPublish("", "queueMission1", null, getSenderMessage().getBytes());
		System.out.println("Message envoyé : "+ getSenderMessage());
		RabbitUtils.EndChannelConections(connection, channel);
	}
	
}
