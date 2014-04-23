package fr.nwg.kingdomwar.listener;

import com.artemis.EntitySystem;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.InputProcessor;
import fr.nwg.kingdomwar.listener.system.KeyDownSystemListener;
import fr.nwg.kingdomwar.listener.system.MouseMovedSystemListener;
import fr.nwg.kingdomwar.listener.system.TouchedUpSystemListener;

import java.util.ArrayList;
import java.util.List;

public class InputListener implements InputProcessor {

    private List<KeyDownSystemListener> keyDownSystemListeners;
    private List<MouseMovedSystemListener> mouseMovedSystemListeners;
    private List<TouchedUpSystemListener> touchedUpSystemListeners;

    public InputListener() {
        keyDownSystemListeners = new ArrayList<KeyDownSystemListener>();
        mouseMovedSystemListeners = new ArrayList<MouseMovedSystemListener>();
        touchedUpSystemListeners = new ArrayList<TouchedUpSystemListener>();
    }

    public void register(Object systemListener) {

        if (systemListener instanceof KeyDownSystemListener)
            keyDownSystemListeners.add((KeyDownSystemListener) systemListener);

        if (systemListener instanceof MouseMovedSystemListener)
            mouseMovedSystemListeners.add((MouseMovedSystemListener) systemListener);

        if (systemListener instanceof TouchedUpSystemListener)
            touchedUpSystemListeners.add((TouchedUpSystemListener) systemListener);

    }

    public void remove(Object systemListener) {

        if (systemListener instanceof KeyDownSystemListener)
            keyDownSystemListeners.remove(systemListener);

        if (systemListener instanceof MouseMovedSystemListener)
            mouseMovedSystemListeners.remove(systemListener);

        if (systemListener instanceof TouchedUpSystemListener)
            touchedUpSystemListeners.remove(systemListener);

    }

    @Override
    public boolean keyDown(int i) {
        for (KeyDownSystemListener keyDownSystemListener : keyDownSystemListeners)
            keyDownSystemListener.keyDown(i);
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
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
        for (TouchedUpSystemListener touchedUpSystemListener : touchedUpSystemListeners)
            touchedUpSystemListener.touchUp(i, i2, i3, i4);
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        for (MouseMovedSystemListener mouseMovedSystemListener : mouseMovedSystemListeners)
            mouseMovedSystemListener.mouseMoved(i, i2);
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
