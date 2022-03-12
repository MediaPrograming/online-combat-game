package game.phisics.Controller;


import game.phisics.Attackplygon;
import game.phisics.Character;

public abstract class CharaModel {
        Attackplygon attack;
        Character character;
        public int attack1(){return 0;}//硬直時間を返す
        public int attack2(){return 0;}
        public int attack3(){return 0;}
        public int attack4(){return 0;}
        public void attacking(){}
        public void jump(){};
}

 
 