package game.client;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import game.config.Config;
import game.config.PATH;
import io.game.hub.messageHub.*;
import io.game.hub.positionHub.PositionHubGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import server.core.GrpcServer;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

//import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link GrpcServer}.
 */
public class GrpcClient {
    //private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());
    public User user;
    public GrpcRoom grpcRoom;
    public final MessageHubGrpc.MessageHubStub stub;
    public final PositionHubGrpc.PositionHubStub positionHubStub;
    private final ManagedChannel channel;
    public GrpcClient(){
        //クライアントの起動

            var endpoint = Config.GetIPEndPoint();
            channel = ManagedChannelBuilder.forAddress(endpoint.getIp(), endpoint.getPort())
                    .usePlaintext() //証明書なし
                    .build();
            //stubの作成
            stub = MessageHubGrpc.newStub(channel);
            positionHubStub = PositionHubGrpc.newStub(channel);



    }

    public boolean IsConnected(){
        return !channel.isShutdown();
    }
    public void Close(){
        if(!channel.isShutdown()){
            channel.shutdown();
        }
    }
}
