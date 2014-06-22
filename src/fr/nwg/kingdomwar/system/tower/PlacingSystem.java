package fr.nwg.kingdomwar.system.tower;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.input.CursorPositionComponent;
import fr.nwg.kingdomwar.component.input.TouchedUpComponent;
import fr.nwg.kingdomwar.component.misc.CostComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.factory.EntityFactory;
import fr.nwg.kingdomwar.factory.ItemMenuFactory;
import fr.nwg.kingdomwar.non_artemis.TowerType;
import fr.nwg.kingdomwar.world.KingdomWarData;
import fr.nwg.kingdomwar.non_artemis.Grid;

public class PlacingSystem extends EntityProcessingSystem {

    private final Grid grid;
    @Mapper
    private ComponentMapper<TouchedUpComponent> touchedUpComponentMapper;
    @Mapper
    private ComponentMapper<CostComponent> costComponenttMapper;
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

        int cellWidth = grid.getCellSize().width;
        int cellHeight = grid.getCellSize().height;
        int x = column * cellWidth + cellWidth / 2;
        int y = row * cellHeight + cellHeight / 2;
        Vector3 victor = new Vector3(x, y, 0);

        TowerType towerType = KingdomWarData.getInstance().getSelectedTowerType();

        if (KingdomWarData.getInstance().canAfford(towerType.getBuildingCost()) &&
            grid.isSpaceAvailable(column, row) &&
            grid.isInside(column, row) &&
            grid.getTopoAt(column, row) == 1) {

            Entity newTower = EntityFactory.createTowerEntity(world, victor, cursorPosition);
            PositionComponent towerPosition = (PositionComponent) newTower.getComponent(ComponentType.getTypeFor(PositionComponent.class));
            grid.addEntityAt(newTower, column, row);
            KingdomWarData.getInstance().playerMoney -= towerType.getBuildingCost();
            ItemMenuFactory.createNegativePointIndicator(world, towerPosition, "-" + towerType.getBuildingCost() + "$");
        }
    }
}
