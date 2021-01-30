package game.phisics.Controller;

import game.phisics.Controller.*;
import game.phisics.Character;
import game.phisics.*;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.Direction;

public class AmeliaController extends CharaController{
    public AmeliaController(Character c, Attackplygon a){        
     attack=a;
     character=c;
    }
    @Override 
    public int attack1(){

            return 70;
    }
    @Override
    public int attack2(){

            return 70;
    }
    @Override
    public int attack3(){

        return 48;
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
            if(character.getAction()!=Behavior.ATTACK1&&character.getAction()!=Behavior.ATTACK2&&attack.getX()!=-1){
                    attack.setVx(character.getVx());
                    attack.setVy(character.getVy());
                    attack.move();
            }else if(character.getAction()==Behavior.ATTACK1&&character.getTimetomove()==25){
                attack.setX(character.getX()-character.getWidth()/2);
                attack.setY(character.getY()+character.getHeight()/4);
                attack.setWidth(character.getWidth()/2);
                attack.setHeight(character.getHeight()/2);
                attack.setVx(-10);
                attack.setDamege(50);
                attack.setVecx(0);
                attack.setVecy(0);
            }else if(character.getAction()==Behavior.ATTACK1&&character.getTimetomove()<25){
                attack.move();
            }else if(character.getAction()==Behavior.ATTACK2&&character.getTimetomove()==25){
                attack.setX(character.getX()+character.getWidth());
                attack.setY(character.getY()+character.getHeight()/4);
                attack.setWidth(character.getWidth()/2);
                attack.setHeight(character.getHeight()/2);
                attack.setVx(10);
                attack.setDamege(50);
                attack.setVecx(0);
                attack.setVecy(0);
            }else if(character.getAction()==Behavior.ATTACK2&&character.getTimetomove()<25){
                attack.move();
            }else if(character.getAction()==Behavior.ATTACK3&&character.getTimetomove()==36){
                var x = character.getWidth() * (character.getDirection() == Direction.LEFT ? -1 : 1);
                attack.setX(character.getX());
                attack.setY(character.getY() - character.getY());
                attack.setWidth(character.getWidth()/2);
                attack.setHeight(character.getHeight()/2);
                attack.setDamege(50);
                var vecX = 5 * (character.getDirection() == Direction.LEFT ? -1 : 1);
                attack.setVecx(vecX);
                attack.setVecy(0);
            }else if(character.getAction()==Behavior.ATTACK4&&character.getTimetomove()==1){
            }
               
     }
}
