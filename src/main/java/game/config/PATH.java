package game.config;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import java.io.File;

public final class PATH {
    public static final String root = new File(".").getAbsolutePath();

    public static final String Back = root + "\\src\\main\\resources\\game\\img\\Back.jpg";
    public static final String Floor = root + "\\src\\main\\resources\\game\\img\\floor.png";

    public static final String Gura_Normal = root + "\\src\\main\\resources\\game\\img\\Gura\\Gura-02-Sheet.png";
    public static final String Gura_Run = root + "\\src\\main\\resources\\game\\img\\Gura\\Gura-03-Sheet.png";
    public static final String Kiara_Normal = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラ-Sheet - .png";
    public static final String Kiara_Run = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラrun-Sheet.png";
    public static final String Kiara_Attack1 = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラupperattack-Sheet.png";
    public static final String Kiara_Attack2 = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラyokoattack-Sheet.png";
    public static final String Kiara_Damage= root+"\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラdamage-Sheet.png";
//    public static final String Effect_HIT = root + "\\src\\main\\resources\\game\\img\\hiteffect-Sheet.png";
    public static final String RIP = root + "\\src\\main\\resources\\game\\sounds\\失礼しますが、RIP.wav";
    public static final String damage = root + "\\src\\main\\resources\\game\\sounds\\hit.wav";
}
