package fr.nwg.kingdomwar.system.test;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.IntervalEntityProcessingSystem;
import fr.nwg.kingdomwar.component.foes.DamageComponent;
import fr.nwg.kingdomwar.component.foes.LifeComponent;

/**
 * Created by eptwalabha on 30/05/2014.
 */
public class DealRandomDamageEveryHalfSecondSystem extends IntervalEntityProcessingSystem {

    public DealRandomDamageEveryHalfSecondSystem() {
        super(Aspect.getAspectForAll(LifeComponent.class), 500);
    }

    @Override
    protected void process(Entity entity) {
        entity.addComponent(new DamageComponent((float) (Math.random() * 10)));
        entity.changedInWorld();
    }
}
