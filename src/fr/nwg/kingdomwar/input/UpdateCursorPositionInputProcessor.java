package fr.nwg.kingdomwar.input;

import com.artemis.Entity;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import javafx.scene.Camera;

public class UpdateCursorPositionInputProcessor implements InputProcessor {

    private OrthographicCamera camera;
    private PositionComponent position;

    public UpdateCursorPositionInputProcessor(OrthographicCamera camera, PositionComponent positionComponent) {
        this.camera = camera;
        this.position = positionComponent;
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
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        Vector3 victor = new Vector3(x, y, 0);
        camera.unproject(victor);
        position.x = victor.x;
        position.y = victor.y;
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
