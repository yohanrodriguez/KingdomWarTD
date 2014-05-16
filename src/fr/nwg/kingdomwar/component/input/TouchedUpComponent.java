package fr.nwg.kingdomwar.component.input;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

/**
 * Created by manu on 16/05/14.
 */
public class TouchedUpComponent extends Component {
    public PositionComponent position;

    public TouchedUpComponent(float x, float y, int i3, int i4) {
        position = new PositionComponent(x, y);
    }
}
