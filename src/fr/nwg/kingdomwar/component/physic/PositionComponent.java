package fr.nwg.kingdomwar.component.physic;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class PositionComponent extends Component {

    public float x;
    public float y;
    public PositionComponent origin;

    public PositionComponent() {
        this(0, 0);
    }

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
        origin = null;
    }

    public PositionComponent(Vector3 position) {
        this(position.x, position.y);
    }

    public PositionComponent(Vector3 position, int offsetX, int offsetY) {
        this(position.x, position.y, offsetX, offsetY);
    }

    public PositionComponent(PositionComponent position) {
        this(position.x, position.y);
    }

    public PositionComponent(PositionComponent position, float offsetX, float offsetY) {
        this.origin = position;
        this.y = offsetY;
        this.x = offsetX;
    }

    public PositionComponent(float x, float y, float offsetX, float offsetY) {
        this(new PositionComponent(x, y), offsetX, offsetY);
    }

    public float getRealPositionX() {
        if (origin == null)
            return x;
        return origin.getRealPositionX() + x;
    }

    public float getRealPositionY() {
        if (origin == null)
            return y;
        return origin.getRealPositionY() + y;
    }

    public Vector3 getVector3() {
        return new Vector3(x, y, 0);
    }
}
