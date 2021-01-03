package game.client;

import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.RoomMessage;
import io.game.hub.messageHub.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.UUID;

public class Entry extends Application {

    GrpcClient client;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //UIの初期化
        Parent start = FXMLLoader.load(Entry.class.getResource("../../../fxml/Start.fxml"));
        Scene panel = new Scene(start);
        primaryStage.setScene(panel);
        primaryStage.show();

        //clientの初期化
        client = new GrpcClient();
        //ユーザの作成
        System.out.print("ユーザーネームを入力してください : ");
        Scanner name = new Scanner(System.in);
        String username = name.nextLine();
        User user = User.newBuilder().setName(username).setId(UUID.randomUUID().hashCode()).build(); //IDはUUIDを用いて適当な文字列を生成する

        //
//        while (true) {
//            System.out.print("modeを入力してください : ");
//            //テキストの入力
//            Scanner scanner = new Scanner(System.in);
//            String message = scanner.nextLine();
//
//            if (message.equals("exit")) {
//                System.out.print("アプリケーションを終了します");
//                return;
//            } else if (message.equals("delete")) {
//                System.out.print("削除するルームを入力してください : ");
//                message = scanner.nextLine();
//                var gRoom = GrpcRoom.newBuilder().setRoomName(message).setHostName(me.getName()).build();
//                var createRequest = RoomMessage.newBuilder().setUser(me).setRoom(gRoom).build();
//                stub.createRoom(createRequest, ClientResponse.DeleteRoomResponse());
//            } else if (message.equals("create")) {
//                System.out.print("作成するルームを入力してください : ");
//                message = scanner.nextLine();
//
//                var gRoom = GrpcRoom.newBuilder().setRoomName(message).setHostName(me.getName()).build();
//                var createRequest = RoomMessage.newBuilder().setUser(me).setRoom(gRoom).build();
//                stub.createRoom(createRequest, ClientResponse.CreateRoomResponse());
//            } else if (message.equals("getRooms")) {
//
//            } else if (message.equals("sendMessage")) {
//
//            } else if (message.equals("join")) {
//                System.out.print("参加するRoomを入力してください : ");
//                message = scanner.nextLine();
//            } else if (message.equals("leave")) {
//
//            } else {
//                System.out.println("invalid input context!!");
//                System.out.println("try again");
//            }
//        }
    }
}
