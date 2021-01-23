package game.phisics;

import game.view.service.ICharacter;
import io.game.hub.positionHub.Behavior;

/**
 * @author Takuya Isaki on 2021/01/14
 * @project online-combat-game
 */
public class PhysicsCalcUtil {
    public static void CharacterCollision(PhysicsObject a, PhysicsObject b) {
        if (a.intersects(b.getX() + b.getVx() - 1 - a.getVx(), b.getY() + b.getVy() - 1 - a.getVy(), b.getWidth() + 2, b.getHeight() + 2)) {
            if (a.intersects(b.getX() + b.getVx() - 1 - a.getVx(), b.getY() - 1, b.getWidth() + 1, b.getHeight() + 1)) {
                if (a.getX() < b.getX()) {
                    //a.setVx((b.getX()-a.getWidth()-2-a.getX()));
                    if (a.getVx() <= 0) {
                        b.setVx(a.getX() + a.getVx() + a.getWidth() + 2 - b.getX());
                    } else if (b.getX() >= 0) {
                        a.setVx(b.getX() + b.getVx() - a.getWidth() - 2 - a.getX());
                    } else {
                        double d = b.getX() - a.getX() - a.getWidth();
                        a.setVx((d * (a.getVx() / (a.getVx() - b.getVx()))));
                        b.setVx((d * (b.getVx() / (a.getVx() - b.getVx()))));
                    }
                } else {
                    if (b.getVx() <= 0) {
                        a.setVx(b.getX() + b.getVx() + b.getWidth() + 2 - a.getX());
                    } else if (a.getVx() >= 0) {
                        b.setVx(a.getX() + a.getVx() - b.getWidth() - 2 - b.getX());
                    } else {
                        double d = a.getX() - b.getX() - b.getWidth();
                        a.setVx((d * (a.getVx() / (-a.getVx() + b.getVx()))));
                        b.setVx((d * (b.getVx() / (-a.getVx() + b.getVx()))));
                    }
                }
            }
            if (a.intersects(b.getX() - 1, b.getY() + b.getVy() - 1 - a.getVy(), b.getWidth() + 2, b.getHeight() + 2)) {
                if (a.getY() < b.getY()) {
                    if (a.getVy() <= 0) {
                        b.setVy((int)(a.getY() + a.getVy() + a.getHeight() + 2 - b.getY()));
                    } else if (b.getVy() >= 0) {
                        a.setVy((int)(b.getY() + b.getVy() - a.getHeight() - 2 - a.getY()));
                    } else {
                        double d=b.getY()-a.getY()-a.getHeight();
                        a.setVy((int)((d*(a.getVy()/(a.getVy()-b.getVy())))));
                        b.setVy((int)((d*(b.getVy()/(a.getVy()-b.getVy())))));
                    }
                    a.setRanded(true);
                } else {
                    if(b.getVy()<=0){
                        a.setVy((int)(b.getY()+b.getVy()+b.getHeight()+2-a.getY()));
                    }else if (a.getVy() >= 0) {
                        b.setVy((int)(a.getY() + a.getVy() - b.getHeight() - 2 - b.getY()));
                    } else {
                        double d=a.getY()-b.getY()-b.getHeight();
                        a.setVy((int)((d*(a.getVy()/(-a.getVy()+b.getVy())))));
                        b.setVy((int)((d*(b.getVy()/(-a.getVy()+b.getVy())))));
                    }
                    b.setRanded(true);
                }

            }

        }
    }

    /**
     * charactor aが攻撃を食らったとき-1,bが食らったとき1を返す。どちらでもないとき0を返す。
     * @param a
     * @param aAttack
     * @param b
     * @param bAttack
     * @return
     */
    public static int isAttackHit(Character a, Attackplygon aAttack, Character b, Attackplygon bAttack){
        if(aAttack.intersects(bAttack.getX(),bAttack.getY(),bAttack.getWidth(),bAttack.getHeight())){
            return 0;
        }
        if(aAttack.intersects(b.getX()+b.getVx(),b.getY()+b.getVy(),b.getWidth(),b.getHeight())){
            if(aAttack.getX()== a.getX()+a.getWidth()/4){
                b.vector(0,-10);
            }else if(aAttack.getX()== a.getX()-a.getWidth()/2){
                b.vector(-5,-2);
            } else if(aAttack.getX()== a.getX()+a.getWidth()){
                b.vector(5,-2);
            }else if(aAttack.getX()== a.getX()-a.getWidth()/4){
                if(b.getX()<=a.getX()){
                    b.vector(-2,-2);
                }else{
                    b.vector(2,-2);
                }
            }
            aAttack.setVisible(false);
            aAttack.setY(-1);
            aAttack.setX(-1);
            aAttack.setWidth(1);
            aAttack.setHeight(1);
            bAttack.setVisible(false);
            bAttack.setY(-1);
            bAttack.setX(-1);
            bAttack.setWidth(1);
            bAttack.setHeight(1);
            b.setTimetomove(aAttack.getDamagetime());
            b.setAction(Behavior.DAMAGE);
            b.Damage(aAttack.getDamage());
            return 1;
        }
        if(bAttack.intersects(a.getX()+a.getVx(),a.getY()+a.getVy(),a.getWidth(),a.getHeight())){
            if(bAttack.getX()== b.getX()+b.getWidth()/4){
                a.vector(0,-10);
            }else if(bAttack.getX()== b.getX()-b.getWidth()/2){
                a.vector(-5,-2);
            }else if(bAttack.getX()== b.getX()+b.getWidth()){
                a.vector(5,-2);
            }else if(bAttack.getX()== b.getX()-b.getWidth()/4){
                if(b.getX()<=a.getX()){
                    a.vector(2,-2);
                }else{
                    a.vector(-2,-2);
                }

            }
            aAttack.setVisible(false);
            aAttack.setY(-1);
            aAttack.setX(-1);
            aAttack.setWidth(1);
            aAttack.setHeight(1);
            a.setTimetomove(bAttack.getDamagetime());
            bAttack.setVisible(false);
            bAttack.setY(-1);
            bAttack.setX(-1);
            bAttack.setWidth(1);
            bAttack.setHeight(1);
            a.setAction(Behavior.DAMAGE);
            a.Damage(bAttack.getDamage());
            return -1;
        }
        return 0;
    }
}
