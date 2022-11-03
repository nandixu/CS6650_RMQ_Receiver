package Recv;

public class Recv {

    public static String IP_ADDRESS = "18.237.102.104";
    public static int RECV_THREADS_COUNT = 1;
    public static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        RecvThread receive = new RecvThread(IP_ADDRESS, QUEUE_NAME);

        for (int i=0; i<RECV_THREADS_COUNT; i++) {
            new Thread(receive).start();
        }

    }
}
