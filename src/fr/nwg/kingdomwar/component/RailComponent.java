package fr.nwg.kingdomwar.component;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.List;

public class RailComponent extends Component {

    private List<DestinationComponent> destinations = new ArrayList<DestinationComponent>();
    private int index = 0;

    public void addDestination(DestinationComponent destination) {
        destinations.add(destination);
    }

    public DestinationComponent getNextDestination() {
        return (index < destinations.size()) ? destinations.get(index++) : null;
    }
}
