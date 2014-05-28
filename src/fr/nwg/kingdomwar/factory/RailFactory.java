package fr.nwg.kingdomwar.factory;

import com.artemis.Component;
import fr.nwg.kingdomwar.component.RailComponent;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;

public class RailFactory {

    public static RailComponent getSimpleRail() {
        RailComponent rail = new RailComponent();
        rail.addDestination(new DestinationComponent(400, 400));
        rail.addDestination(new DestinationComponent(0, 400));
        rail.addDestination(new DestinationComponent(0, 0));
        rail.addDestination(new DestinationComponent(400, 0));
        return rail;
    }
}
