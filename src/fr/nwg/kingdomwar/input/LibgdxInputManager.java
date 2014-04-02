package fr.nwg.kingdomwar.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.PositionComponent;

/**
 * Created by manu on 08/03/14.
 */
public class LibgdxInputManager extends InputManager {
    private boolean keyUpped;
    private int lastKeyUpped;
    private boolean touchUpped;

    public LibgdxInputManager() {
        MyInputProcessor inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public boolean isKeyPressed(int key) {
        return Gdx.input.isKeyPressed(key);
    }

    @Override
    public float getAccelerometerY() {
        return Gdx.input.getAccelerometerY();
    }

    @Override
    public boolean isAccelerometerAvailable() {
        return Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Override
    public boolean isTouched() {
        return Gdx.input.isTouched();
    }

    @Override
    public boolean isTouchedUp() {
        return touchUpped;
    }

    @Override
    public boolean isKeyUp(int key) {
        if(keyUpped)
            return lastKeyUpped == key;

        return false;
    }

    @Override
    public void reset() {
        keyUpped = false;
        touchUpped = false;
    }

    @Override
    public Vector3 getTouchPosition() {
        return new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    }

    @Override
    public Vector3 getCursorPosition() {
        return new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    }

    private class MyInputProcessor implements InputProcessor {
        @Override
        public boolean keyDown(int i) {
            return false;
        }

        @Override
        public boolean keyUp(int key) {
            keyUpped = true;
            lastKeyUpped = key;
            return true;
        }

        @Override
        public boolean keyTyped(char c) {
            return false;
        }

        @Override
        public boolean touchDown(int i, int i2, int i3, int i4) {
            return false;
        }

        @Override
        public boolean touchUp(int i, int i2, int i3, int i4) {
            touchUpped = true;
            return true;
        }

        @Override
        public boolean touchDragged(int i, int i2, int i3) {
            return false;
        }

        @Override
        public boolean mouseMoved(int i, int i2) {
            return false;
        }

        @Override
        public boolean scrolled(int i) {
            return false;
        }
    }
}
