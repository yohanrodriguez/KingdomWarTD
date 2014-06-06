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
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.non_artemis.Grid;

public class PlacingSystem extends EntityProcessingSystem {

    private final Grid grid;
    @Mapper
    private ComponentMapper<TouchedUpComponent> touchedUpComponentMapper;
    //Temp following cursor
    @Mapper
    private ComponentMapper<CursorPositionComponent> cursorPositionComponentMapper;

    public PlacingSystem() {
        super(Aspect.getAspectForAll(TouchedUpComponent.class, CursorPositionComponent.class));
        grid = KingdomWarData.getInstance().getGrid();
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent touchedUpPosition = touchedUpComponentMapper.get(entity).position;
        PositionComponent cursorPosition = cursorPositionComponentMapper.get(entity).position;
        int row = grid.getRowFromPosition(touchedUpPosition.getRealPositionY());
        int column = grid.getColumnFromPosition(touchedUpPosition.getRealPositionX());

        if (row >= 0 && column >= 0 && row < grid.getRowsCount() && column < grid.getColumnsCount() && grid.getTopoAt(column, row) == 1) {
            int x = column * grid.getCellSize().width;
            int y = row * grid.getCellSize().height;
            Vector3 victor = new Vector3(x, y, 0);
            Entity newTower = EntityFactory.createTowerEntity(world, victor, cursorPosition);
            grid.addEntityAt(newTower, column, row);
        }
    }
}
