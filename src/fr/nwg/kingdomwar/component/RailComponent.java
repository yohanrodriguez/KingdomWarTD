package fr.nwg.kingdomwar.component;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.non_artemis.Rail;

public class RailComponent extends Component {

    private Rail rail;
    private int index = 0;

    public RailComponent(Rail rail) {
        this.rail = rail;
        index = 0;
    }

    public DestinationComponent getNextDestination() {
        DestinationComponent destinationComponent = rail.getDestination(index);
        if (destinationComponent == null)
            return null;
        return destinationComponent;
    }

    public void moveIndexForward() {
        index++;
    }
}
