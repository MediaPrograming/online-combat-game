package game.util;

/**
 * @author Takuya Isaki on 2021/01/14
 * @project online-combat-game
 */


public class Input {
    protected boolean a=false,s=false,d=false,w=false,jumped=false,atk=false;
    public void keyPressed(String c){
        switch (c){
            case "A":
                // System.out.println("a is pressed");
                a=true;
                if(d) d=false;
                break;
            case "S":
                s=true;
                //System.out.println("s is pressed");
                break;
            case "D":
                d=true;
                // System.out.println("d is pressed");
                if(a) a=false;
                break;
            case "W":
                // System.out.println("w is pressed");
                w=true;
                break;
            case "K":
                atk=true;
                break;
        }
    }
    public void keyReleased(String c){
        switch (c){
            case "A":
                a=false;
                //         System.out.println("a is released");
                break;
            case "S":
                s=false;
                //           System.out.println("s is released");
                break;
            case "D":
                d=false;
                //             System.out.println("d is released");
                break;
            case "W":
                w=false;
                //               System.out.println("w is released");
                break;
            case "K":
                atk=false;
                break;
        }
    }
}
