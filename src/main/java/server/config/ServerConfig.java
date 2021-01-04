/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
package server.config;

import io.grpc.Server;

/**
 * This is config class for server. To write static variables in the directory "io.grpc.server".
 */
public final class ServerConfig {
    public static final String HOST = "localhost";
    public static final int PORT = 50051;
}