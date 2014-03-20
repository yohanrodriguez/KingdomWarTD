package fr.nwg.kingdomwar.component;

import com.artemis.Component;

public class DestinationComponent extends Component {
    public PositionComponent position;

    public DestinationComponent(PositionComponent position) {
        this.position = position;
    }
}
