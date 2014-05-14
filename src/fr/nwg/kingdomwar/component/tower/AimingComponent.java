package fr.nwg.kingdomwar.component.tower;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

public class AimingComponent extends Component {
    public PositionComponent position;

    public AimingComponent(PositionComponent position) {
        this.position = position;
    }
}
