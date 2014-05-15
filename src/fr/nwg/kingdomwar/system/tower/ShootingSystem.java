package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.DelayedEntityProcessingSystem;
import fr.nwg.kingdomwar.component.tower.AimingComponent;
import fr.nwg.kingdomwar.component.tower.FiringRateComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class ShootingSystem extends DelayedEntityProcessingSystem{

    @Mapper
    ComponentMapper<FiringRateComponent> firingRateComponentMapper;
    @Mapper
    ComponentMapper<AimingComponent> aimingComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    public ShootingSystem() {
        super(Aspect.getAspectForAll(FiringRateComponent.class, AimingComponent.class));
    }

    @Override
    protected float getRemainingDelay(Entity entity) {
        return firingRateComponentMapper.get(entity).delay;
    }

    @Override
    protected void processDelta(Entity entity, float delta) {
        FiringRateComponent firingRateComponent = firingRateComponentMapper.get(entity);
        firingRateComponent.delay -= delta;
    }

    @Override
    protected void processExpired(Entity entity) {
        PositionComponent position = positionComponentMapper.getSafe(entity);
        AimingComponent aiming = aimingComponentMapper.get(entity);
        EntityFactory.createBullet((KingdomWarWorld) world, position, aiming);
        FiringRateComponent firingRateComponent = firingRateComponentMapper.get(entity);
        firingRateComponent.resetDelay();
        this.offerDelay(firingRateComponent.delay);
    }
}
