package game.util;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.BasePanel;
import game.service.IUpdate;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;

import java.util.ArrayList;
import java.util.List;

public final class Time implements Runnable {
    private double totalTime;
    private double deltaTime;
    public double getTotalTime(){
        return this.totalTime;
    }
    public double getDeltaTime() { return this.deltaTime; }

    public static Time Instance = new Time();

    private List<IUpdate> updates = new ArrayList<>();
    public void addListener(IUpdate update) {
        updates.add(update);
    }
    public void remoteListener(IUpdate update){updates.remove(update);}
    public void clearUpdates() { updates.clear();}
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            for (IUpdate update : updates) {
                deltaTime = (double) now;
                totalTime += (double)( now ) / 1_000_000_000 * 60;
                update.EveryFrameUpdate();
            }
        }
    };
    @Override
    public void run() {
        timer.start();
    }
}
