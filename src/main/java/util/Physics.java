package util;

import model.Component;
import service.IPhysics;

public class Physics implements IPhysics {
    Component component;
    public Physics(Component component){
        this.component = component;
    }
    public void AddForce(float x, float y, float thrust){
        System.out.println("AddForce");
    }
}
