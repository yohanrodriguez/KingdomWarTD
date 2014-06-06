package fr.nwg.kingdomwar.factory;

import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.non_artemis.Rail;

public class RailFactory {

    public static Rail getSimpleRail() {
        Rail rail = new Rail();
        rail.addDestination(new DestinationComponent(0, 0));
        rail.addDestination(new DestinationComponent(400, 400));
        rail.addDestination(new DestinationComponent(50, 500));
        rail.addDestination(new DestinationComponent(20, 100));
        rail.addDestination(new DestinationComponent(400, 50));

        KingdomWarData.getInstance().addRail(Constants.Rails.GROUND_001, rail);

        return rail;
    }
}
