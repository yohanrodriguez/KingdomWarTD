package fr.nwg.kingdomwar.listener;

import com.badlogic.gdx.InputProcessor;
import fr.nwg.kingdomwar.system.listener.MouseMovedListenerSystem;
import fr.nwg.kingdomwar.system.listener.TouchUpListenerSystem;

public class InputListener implements InputProcessor{

    private TouchUpListenerSystem touchUpListenerSystem;
    private MouseMovedListenerSystem mouseMovedListenerSystem;

    public InputListener(TouchUpListenerSystem touchUpListenerSystem, MouseMovedListenerSystem mouseMovedListenerSystem) {
        this.touchUpListenerSystem = touchUpListenerSystem;
        this.mouseMovedListenerSystem = mouseMovedListenerSystem;
    }

    @Override
    public boolean keyDown(int i) {
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
        touchUpListenerSystem.process(i, i2, i3, i4);
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        mouseMovedListenerSystem.process(x, y);
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
