package Recv;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Recv {

    public static String IP_ADDRESS = "35.89.140.129";
    public static int RECV_THREADS_COUNT = 1;
    public static String QUEUE_NAME = "hello";
    public static int DEFAULT_EVENTS_AMOUNT = 200000;

    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/SkierEventInfoDB";
        String username = "postgres";
        String password = "xunandi";

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(DEFAULT_EVENTS_AMOUNT);
        RecvThread receive = new RecvThread(IP_ADDRESS, QUEUE_NAME, queue, username, password, jdbcURL);

        for (int i=0; i<RECV_THREADS_COUNT; i++) {
            new Thread(receive).start();
        }

    }
}
