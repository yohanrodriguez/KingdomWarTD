package fr.nwg.kingdomwar.input;

import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.PositionComponent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by manu on 3/7/14.
 */
public class MockInputManager extends InputManager {
    private int keyPressed;
    private int keyUpped;
    private float accelerometerY;
    private boolean accelerometerAvailable;
    private boolean touched;
    private boolean touchedUp;

    public MockInputManager() {
        accelerometerY = 0;
    }

    public void setInput(int key) {
        keyPressed = key;
    }

    @Override
    public boolean isKeyPressed(int key) {
        return keyPressed == key;
    }

    public void setAccelerometerY(float y) {
        accelerometerAvailable = true;
        this.accelerometerY = y;
    }

    @Override
    public float getAccelerometerY() {
        return accelerometerY;
    }

    @Override
    public boolean isAccelerometerAvailable() {
        return accelerometerAvailable;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    @Override
    public boolean isTouched() {
        return touched;
    }

    @Override
    public boolean isTouchedUp() {
        return touchedUp;
    }

    @Override
    public boolean isKeyUp(int key) {
        return keyUpped == key;
    }

    @Override
    public void reset() {

    }

    @Override
    public Vector3 getTouchPosition() {
        return null;
    }

    @Override
    public Vector3 getCursorPosition() {
        return null;
    }

    public void setKeyUp(int key) {
        this.keyUpped = key;
    }

    public void setTouchedUp(boolean touchedUp) {
        this.touchedUp = touchedUp;
    }
}
