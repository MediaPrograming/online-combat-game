package server.room;

/**
 * @author Takuya Isaki on 2021/01/21
 * @project online-combat-game
 */

import javafx.animation.AnimationTimer;


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
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
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
            }
        }
    };
    @Override
    public void run() {
        timer.start();
    }

}
