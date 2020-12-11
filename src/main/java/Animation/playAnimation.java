package Animation;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class playAnimation {
    animationHolder animations;
    public playAnimation(){
        animations = new animationHolder();
    }




    AnimationTimer timer = new AnimationTimer() {
        long offset = 0;
        @Override    // frame 毎に呼び出されるメソッド handle(now) の実装
        public void handle(long now) { // now (ナノ秒単位) から現在時刻を抽出
            if( offset == 0 ) { // start 直後の最初の呼び出し
                offset = now;     // offset に最初の時刻を代入
            } else {            // その後は経過時間 t（1/60秒単位）を変数として各種プロパティを計算
                double t = (double)( now - offset ) / 1_000_000_000 * 60;
            }
        }
    };
}
