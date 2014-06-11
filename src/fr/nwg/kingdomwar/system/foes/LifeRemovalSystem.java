package fr.nwg.kingdomwar.system.foes;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.foes.DamageComponent;
import fr.nwg.kingdomwar.component.foes.LifeComponent;
import fr.nwg.kingdomwar.component.misc.DeadEntityComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

/**
 * Created by eptwalabha on 30/05/2014.
 */
public class LifeRemovalSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<LifeComponent> lifeComponentMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageComponentMapper;

    public LifeRemovalSystem() {
        super(Aspect.getAspectForAll(DamageComponent.class, LifeComponent.class));
    }

    @Override
    protected void process(Entity entity) {

        LifeComponent life = lifeComponentMapper.get(entity);
        life.life -= damageComponentMapper.get(entity).damage;

        if (life.life <= 0) {
            entity.addComponent(new DeadEntityComponent());
        }

        entity.removeComponent(DamageComponent.class);
        entity.changedInWorld();
    }
}
