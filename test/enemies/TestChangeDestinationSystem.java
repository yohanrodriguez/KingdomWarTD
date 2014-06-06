package enemies;

import com.artemis.Entity;
import com.artemis.World;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
import fr.nwg.kingdomwar.component.RailComponent;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.factory.RailFactory;
import fr.nwg.kingdomwar.non_artemis.Rail;
import fr.nwg.kingdomwar.system.misc.DestinationReachedSystem;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestChangeDestinationSystem {

    private World world;
    private DestinationReachedSystem destinationReachedSystem;
    private Entity entity;
    private Rail rail;
    private RailComponent railComponent;

    @Before
    public void setUp() {
        world = new World();
        world.initialize();
        entity = world.createEntity();
        rail = RailFactory.getSimpleRail();
        railComponent = new RailComponent(rail);
        entity.addComponent(new DestinationReachedComponent());
        entity.addComponent(railComponent);
        entity.addToWorld();

        destinationReachedSystem = new DestinationReachedSystem();
        world.setSystem(destinationReachedSystem);
    }

    @Test
    public void railGivesTheFirstDestinationWhenNoDestination() {

        DestinationComponent destination1 = addDestinationToRail(rail);
        world.process();

        assertEquals(destination1, entity.getComponent(DestinationComponent.class));
    }

    @Test
    public void railGiveTheNextDestinationWhenPreviousReached() {

        DestinationComponent destination2 = addDestinationToRail(rail);

        entity.addComponent(railComponent.getNextDestination());
        entity.changedInWorld();

        world.process();
        assertEquals(destination2, entity.getComponent(DestinationComponent.class));
    }

    private DestinationComponent addDestinationToRail(Rail rail) {
        DestinationComponent destinationComponent = new DestinationComponent(0, 0);
        rail.addDestination(destinationComponent);
        return destinationComponent;
    }
}