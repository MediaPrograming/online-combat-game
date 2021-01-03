package game.client;

import io.game.hub.messageHub.*;
import server.core.GrpcServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link GrpcServer}.
 */
public class GrpcClient {
    static int PORT = 50051;
    static String HOST = "localhost";
    private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());
    public final MessageHubGrpc.MessageHubStub stub;
    public GrpcClient(){
        //クライアントの起動
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext() //証明書なしD
                .build();

        //stubの作成
        stub = MessageHubGrpc.newStub(channel);
    }
//
//    void join(User user, String roomName){
//        var request = Message.newBuilder().setType(Type.JOIN).setUser(user).setMessage(roomName).build();
//        var observer = stub.streamEvent(ClientResponse.StreamEventResponse());
//        observer.onNext(request);
//    }
//
//    void leave(User user){
//        var request = Message.newBuilder().setType(Type.LEAVE).setUser(user).build();
//        var observer = stub.streamEvent(ClientResponse.StreamEventResponse());;
//        observer.onNext(request);
//    }
//
//    void getRooms(){
//        stub.getRooms(unitRequest, ClientResponse.GetRoomsResponse());
//    }
//    void sendMessage(){
//        var messageRequest = Message.newBuilder().setType(Type.MESSAGE).setUser(me).setMessage(message).build();
//        var req = stub.streamEvent(ClientResponse.StreamEventResponse());
//        req.onNext(messageRequest);
//    }
//
//    void createRoom(User user, String roomName){
//        var gRoom = GrpcRoom.newBuilder().setRoomName(roomName).setHostName(user.getName()).build();
//        var createRequest = RoomMessage.newBuilder().setUser(user).setRoom(gRoom).build();
//        stub.createRoom(createRequest, ClientResponse.CreateRoomResponse());
//    }
//
//    void deleteRoom(User user){
//        var info = user.getRoomInfo();
//        var gRoom = GrpcRoom.newBuilder().setRoomName(info.getRoomName()).setHostName(info.getHostName()).build();
//        var deleteRequest = RoomMessage.newBuilder().setUser(user).setRoom(gRoom).build();
//        stub.deleteRoom(deleteRequest, ClientResponse.DeleteRoomResponse());
//    }
}
