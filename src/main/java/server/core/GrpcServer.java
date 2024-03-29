package server.core;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import game.config.Config;
import game.util.Time;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.service.MessageHubImpl;
import server.service.PositionHubImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Server that manages startup/shutdown of a {@code MessageHub} server.
 */
public class GrpcServer {
  private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());
  private Server server;
  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    //Timeの実行
//    Thread thread = new Thread(Time.Instance);
//    thread.start();

    final GrpcServer grpcServer = new GrpcServer();
    grpcServer.start();
    grpcServer.blockUntilShutdown();
  }

  private void start() throws IOException {
    try {
      var endpoint = Config.GetIPEndPoint();
      server = ServerBuilder.forPort(endpoint.getPort())
              .addService(new MessageHubImpl())
              .addService(new PositionHubImpl())
              .build()
              .start();

      logger.info("Server started, listening on " + endpoint.getPort());

      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
          System.err.println("*** shutting down gRPC server since JVM is shutting down");
          try {
            GrpcServer.this.stop();
          } catch (InterruptedException e) {
            e.printStackTrace(System.err);
          }
          System.err.println("*** server shut down");
        }
      });
    }catch (IOException exception){
       throw new NullPointerException();
    }
  }

  private void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }
}
