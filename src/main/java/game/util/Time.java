package game.util;

import com.taku.util.flux.service.IUpdate;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public final class Time implements Runnable {
    private double totalTime;
    private double deltaTime;
    private double frameTime;

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false ;
    private double frameRate;

    public double getTotalTime(){ return this.totalTime; }
    public double getDeltaTime() { return this.deltaTime; }
    public double getFrameRate() { return this.frameRate; }

    public static Time Instance = new Time();
    private Time(){
        frameTime=0;
    }
    private final List<IUpdate> updates = new ArrayList<>();
    public void addListener(IUpdate update) {
        updates.add(update);
    }
    public void remoteListener(IUpdate update){updates.remove(update);}
    public void clearUpdates() { updates.clear();}
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            for (IUpdate update : updates) {
                deltaTime = (double) (now) /  1_000_000_000 - totalTime;
                totalTime = (double) now / 1_000_000_000 ;
//                if(totalTime-frameTime>=1){frameTime = totalTime; update.EveryFrameUpdate();}
//                update.EveryFrameUpdate();

                long oldFrameTime = frameTimes[frameTimeIndex] ;
                frameTimes[frameTimeIndex] = now ;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
                if (frameTimeIndex == 0) {
                    arrayFilled = true ;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    frameRate = 1_000_000_000 / elapsedNanosPerFrame ;
                    update.EveryFrameUpdate();
                }
            }
        }
    };

    public void KeyPressed(KeyEvent keyEvent){
        for(var l : updates)
            l.KeyPressed(keyEvent);
    }

    public void KeyReleased(KeyEvent keyEvent){
        for (var l : updates)
            l.KeyReleased(keyEvent);
    }
    @Override
    public void run() {
        timer.start();
    }

}
