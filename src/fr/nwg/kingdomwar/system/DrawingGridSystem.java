package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.ColumnsComponent;
import fr.nwg.kingdomwar.component.RowsComponent;

public class DrawingGridSystem extends EntityProcessingSystem {

    @Mapper
    private ComponentMapper<RowsComponent> rowsComponentMapper;
    @Mapper
    private ComponentMapper<ColumnsComponent> columnsComponentMapper;

    public DrawingGridSystem() {
        super(Aspect.getAspectForAll(RowsComponent.class, ColumnsComponent.class));
    }

    @Override
    protected void process(Entity entity) {

    }
}
