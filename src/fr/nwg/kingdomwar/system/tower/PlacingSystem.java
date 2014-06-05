package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.input.TouchedUpComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.non_artemis.Grid;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class PlacingSystem extends EntityProcessingSystem {

    private final Grid grid;
    @Mapper
    private ComponentMapper<TouchedUpComponent> touchedUpComponentMapper;
    //Temp following cursor
    @Mapper
    private ComponentMapper<CursorPositionComponent> cursorPositionComponentMapper;

    public PlacingSystem(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(TouchedUpComponent.class, CursorPositionComponent.class));
        grid = kingdomWarWorld.getGrid();
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent touchedUpPosition = touchedUpComponentMapper.get(entity).position;
        PositionComponent cursorPosition = cursorPositionComponentMapper.get(entity).position;
        int row = grid.getRowFromPosition(touchedUpPosition.getRealPositionY());
        int column = grid.getColumnFromPosition(touchedUpPosition.getRealPositionX());

        if (grid.getTopoAt(column, row) == 1) {
            int x = column * grid.getCellSize().width;
            int y = row * grid.getCellSize().height;
            Vector3 victor = new Vector3(x, y, 0);
            EntityFactory.createTowerEntity((KingdomWarWorld) world, victor, cursorPosition);
        }
    }
}
