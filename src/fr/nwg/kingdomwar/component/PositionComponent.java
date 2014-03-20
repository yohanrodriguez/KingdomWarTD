package fr.nwg.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class PositionComponent extends Component {
    public float x;
    public float y;

    public PositionComponent() {
        this(0, 0);
    }

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PositionComponent(Vector3 position) {
        this(position.x, position.y);
    }

    public PositionComponent(PositionComponent position) {
        this(position.x, position.y);
    }
}
