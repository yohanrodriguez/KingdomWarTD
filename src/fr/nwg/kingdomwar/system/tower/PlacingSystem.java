package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.input.TouchedUpComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class PlacingSystem extends EntityProcessingSystem {

    @Mapper
    private ComponentMapper<TouchedUpComponent> touchedUpComponentMapper;
    //Temp following cursor
    @Mapper
    private ComponentMapper<CursorPositionComponent> cursorPositionComponentMapper;

    public PlacingSystem() {
        super(Aspect.getAspectForAll(TouchedUpComponent.class, CursorPositionComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent touchedUpPosition = touchedUpComponentMapper.get(entity).position;
        PositionComponent cursorPosition = cursorPositionComponentMapper.get(entity).position;
        Entity tower = EntityFactory.createTowerEntity((KingdomWarWorld) world, touchedUpPosition.getVector3(), cursorPosition);
//        tower.changedInWorld();
    }
}
