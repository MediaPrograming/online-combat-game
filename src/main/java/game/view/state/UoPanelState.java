package game.view.state;

import io.game.hub.positionHub.CharacterState;

import java.util.Hashtable;

public class UoPanelState {
    public String roomName;
    public String hostName;
    public Hashtable<Integer, CharacterState> characters = new Hashtable<>();
}
