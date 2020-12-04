package server;

import java.util.ArrayDeque;
import java.util.Queue;

public final class Server {
    public static Server Instance = new Server();
    public Queue<Object> SendQueue = new ArrayDeque<>();
    public void Send() {

    }

    public void Receive(){

    }
}
