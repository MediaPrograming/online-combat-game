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

    private GrpcClient client;
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
    }
}
