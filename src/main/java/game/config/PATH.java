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

    public static final String HIT = root + "\\src\\main\\resources\\game\\img\\hiteffect -Sheet.png";

    public static final String Gura_Normal = root + "\\src\\main\\resources\\game\\img\\Gura\\Gura-02-Sheet.png";
    public static final String Gura_Run = root + "\\src\\main\\resources\\game\\img\\Gura\\Gura-03-Sheet.png";
    public static final String Kiara_Normal = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラ-Sheet - .png";
    public static final String Kiara_Run = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラrun-Sheet.png";
    public static final String Kiara_Attack1 = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラyokoattack-Sheet.png";
    public static final String Kiara_Attack3 = root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラupperattack-Sheet.png";
    public static final String Kiara_Attack4 = root +"\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラeat-Sheet.png";
    public static final String Kiara_Jump=root +"\\src\\main\\resources\\game\\img/Kiara\\小鳥遊キアラjump-Sheet.png";
    public static final String Kiara_Damage= root+"\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラdamage-Sheet.png";

    public static final String Amelia_Normal = root + "\\src\\main\\resources\\game\\img\\Amelia\\Amerial-Nornal-01.png";
    public static final String Amelia_Attack1 = root + "\\src\\main\\resources\\game\\img\\Amelia\\Amerial-Attack-01.png";


    public static final String TRIDENT = root + "\\src\\main\\resources\\game\\img\\Gura\\Guratrydent-Sheet.png";

    public static final String BGM = root + "\\src\\main\\resources\\game\\sounds\\Tanukichi.wav";
    public static final String Effect_HIT = root + "\\src\\main\\resources\\game\\sounds\\hit.wav";
}
