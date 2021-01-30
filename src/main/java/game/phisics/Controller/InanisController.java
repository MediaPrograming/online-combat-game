package game.phisics.Controller;


import game.phisics.Attackplygon;
import game.phisics.Character;
import game.phisics.Controller.*;
import io.game.hub.positionHub.Behavior;

public class InanisController extends CharaController{
       public InanisController(Character c,Attackplygon a){        
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
        attack.setVecx(0);
        attack.setVecy(0);
            return 50;
    }
    @Override
    public int attack2(){
        attack.setX(character.getX()+character.getWidth());
        attack.setY(character.getY()+character.getHeight()/4);
        attack.setWidth(character.getWidth()/2);
        attack.setHeight(character.getHeight()/2);
        attack.setDamege(50);
        attack.setVecx(0);
        attack.setVecy(0);
            return 50;
    }
    @Override
    public int attack3(){
        attack.setX(character.getX()+character.getWidth()/4);
        attack.setY(character.getY()-character.getHeight()/2);
        attack.setWidth(character.getWidth()/2);
        attack.setHeight(character.getHeight()/2);
        attack.setDamege(50);
        attack.setVecx(0);
        attack.setVecy(0);
             return 50;
     }

     @Override public int attack4(){
        attack.setX(character.getX()-character.getWidth()/4);
        attack.setY(character.getY()+3*character.getHeight()/4);
        attack.setWidth(3*character.getWidth()/2);
        attack.setHeight(character.getHeight()/4);
        attack.setDamege(50);
        attack.setVecx(0);
        attack.setVecy(0);
             return 50;
     }
     @Override public void jump(){
             character.vector(0, -10);
     }
     @Override public void attacking(){
        if(attack.getX()!=-1){
                    attack.setVx(character.getVx());
                    attack.setVy(character.getVy());
                    attack.move();
        }else if(character.getAction()==Behavior.ATTACK1&&character.getTimetomove()==12){  
        }else if(character.getAction()==Behavior.ATTACK2&&character.getTimetomove()==12){
        }else if(character.getAction()==Behavior.ATTACK3&&character.getTimetomove()==12){
        }else if(character.getAction()==Behavior.ATTACK4&&character.getTimetomove()==1){
        }
               
     }
}
