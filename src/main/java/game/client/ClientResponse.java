package game.client;

import com.taku.util.flux.view.BasePanel;
import game.view.service.IFetch;
import game.view.state.RoomState;
import io.game.hub.messageHub.*;
import io.grpc.stub.StreamObserver;

public class ClientResponse extends BasePanel<RoomState, IFetch> {
    public static StreamObserver<GrpcRoomInfo> GetRoomsResponse() {return new StreamObserver<GrpcRoomInfo>() {
        @Override
        public void onNext(GrpcRoomInfo value) {
            var rooms = value.getRoomList();
            System.out.println("ルームの一覧を表示します");
            int i = 0;
            for (var r: rooms){
                System.out.println( "[" + i++ + "]" + r);
            }
        }

        @Override
        public void onError(Throwable t) { }

        @Override
        public void onCompleted() { }
    };}
    public static StreamObserver<ResponseCode> CreateRoomResponse() {return new StreamObserver<ResponseCode>() {
        @Override public void onNext(ResponseCode value) { System.out.println("ResponseCode : " + "[" + value.getCode() +  "]"); }
        @Override public void onError(Throwable t) { }
        @Override public void onCompleted() { }
    };}
    public static StreamObserver<ResponseCode> DeleteRoomResponse() {return new StreamObserver<ResponseCode>() {
        @Override
        public void onNext(ResponseCode value) {
            System.out.println("ResponseCode : " + "[" + value.getCode() +  "]");
        }

        @Override
        public void onError(Throwable t) { }

        @Override
        public void onCompleted() { }
    };}
    public static StreamObserver<Message> StreamEventResponse(){
        return new StreamObserver<Message>() {
            @Override
            public void onNext(Message value) {
                switch (value.getType()){
                    case MESSAGE:
                        System.out.println("[" + value.getUser().getName() + "]" + value.getMessage());
                        break;
                    case JOIN:
                        System.out.println(value.getUser().getName() + "さんが入室しました");
                        break;
                    case LEAVE:
                        System.out.println(value.getUser().getName() + "さんが退室しました");
                        break;
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

//    public static StreamObserver<Message> SendMessageResponse(){ return new StreamObserver<Message>() {
//        @Override
//        public void onNext(Response value) {
//            System.out.println("[" + value.getUser().getName()+ "]" + value.getMessage());
//        }
//
//        @Override
//        public void onError(Throwable t) { }
//
//        @Override
//        public void onCompleted() { }
//    };}
//
//    public static StreamObserver<Response> JoinResponse(){ return new StreamObserver<Response>() {
//        @Override
//        public void onNext(Response value) {
//            System.out.println(value.getUser().getName() + "さんが入室しました");
//        }
//
//        @Override
//        public void onError(Throwable t) { }
//
//        @Override
//        public void onCompleted() { }
//    };}
//
//    public static StreamObserver<User> LeaveResponse(){return new StreamObserver<User>() {
//        @Override
//        public void onNext(User value) {
//            System.out.println(value.getName() + "さんが退室しました");
//        }
//
//        @Override
//        public void onError(Throwable t) {
//
//        }
//
//        @Override
//        public void onCompleted() {
//
//        }
//    };}
}
