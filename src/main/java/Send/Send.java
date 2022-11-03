package Send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {

    private final static String QUEUE_NAME = "hello";
    private final static int SEND_THREADS_COUNT = 10;
    private final static String RMQ_ADDRESS = "18.237.102.104";

    public static void main(String[] argv) throws Exception {

        SendThread send = new SendThread(RMQ_ADDRESS);
        for (int i=0; i<SEND_THREADS_COUNT; i++) {
            new Thread(send).start();
        }
    }
}
