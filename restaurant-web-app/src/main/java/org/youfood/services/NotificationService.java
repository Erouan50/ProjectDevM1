package org.youfood.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.youfood.utils.RabbitMQConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */

public class NotificationService {

    private BlockingQueue<String> messages;

    private static final String EXCHANGE_NAME = "test";
    private static final String ROUTING_KEY = "test";
    private static final boolean NO_ACK = false;

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    RabbitMQConfiguration configuration = new RabbitMQConfiguration();
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setUsername(configuration.getUsername());
                    factory.setPassword(configuration.getPassword());
                    factory.setHost(configuration.getHostname());
                    factory.setPort(configuration.getPort());
                    Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel();
                    channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
                    String queueName = channel.queueDeclare().getQueue();
                    channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    channel.basicConsume(queueName, NO_ACK, consumer);
                    while (true) {
                        QueueingConsumer.Delivery delivery;
                        try {
                            delivery = consumer.nextDelivery();
                            messages.put(new String(delivery.getBody()));
                            System.out.println("Message: " + new String(delivery.getBody()));
                            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Unable to connect or set up the rabbitmq connection", e);
                }
            }
        }).start();
    }

    public void setMessages(BlockingQueue<String> messages) {
        this.messages = messages;
    }
}
