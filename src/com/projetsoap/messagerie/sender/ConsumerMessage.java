package com.projetsoap.messagerie.sender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.projetsoap.messagerie.utils.RabbitUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class ConsumerMessage extends Thread {
	
	Connection connection;
	Channel channel;
	Delivery delivery;
	
	public void run() {
		try {
			connection = RabbitUtils.RabbitConnection("localhost");
		} catch (IOException | TimeoutException e1) {
			e1.printStackTrace();
		}
		System.out.println("Client connected");
		
		try {
			channel = RabbitUtils.GetInstantiatedMessengerChannel(connection);
			channel.queueDeclare("queueMission1", false, false, false, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The consumer is waiting for a Message");
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		try {
			channel.basicConsume("queueMission1", true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			System.out.println("Dans la boucle client");
			try {
				delivery = consumer.nextDelivery();
				String messageReceived = new String(delivery.getBody());
				System.out.println("Message reçu : " +messageReceived);
			} catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		}
}




