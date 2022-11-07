package Recv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class RecvThread implements Runnable {

    public static String IP_ADDRESS = "";
    public static String QUEUE_NAME = "hello";
    public static BlockingQueue<String> queue;

    public RecvThread(String IP_Address, String queueName, BlockingQueue<String> queue) {
        this.IP_ADDRESS = IP_Address;
        this.QUEUE_NAME = queueName;
        this.queue = queue;
    }

    public RecvThread(String IP_Address, String queueName) {
        this.IP_ADDRESS = IP_Address;
        this.QUEUE_NAME = queueName;
    }

    public RecvThread(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }



    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");

            //Put message into the queue. (Assignment2 Requirement/could be deleted)
            try {
                queue.put(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        try {
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
