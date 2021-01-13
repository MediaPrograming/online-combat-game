package game.util;

import com.taku.util.flux.view.BasePanel;
import game.service.IUpdate;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;

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
    private void Time(){
        frameTime=0;
    }
    private List<IUpdate> updates = new ArrayList<>();
    public void addListener(IUpdate update) {
        updates.add(update);
    }
    private AnimationTimer timer = new AnimationTimer() {
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
    @Override
    public void run() {
        timer.start();
    }

}
