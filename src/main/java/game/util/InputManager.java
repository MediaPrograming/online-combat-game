package game.util;

import game.model.Key;
import javafx.scene.input.KeyCode;

import java.util.ArrayDeque;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Queue;

public class InputManager {
    public final static InputManager Instance = new InputManager();
    private Queue<Key> currentKeys = new ArrayDeque<>();
    private Dictionary<KeyCode, Key> keyDictionary = new Hashtable<>();

    private final Key key_Attack = new Key(KeyCode.K, 1, () -> System.out.println("アタックするよ")) ;
    private final Key key_Guard = new Key(KeyCode.J, 1, () -> System.out.println("ガードするぜ")) ;
    private final Key key_A = new Key(KeyCode.A, 2, () -> System.out.println("A")) ;
    private final Key key_D = new Key(KeyCode.D, 2, () -> System.out.println("D")) ;
    private final Key key_Space = new Key(KeyCode.SPACE, 3, () -> System.out.println("Space")) ;
    private InputManager(){
        keyDictionary.put(key_A.getKeyCode(), key_A);
        keyDictionary.put(key_D.getKeyCode(), key_D);
        keyDictionary.put(key_Space.getKeyCode(), key_Space);
        keyDictionary.put(key_Attack.getKeyCode(), key_Attack);
        keyDictionary.put(key_Guard.getKeyCode(), key_Guard);
    }

    public void Register(KeyCode key){
//        var d = keyDictionary.get(key);
//        if(d == null ) {
//            System.out.println("存在しないキー");
//            return;
//        }
//        if(currentKeys.contains(d)) return;
//        currentKeys.add(d);
//        System.out.println(d + " : Addした");
        switch (key){
            case A:
                key_A.Invoke();
                break;
            case D:
                key_D.Invoke();
                break;
        }
    }

    public void Exe(){
        if(currentKeys.isEmpty()) return;
        for (Key k : currentKeys){
            k.Invoke();
        }

//        var one = Linq.First(currentKeys, f -> f.getWeight() == 1);
//        var two = Linq.First(currentKeys, f -> f.getWeight() == 2);
//        var three = Linq.First(currentKeys, f -> f.getWeight() == 3);
//        if(one != null)one.Invoke();
//        if (two != null)two.Invoke();
//        if (three != null)three.Invoke();

    }

    public void Release(KeyCode key){
//        if(!currentKeys.contains(key)) return;
//        currentKeys.forEach(k -> {
//            if (k.getKeyCode() == key)
//                currentKeys.remove(k);
//        });
    }
}

