package enemies;

import com.artemis.Entity;
import com.artemis.World;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.listener.MovingEntityListener;
import fr.nwg.kingdomwar.system.misc.MovingToDestinationSystem;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by eptwalabha on 25/05/2014.
 */
public class TestMovingEntitySystem {

    private World world;
    private Entity entity;
    private MovingToDestinationSystem movingSystem;
    private PositionComponent position;
    private DestinationComponent destination;
    private SpeedComponent speed;

    @Before
    public void setUp() {
        world = new World();
        world.initialize();
        movingSystem = new MovingToDestinationSystem();
        world.setSystem(movingSystem);
        entity = generateEntity();

        position = new PositionComponent();
        destination = new DestinationComponent(100f, 100f);
        speed = new SpeedComponent(2f);

        entity.addComponent(position);
        entity.addComponent(destination);
        entity.addComponent(speed);

        entity.addToWorld();
    }

    @Test
    public void testAnEntityIsProcessedWhenItHasAPositionADestinationAndASpeed() {

        world.process();
        assertThat(movingSystem.getActives().size(), is(1));
    }

    @Test
    public void testAnEntityMoves() {

        assertThat(position.x, is(0f));
        assertThat(position.y, is(0f));

        PositionComponent oldPosition = new PositionComponent(position.x, position.y);
        float deltaX = destination.x - position.x;
        float deltaY = destination.y - position.y;

        // 1 seconde
        world.setDelta(1000f);
        world.process();

        float realSpeed = speed.speed * world.getDelta() / 1000;
        float ratio = (realSpeed * realSpeed) / (deltaX * deltaX + deltaY * deltaY);

        assertThat(position.x, is(oldPosition.x + deltaX * ratio));
        assertThat(position.y, is(oldPosition.y + deltaY * ratio));
    }

    @Test
    public void canEntityStopMovingWhenItReachesItsDestination() {

        position.x = 75;
        position.y = 75;
        speed.speed = 50;

        world.setDelta(1000f);
        world.process();

        assertThat(position.x, is(destination.x));
        assertThat(position.x, is(destination.y));
        assertNotNull(entity.getComponent(DestinationReachedComponent.class));
    }

    private Entity generateEntity() {
        return world.createEntity();
    }

    private class MovingEntityListenerMock implements MovingEntityListener {

        public int counter = 0;
        public Entity entity = null;

        @Override
        public void entityHasReachDestination(Entity entity) {
            this.entity = entity;
            counter++;
        }
    }
}
