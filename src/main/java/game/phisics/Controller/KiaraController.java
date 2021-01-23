package game.phisics.Controller;

import game.phisics.Attackplygon;
import game.phisics.Character;
import game.phisics.Controller.*;

public class KiaraController extends CharaController{
    public KiaraController(Character c, Attackplygon a){        
     attack=a;
     character=c;
    }
    @Override 
    public int attack1(){
            attack.setX(character.getX()-character.getWidth()/2);
            attack.setY(character.getY()+character.getHeight()/4);
            attack.setWidth(character.getWidth());
            attack.setHeight(character.getHeight()/4);
            attack.setDamege(50);
            attack.setDamagetime(20);
            return 20;
    }
    @Override
    public int attack2(){
            attack.setX(character.getX()+character.getWidth());
            attack.setY(character.getY()+character.getHeight()/4);
            attack.setWidth(character.getWidth());
            attack.setHeight(character.getHeight()/2);
            attack.setDamege(50);
            attack.setDamagetime(20);
            return 20;
    }
    @Override
    public int attack3(){
            attack.setX(character.getX()+character.getWidth()/4);
            attack.setY(character.getY()-character.getHeight()/2);
            attack.setWidth(character.getWidth()/2);
            attack.setHeight(character.getHeight()/2);
            attack.setDamege(50);
            attack.setDamagetime(50);
             return 50;
     }

     @Override public int attack4(){
            attack.setX(character.getX()-character.getWidth()/4);
            attack.setY(character.getY()+3*character.getHeight()/4);
            attack.setWidth(3*character.getWidth()/2);
            attack.setHeight(character.getHeight()/4);
            attack.setDamege(50);
            attack.setDamagetime(50);

             return 50;
     }
     @Override public void jump(){
             character.vector(0, -15);
     }
     @Override public void attacking(){
            if(attack.getX()!=-1){
                    attack.setVx(character.getVx());
                    attack.setVy(character.getVy());
                    attack.move();
                }
               
     }
}