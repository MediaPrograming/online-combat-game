package game.phisics.Controller;

import game.config.CharaData.Gura;
import game.phisics.Controller.*;
import game.phisics.Attackplygon;
import game.phisics.Character;
import io.game.hub.positionHub.Behavior;

public class GuraController extends CharaController{
    public GuraController(Character c, Attackplygon a){        
     attack=a;
     character=c;
    }
    @Override 
    public int attack1(){
        attack.setX(character.getX()-character.getWidth()/2);
        attack.setY(character.getY()+character.getHeight()/4);
        attack.setWidth(character.getWidth()/2);
        attack.setHeight(character.getHeight()/2);
        attack.setDamege(50);
            return 50;
    }
    @Override
    public int attack2(){
        attack.setX(character.getX()+character.getWidth());
        attack.setY(character.getY()+character.getHeight()/4);
        attack.setWidth(character.getWidth()/2);
        attack.setHeight(character.getHeight()/2);
        attack.setDamege(50);
            return 50;
    }
    @Override
    public int attack3(){
        attack.setX(character.getX()+character.getWidth()/4);
        attack.setY(character.getY()-character.getHeight()/2);
        attack.setWidth(character.getWidth()/2);
        attack.setHeight(character.getHeight()/2);
        attack.setDamege(50);
             return 50;
     }

     @Override public int attack4(){
        attack.setDamege(100);
             return 80;
     }
     @Override public void jump(){
             character.vector(0, -20);
     }
     @Override public void attacking(){
            if(character.getAction()==Behavior.ATTACK1){
            }else if(character.getAction()==Behavior.ATTACK2&&character.getTimetomove()==12){
            }else if(character.getAction()==Behavior.ATTACK3&&character.getTimetomove()==12){
            }else if(character.getAction()==Behavior.ATTACK4){
                if(character.getTimetomove()>50){
                    attack.setWidth(Gura.TRIDENT_W/(character.getTimetomove()-49));
                    attack.setHeight(Gura.TRIDENT_H/(character.getTimetomove()-49));
                    attack.setX(character.getX()-character.getWidth()/2+attack.getWidth()/2);
                    attack.setY(character.getY()-character.getHeight()/4+(character.getTimetomove()-50)-attack.getHeight()/2);

                }else if(character.getTimetomove()==50) {
                    attack.setWidth(Gura.TRIDENT_W);
                    attack.setHeight(Gura.TRIDENT_H);
                    switch (character.getDirection()) {
                        case LEFT:
                            attack.setVx(-15);
                            break;
                        case RIGHT:
                            attack.setVx(15);
                    }
                }else {if(attack.getX()!=-1)attack.setVy((50-character.getTimetomove())/3);attack.move();}
            }
     }
}
