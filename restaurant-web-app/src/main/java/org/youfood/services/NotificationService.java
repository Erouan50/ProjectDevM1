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
@Startup
@Singleton
public class NotificationService {

    @EJB
    private NotificationServiceListener notificationListener;

    @PostConstruct
    public void startNotificationListener() {
        notificationListener.notificationListener();
    }

}
