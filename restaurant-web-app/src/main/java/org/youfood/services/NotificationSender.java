package org.youfood.services;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.AMQBasicProperties;
import org.youfood.utils.RabbitMQConfiguration;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class NotificationSender {

    private static final String EXCHANGE_NAME = "test";
    private static final String ROUTING_KEY = "test";
    private static final boolean NO_ACK = false;

    public void sendOrder(String order) {
        try {
            RabbitMQConfiguration configuration = new RabbitMQConfiguration();
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(configuration.getUsername());
            factory.setPassword(configuration.getPassword());
            factory.setHost(configuration.getHostname());
            factory.setPort(configuration.getPort());
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, order.getBytes());
            channel.close();
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to connect or set up the rabbitmq connection", e);
        }
    }
}
