package Send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class SendThread implements Runnable{

    private final static String QUEUE_NAME = "hello";
    private final static int MESSAGE_COUNT = 1000;
    private static String RMQ_ADDRESS = "35.162.219.135";

    public SendThread() {
    }

    public SendThread(String IP_ARRESS) {
        RMQ_ADDRESS = IP_ARRESS;
    }

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RMQ_ADDRESS);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";

            for (int i=0; i<MESSAGE_COUNT; i++) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
