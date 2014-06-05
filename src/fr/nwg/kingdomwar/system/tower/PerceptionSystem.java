package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.tower.AimingComponent;
import fr.nwg.kingdomwar.component.tower.PerceptionComponent;

public class PerceptionSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PerceptionComponent> perceptionComponentMapper;

    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    private ImmutableBag<Entity> foes;

    public PerceptionSystem() {
        super(Aspect.getAspectForAll(PerceptionComponent.class, PositionComponent.class));
    }

    @Override
    protected void begin() {
        // on met à jour la liste des ennemis
        foes = world.getManager(GroupManager.class).getEntities(Constants.Groups.FOES);
    }

    @Override
    protected void process(Entity entity) {

        PerceptionComponent perception = perceptionComponentMapper.get(entity);
        PositionComponent position = positionComponentMapper.get(entity);
        entity.removeComponent(ComponentType.getTypeFor(AimingComponent.class));
        for(Entity foe : foes){
            PositionComponent foePosition = foe.getComponent(PositionComponent.class);
            float deltaX = foePosition.getRealPositionX() - position.getRealPositionX();
            float deltaY = foePosition.getRealPositionY() - position.getRealPositionY();
            if(deltaX * deltaX + deltaY * deltaY < perception.radius * perception.radius) {
                entity.addComponent(new AimingComponent(foePosition));
            }
        }
        entity.changedInWorld();
    }
}
