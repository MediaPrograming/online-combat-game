package util;

import com.taku.util.model.Unit;
import model.Key;
import model.VCharacter;
import com.taku.util.function.Linq;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Queue;

public class InputManager extends Thread {
    public final static InputManager Instance = new InputManager();
    private Queue<Key> currentKeys = new ArrayDeque<>();
    private Dictionary<Integer, Key> keyDictionary = new Hashtable<>();

    private final Key key_Attack = new Key(KeyEvent.VK_K, 1, obj -> System.out.println("アタックするよ")) ;
    private final Key key_Guard = new Key(KeyEvent.VK_J, 1, obj -> System.out.println("ガードするぜ")) ;
    private final Key key_A = new Key(KeyEvent.VK_A, 2, obj -> ((VCharacter)obj).transform.x += 1) ;
    private final Key key_D = new Key(KeyEvent.VK_D, 2, obj -> ((VCharacter)obj).transform.x -= 1) ;
    private final Key key_Space = new Key(KeyEvent.VK_SPACE, 3, obj -> ((VCharacter)obj).transform.y += 3) ;

    private final Unit unit = new Unit();
    private InputManager(){
        keyDictionary.put(key_A.getKeyCode(), key_A);
        keyDictionary.put(key_D.getKeyCode(), key_D);
        keyDictionary.put(key_Space.getKeyCode(), key_Space);
        keyDictionary.put(key_Attack.getKeyCode(), key_Attack);
        keyDictionary.put(key_Guard.getKeyCode(), key_Guard);
    }

    public void Register(int index){ currentKeys.add(keyDictionary.get(index)); }
    VCharacter character;

    public void Exe(){
        var one = Linq.First(currentKeys, f -> f.getWeight() == 1);
        var two = Linq.First(currentKeys, f -> f.getWeight() == 2);
        var three = Linq.First(currentKeys, f -> f.getWeight() == 3);
        one.Invoke(unit);
        two.Invoke(character);
        three.Invoke(character);
    }

    public void Release(int key){
        currentKeys.forEach(k -> {
            if (k.getKeyCode() == key)
                currentKeys.remove(k);
        });
    }
}

