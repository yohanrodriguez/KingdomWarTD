package fr.nwg.kingdomwar.component.input;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

/**
 * Created by manu on 16/05/14.
 */
public class CursorPositionComponent extends Component{

    public PositionComponent position;

    public CursorPositionComponent() {
        this(new PositionComponent());
    }

    public CursorPositionComponent(PositionComponent positionComponent) {
        this.position = positionComponent;
    }
}
