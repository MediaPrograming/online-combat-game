package game.phisics;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class Physics extends Application
{



    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage theStage)
    {
//
//        Group root =new Group();
//        PhysicsObject chareattack=new PhysicsObject(0,0,1,1);
//        Character chare=new Character(50,50,100,100,chareattack);
//        PhysicsObject enemyattack=new PhysicsObject(0,0,1,1);
//        Character enemy=new Character(500,50,100,100,enemyattack);
//        PhysicsObject flore=new PhysicsObject(50,500,1000,100);
//        root.getChildren().add(chare);
//        root.getChildren().add(chareattack);
//        root.getChildren().add(enemy);
//        root.getChildren().add(enemyattack);
//        root.getChildren().add(flore);
//        Scene scene =new Scene(root);
//        scene.setOnKeyPressed(
//                new EventHandler<KeyEvent>() {
//                    @Override
//                    public void handle(KeyEvent event) {
//                        String c =event.getCode().toString();
//                        chare.keyPressed(c);
//                    }
//                }
//        );
//        scene.setOnKeyReleased(
//                new EventHandler<KeyEvent>() {
//                    @Override
//                    public void handle(KeyEvent event) {
//                        String c =event.getCode().toString();
//                        chare.keyReleased(c);
//                    }
//                }
//        );
//        Timeline batlletimer=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
//            int count=0;//敵を適当に動かすようなので消す。
//            public void handle(ActionEvent event) {                //毎フレームごとに呼び出す処理
//                chare.fall();
//                enemy.fall();
//                chare.keycheck();
//                if(count==0){
//                    enemy.keyPressed("K");
//                    enemy.keyPressed("A");
//                }
//              /*
//                if(count==20){
//                    enemy.keyreleased("A");
//                }
//                if(count==40){
//                    enemy.keypressed("D");
//                }敵の挙動確認用*/
//                count++;
//                count=count%120;
//                enemy.keycheck();
//                PhysicsCalcUtil.isAttackHit(chare,chareattack,enemy,enemyattack);
//                if(chare.getHP()<=0||enemy.getHP()<=0){
//                    if(chare.getHP()<=0){
//                        //chareの勝利アニメーションとか
//                    }  else {
//                        //enemyの勝利アニメーションとか
//                    }
//                }
//                PhysicsCalcUtil.CharacterCollision(chare,flore);
//                PhysicsCalcUtil.CharacterCollision(enemy,flore);
//                PhysicsCalcUtil.CharacterCollision(chare,enemy);
//                if(!chare.intersects(flore.getX()-1-chare.getVx(),flore.getY()-2-chare.getVy(),flore.getWidth()+1,flore.getHeight()+1)&&!chare.intersects(enemy.getX()-1-chare.getVx(),enemy.getY()-2-chare.getVy(),enemy.getWidth()+1,enemy.getHeight()+1)){
//                    chare.setRanded(false);
//                };
//                if(!enemy.intersects(flore.getX()-1-chare.getVx(),flore.getY()-2-enemy.getVy(),flore.getWidth()+1,flore.getHeight()+1)&&!enemy.intersects(chare.getX()-1-enemy.getVx(),chare.getY()-2-enemy.getVy(),chare.getWidth()+1,chare.getHeight()+1)){
//                    enemy.setRanded(false);
//                }
//                //System.out.println(chare.intersects(enemy.getX()-1-chare.getVx(),enemy.getY()-2-chare.getVy(),enemy.getWidth()+1,enemy.getHeight()+1));
//                chare.move();
//                enemy.move();
//               }
//        }, new javafx.animation.KeyValue[]{}));
//        batlletimer.setCycleCount(Timeline.INDEFINITE);
//        batlletimer.play();
//
//        theStage.setScene(scene);
//        theStage.setTitle("Battle game");
//        theStage.show();


    }
}
