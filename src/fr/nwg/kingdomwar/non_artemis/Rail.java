package fr.nwg.kingdomwar.non_artemis;

import fr.nwg.kingdomwar.component.misc.DestinationComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Eptwalabha
 * Date: 07/06/2014
 * Time: 00:41
 */
public class Rail {
    private List<DestinationComponent> destinations = new ArrayList<DestinationComponent>();

    public void addDestination(DestinationComponent destination) {
        destinations.add(destination);
    }

    public DestinationComponent getDestination(int index) {
        return (index < destinations.size()) ? destinations.get(index) : null;
    }

    public List<DestinationComponent> getAllDestinations() {
        return destinations;
    }
}
