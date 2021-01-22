package game.config;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import java.io.File;

public final class PATH {
    public static final String root = new File(".").getAbsolutePath();
    public static final String Img1 = root + "\\src\\main\\resources\\img\\c1.png";
    public static final String Img2 = root + "\\src\\test\\resources\\img1.gif";
    public static final String Img3 = root + "\\src\\main\\resources\\game\\img\\ground.jpg";

    public static final String Gura_Normal = root + "\\src\\main\\resources\\game\\img\\Gura\\Gura-02-Sheet.png";
    public static final String Gura_Run = root + "\\src\\main\\resources\\game\\img\\Gura\\Gura-03-Sheet.png";
    public static final String Kiara_Normal = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラ-Sheet - .png";
    public static final String Kiara_Run = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラrun-Sheet.png";

//    public static final String Effect_HIT = root + "\\src\\main\\resources\\game\\img\\hiteffect-Sheet.png";
    public static final String RIP = root + "\\src\\main\\resources\\game\\sounds\\失礼しますが、RIP.wav";
}
