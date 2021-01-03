package game.client;

import game.config.Config;
import io.game.hub.messageHub.*;
import server.core.GrpcServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link GrpcServer}.
 */
public class GrpcClient {
    private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());
    public final MessageHubGrpc.MessageHubStub stub;
    public GrpcClient(){
        //クライアントの起動
        ManagedChannel channel = ManagedChannelBuilder.forAddress(Config.HOST, Config.PORT)
                .usePlaintext() //証明書なし
                .build();

        //stubの作成
        stub = MessageHubGrpc.newStub(channel);
    }
}
