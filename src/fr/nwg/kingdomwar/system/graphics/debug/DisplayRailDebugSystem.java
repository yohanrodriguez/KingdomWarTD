package fr.nwg.kingdomwar.system.graphics.debug;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.non_artemis.Rail;

import java.util.List;

public class DisplayRailDebugSystem extends VoidEntitySystem {

    private ShapeRenderer shapeRenderer;
    private Rail rail;

    public DisplayRailDebugSystem() {
        shapeRenderer = KingdomWarData.getInstance().getShapeRenderer();
        rail = KingdomWarData.getInstance().getRail(Constants.Rails.GROUND_001);
    }

    @Override
    protected void processSystem() {

        List<DestinationComponent> destinations = rail.getAllDestinations();
        if (destinations == null || destinations.size() < 2)
            return;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        DestinationComponent oldDestination = destinations.get(0);
        for (DestinationComponent destination : destinations) {
            if (destination == oldDestination)
                continue;
            shapeRenderer.line(oldDestination.x, oldDestination.y, destination.x, destination.y);
            shapeRenderer.circle(destination.x, destination.y, 5f);
            oldDestination = destination;
        }
        shapeRenderer.end();

    }
}
