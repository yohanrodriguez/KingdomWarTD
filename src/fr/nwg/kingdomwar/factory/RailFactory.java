package fr.nwg.kingdomwar.factory;

import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.non_artemis.Rail;

public class RailFactory {

    public static Rail getSimpleRail() {
        Rail rail = new Rail();
        addNodeToRail(rail, 0, 0);
        addNodeToRail(rail, 0, 1);
        addNodeToRail(rail, 1, 1);
        addNodeToRail(rail, 1, 8);
        addNodeToRail(rail, 8, 8);
        addNodeToRail(rail, 8, 5);
        addNodeToRail(rail, 5, 5);
        addNodeToRail(rail, 5, 6);
        addNodeToRail(rail, 3, 6);
        addNodeToRail(rail, 3, 1);
        addNodeToRail(rail, 5, 1);
        addNodeToRail(rail, 5, 3);
        addNodeToRail(rail, 7, 3);
        addNodeToRail(rail, 7, 0);

        KingdomWarData.getInstance().addRail(Constants.Rails.GROUND_001, rail);

        return rail;
    }

    public static void addNodeToRail(Rail rail, int x, int y) {
        int width = KingdomWarData.getInstance().getGrid().getCellSize().width;
        int height = KingdomWarData.getInstance().getGrid().getCellSize().height;
        rail.addDestination(new DestinationComponent(x * width + width / 2, y * height + height / 2));
    }
}
