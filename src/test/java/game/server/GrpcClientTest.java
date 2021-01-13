package game.server;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import game.client.GrpcClient;
import io.game.hub.messageHub.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import io.grpc.stub.StreamObserver;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * RESTAPI確認用のTestプログラム
 * serverを事前に起動しておく必要がある
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GrpcClientTest {
    String roomName = "testRoom", userName = "TestUser";
    GrpcRoom gRoom = GrpcRoom.newBuilder().setRoomName(roomName).setHostName(userName).build();
    User testUser = User.newBuilder().setId(UUID.randomUUID().hashCode()).setName(userName).build();
//    @Before
//    public void LaunchServer() throws IOException, InterruptedException {
//        MessageServer.main(new String[]{"args"});
//
//    }
    /**
     * ルーム作成のテスト
     * roomNameとuserNameで作成する
     *
     * @throws Exception
     */
    @Test
    public void Test01_CreateRoomTest() throws Exception{
        CountDownLatch lock = new CountDownLatch(1);
        GrpcClient client = new GrpcClient();
        var createRequest = RoomMessage.newBuilder().setUser(testUser).setRoom(gRoom).build();
        client.stub.createRoom(createRequest, new StreamObserver<>() {
            @Override public void onNext(ResponseCode value) {
                if(value.getCode() == 200){
                    System.out.println("[SUCCESS]");
                    lock.countDown();
                }else{
                    System.out.println("[FAIL]Response code is " + value.getCode());
                    lock.countDown();
                }
                System.out.println("ResponseCode : " + "[" + value.getCode() + "]");
                assertThat(200, is(value.getCode()));
            }
            @Override public void onError(Throwable t) { System.out.println(t.toString()); }
            @Override public void onCompleted() { }
        });


        //まあ2秒待てばいいっしょ
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Room取得のテスト
     * createRoomTestで作成したroomをそのまま活用するのでserverはTest実行前に起動し、
     * CreateリクエストはTestプログラムで発行されるものだけにしなくてはいけない
     * @throws Exception
     */
    @Test
    public void Test02_GetRoomsTest() throws Exception{
        CountDownLatch lock = new CountDownLatch(1);
        GrpcClient client = new GrpcClient();
        UnitRequest request = UnitRequest.newBuilder().build();
        client.stub.getRooms(request, new StreamObserver<GrpcRoomInfo>() {
            @Override
            public void onNext(GrpcRoomInfo value) {
                var room = value.getRoomList().get(0);
                System.out.println("roomName = " + room.getRoomName()+ "\n " +
                        "hostName = " + room.getHostName());
                assertThat(room, is(gRoom));
                lock.countDown();
            }
            @Override public void onError(Throwable t) { System.out.println("[ERROR]" + t.toString());}
            @Override public void onCompleted() { }
        });


        lock.await(2000, TimeUnit.MILLISECONDS);
    }


//    /**
//     * Room削除のテスト
//     * 先にルームの作成が行われている必要がある
//     * @throws Exception
//     */
//    public void DeleteRoomTest() throws Exception{
//        CountDownLatch lock = new CountDownLatch(1);
//        GrpcClient client = new GrpcClient();
//        var deleteRequest = RoomMessage.newBuilder().setUser(testUser).setRoom(gRoom).build();
//        client.stub.deleteRoom(deleteRequest, new StreamObserver<>() {
//            @Override public void onNext(ResponseCode value) {
//                if(value.getCode() == 200){
//                    System.out.println("[SUCCESS]");
//                    lock.countDown();
//                }else{
//                    System.out.println("[FAIL]Response code is " + value.getCode());
//                    lock.countDown();
//                }
//                System.out.println("ResponseCode : " + "[" + value.getCode() +  "]");
//                assertThat(200, is(value.getCode()));
//            }
//            @Override public void onError(Throwable t) { System.out.println(t.toString()); }
//            @Override public void onCompleted() { }
//        });
//
//        //まあ2秒待てばいいっしょ
//        lock.await(2000, TimeUnit.MILLISECONDS);
//    }
}
