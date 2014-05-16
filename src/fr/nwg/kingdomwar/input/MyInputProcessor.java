package fr.nwg.kingdomwar.input;

import com.artemis.Entity;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.input.TouchedUpComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

public class MyInputProcessor implements InputProcessor {

    private OrthographicCamera camera;
    private Entity inputEntity;
    private PositionComponent cursorPosition;

    public MyInputProcessor(OrthographicCamera camera, Entity inputEntity) {
        this.camera = camera;
        this.inputEntity = inputEntity;
        this.cursorPosition = inputEntity.getComponent(CursorPositionComponent.class).position;
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
    public boolean touchUp(int x, int y, int i3, int i4) {
        Vector3 victor = victorize(x, y);
        inputEntity.addComponent(new TouchedUpComponent(victor.x, victor.y, i3, i4));
        inputEntity.changedInWorld();
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        Vector3 victor = victorize(x, y);
        cursorPosition.x = victor.x;
        cursorPosition.y = victor.y;
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    private Vector3 victorize(int x, int y) {
        Vector3 victor = new Vector3(x, y, 0);
        camera.unproject(victor);
        return victor;
    }
}
