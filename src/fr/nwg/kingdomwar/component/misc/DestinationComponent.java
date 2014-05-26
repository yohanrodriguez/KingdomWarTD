package fr.nwg.kingdomwar.component.misc;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

/**
 * Created by eptwalabha on 25/05/2014.
 */
public class DestinationComponent extends Component {
    public float x;
    public float y;

    public DestinationComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
