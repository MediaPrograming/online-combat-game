package game.phisics;


import game.phisics.Attackplygon;
import game.phisics.Character;

public abstract class CharaController{
        Attackplygon attack;
        Character character;
        public int attack1(){return 0;}//硬直時間を返す
        public int attack2(){return 0;}
        public int attack3(){return 0;}
        public int attack4(){return 0;}
        public void attacking(){}
        public void jump(){};
}

class GuraController extends CharaController{
       public GuraController(Character c, Attackplygon a){        
        attack=a;
        character=c;
       }
       @Override 
       public int attack1(){
               return 50;
       }
       @Override
       public int attack2(){
               return 50;
       }
       @Override
       public int attack3(){
                return 50;
        }

        @Override public int attack4(){
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
                   }
                  
        }
}
 class KiaraController extends CharaController{
        public KiaraController(Character c, Attackplygon a){        
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
                attack.setX(character.getX()-character.getWidth()/4);
                attack.setY(character.getY()+3*character.getHeight()/4);
                attack.setWidth(3*character.getWidth()/2);
                attack.setHeight(character.getHeight()/4);
                attack.setDamege(50);
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
 class AmeliaController extends CharaController{
        public AmeliaController(Character c, Attackplygon a){        
         attack=a;
         character=c;
        }
        @Override 
        public int attack1(){
                return 50;
        }
        @Override
        public int attack2(){
                return 50;
        }
        @Override
        public int attack3(){
                 return 50;
         }
 
         @Override public int attack4(){
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
                   }
                  
        }
 }
 class InanisController extends CharaController{
        public InanisController(Character c,Attackplygon a){        
         attack=a;
         character=c;
        }
        @Override 
        public int attack1(){
                return 50;
        }
        @Override
        public int attack2(){
                return 50;
        }
        @Override
        public int attack3(){
                 return 50;
         }
 
         @Override public int attack4(){
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
                   }
                  
        }

 }
 class CalliopeController extends CharaController{
        public CalliopeController(Character c,Attackplygon a){        
         attack=a;
         character=c;
        }
        @Override 
        public int attack1(){
                return 50;
        }
        @Override
        public int attack2(){
                return 50;
        }
        @Override
        public int attack3(){
                 return 50;
         }
 
         @Override public int attack4(){
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
                   }
                  
        }

 }