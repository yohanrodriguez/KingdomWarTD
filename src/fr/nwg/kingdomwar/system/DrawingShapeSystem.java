package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.component.DrawingComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.component.SizeComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class DrawingShapeSystem extends EntityProcessingSystem{

    ShapeRenderer shapeRenderer;
    @Mapper
    ComponentMapper<SizeComponent> rectangleComponentMapper;
    @Mapper
    ComponentMapper<DrawingComponent> drawingComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    public DrawingShapeSystem(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(DrawingComponent.class, SizeComponent.class, PositionComponent.class));
        shapeRenderer = kingdomWarWorld.getShapeRenderer();
    }

    @Override
    protected void process(Entity entity) {
        SizeComponent rectangle = rectangleComponentMapper.get(entity);
        DrawingComponent drawingComponent = drawingComponentMapper.get(entity);
        PositionComponent position = positionComponentMapper.get(entity);

        if (drawingComponent != null) {
            shapeRenderer.setColor(drawingComponent.color);
            if(drawingComponent.shapeType != null)
                shapeRenderer.begin(drawingComponent.shapeType);
            else
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        }

        shapeRenderer.rect(position.getRealPositionX(), position.getRealPositionY(), rectangle.width, rectangle.height);
        shapeRenderer.end();
    }

    @Override
    protected void end() {

    }
}
