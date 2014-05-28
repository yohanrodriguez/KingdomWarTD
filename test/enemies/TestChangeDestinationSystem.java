package enemies;

import com.artemis.Entity;
import com.artemis.World;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
import fr.nwg.kingdomwar.component.RailComponent;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.system.misc.DestinationReachedSystem;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestChangeDestinationSystem {

    private World world;
    private DestinationReachedSystem destinationReachedSystem;
    private Entity entity;
    private RailComponent rail;

    @Before
    public void setUp() {
        world = new World();
        world.initialize();
        entity = world.createEntity();
        rail = new RailComponent();
        entity.addComponent(new DestinationReachedComponent());
        entity.addComponent(rail);
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

        entity.addComponent(rail.getNextDestination());
        entity.changedInWorld();

        world.process();
        assertEquals(destination2, entity.getComponent(DestinationComponent.class));
    }

    private DestinationComponent addDestinationToRail(RailComponent rail) {
        DestinationComponent destinationComponent = new DestinationComponent(0, 0);
        rail.addDestination(destinationComponent);
        return destinationComponent;
    }
}