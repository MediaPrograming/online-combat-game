package game.config;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */

import com.google.gson.Gson;
import game.store.StoreManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public final class Config {
    public static final int WINDOW_WIDTH = 1080, WINDOW_HEIGHT = 720; // Window Size
//    public static final String HOST = "127.0.0.1";
//    public static final int PORT = 50051;
    private static String path = "\\config.json";

    public static final IPEndpoint GetIPEndPoint()  {
        try {

            //jsonからipとportを取得
            File file = new File(PATH.root + path);
            System.out.println(PATH.root + path);

            FileReader filereader = new FileReader(file);
            int ch = filereader.read();
            String json = "";
            while (ch != -1) {
                json += (char) ch;
                ch = filereader.read();
            }
            Gson gson = new Gson();
            IPEndpoint endpoint = gson.fromJson(json, IPEndpoint.class);
            return endpoint;
        }catch (Exception e){
            throw new NullPointerException();
        }
    }
}
