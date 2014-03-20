package fr.nwg.kingdomwar.component;

import com.artemis.Component;

public class AimingComponent extends Component {
    public PositionComponent position;

    public AimingComponent(PositionComponent position) {
        this.position = position;
    }
}
