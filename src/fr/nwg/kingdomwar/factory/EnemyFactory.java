package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import fr.nwg.kingdomwar.component.DestinationComponent;
import fr.nwg.kingdomwar.component.LifeComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.component.SpeedComponent;

public class EnemyFactory extends EntityFactory {

    public static Entity createBasicEnemy(World world) {
        Entity basicEnemy = world.createEntity();
        basicEnemy.addComponent(new LifeComponent(100));
        basicEnemy.addComponent(new PositionComponent());
        basicEnemy.addComponent(new DestinationComponent(new PositionComponent(42,42)));
        basicEnemy.addComponent(new SpeedComponent(42));
        return basicEnemy;
    }

}
