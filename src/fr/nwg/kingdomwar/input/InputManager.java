package fr.nwg.kingdomwar.input;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.PositionComponent;

/**
 * Created by manu on 3/7/14.
 */
public abstract class InputManager {
    public abstract boolean isKeyPressed(int key);
    public abstract float getAccelerometerY();
    public abstract boolean isAccelerometerAvailable();
    public abstract boolean isTouched();
    public abstract boolean isTouchedUp();
    public abstract boolean isKeyUp(int key);
    public abstract void reset();
    public abstract Vector3 getTouchPosition();
}
