package fr.nwg.kingdomwar.system.graphics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingTypeComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.graphics.SizeComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class DrawingShapeSystem extends EntityProcessingSystem{

    ShapeRenderer shapeRenderer;
    @Mapper
    ComponentMapper<SizeComponent> sizeComponentMapper;
    @Mapper
    ComponentMapper<DrawingComponent> drawingComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;
    @Mapper
    ComponentMapper<DrawingTypeComponent> drawingTypeComponentMapper;

    public DrawingShapeSystem() {
        super(Aspect.getAspectForAll(DrawingComponent.class, SizeComponent.class, PositionComponent.class));
        shapeRenderer = KingdomWarData.getInstance().getShapeRenderer();
    }

    @Override
    protected void process(Entity entity) {
        SizeComponent size = sizeComponentMapper.get(entity);
        DrawingComponent drawingComponent = drawingComponentMapper.get(entity);
        PositionComponent position = positionComponentMapper.get(entity);
        DrawingTypeComponent drawingType = drawingTypeComponentMapper.getSafe(entity);

        shapeRenderer.setColor(drawingComponent.color);
        if(drawingComponent.shapeType != null)
            shapeRenderer.begin(drawingComponent.shapeType);
        else
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if(drawingType == null || drawingType.drawingType == DrawingTypeComponent.DrawingType.RECT)
            shapeRenderer.rect(position.getRealPositionX(), position.getRealPositionY(), size.width, size.height);
        else if(drawingType.drawingType == DrawingTypeComponent.DrawingType.ELLIPSE)
            shapeRenderer.circle(position.getRealPositionX(), position.getRealPositionY(), size.width, size.height);
        shapeRenderer.end();
    }
}
