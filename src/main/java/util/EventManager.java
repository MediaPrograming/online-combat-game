package util;

import com.google.gson.Gson;
import com.taku.util.function.Action1.Action;
import com.taku.util.ui.Event;
import server.Server;

import java.util.ArrayDeque;
import java.util.Queue;

public class EventManager {
    public static EventManager Instance = new EventManager();
    public static Queue<Action> ReceiveQueue = new ArrayDeque<>();
    public void Publish(Event event){
        //SendServer
        Gson gson = new Gson();
        var json = gson.toJson(event);
        Server.Instance.SendQueue.add(json);
    }

    ///public void Capture(Event event){

}