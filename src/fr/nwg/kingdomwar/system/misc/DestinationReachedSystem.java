package fr.nwg.kingdomwar.system.misc;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.DestinationReachedComponent;
import fr.nwg.kingdomwar.component.RailComponent;
import fr.nwg.kingdomwar.component.foes.PenaltyWhenWinComponent;
import fr.nwg.kingdomwar.component.misc.DeadEntityComponent;
import fr.nwg.kingdomwar.component.misc.DestinationComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class DestinationReachedSystem extends EntityProcessingSystem {

    @Mapper
    private ComponentMapper<RailComponent> railComponentMapper;
    @Mapper
    private ComponentMapper<PenaltyWhenWinComponent> penaltyWhenWinComponentMapper;

    public DestinationReachedSystem() {
        super(Aspect.getAspectForAll(DestinationReachedComponent.class, RailComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        RailComponent rail = railComponentMapper.get(entity);
        DestinationComponent nextDestination = rail.getNextDestination();
        if (nextDestination != null) {
            entity.addComponent(nextDestination);
            rail.moveIndexForward();
        } else {
            PenaltyWhenWinComponent penaltyWhenWinComponent = penaltyWhenWinComponentMapper.getSafe(entity);
            if(penaltyWhenWinComponent != null) {
                KingdomWarData.getInstance().playerLifePoints -= penaltyWhenWinComponent.lifePoints;
                if(KingdomWarData.getInstance().playerLifePoints <= 0) {
                    KingdomWarData.getInstance().playerLifePoints = 0;
                    KingdomWarData.getInstance().timeScale = 0;
                }
                entity.addComponent(new DeadEntityComponent());
            }
        }
        entity.removeComponent(DestinationReachedComponent.class);
        entity.changedInWorld();
    }
}
