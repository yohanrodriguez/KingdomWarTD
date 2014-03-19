package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.component.DrawingComponent;
import fr.nwg.kingdomwar.component.RectangleComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class DrawingShapeSystem extends EntityProcessingSystem{

    ShapeRenderer shapeRenderer;
    @Mapper
    ComponentMapper<RectangleComponent> rectangleComponentMapper;
    @Mapper
    ComponentMapper<DrawingComponent> drawingComponentMapper;

    public DrawingShapeSystem(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(DrawingComponent.class, RectangleComponent.class));
        shapeRenderer = kingdomWarWorld.getShapeRenderer();
    }

    @Override
    protected void begin() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    @Override
    protected void process(Entity entity) {
        RectangleComponent rectangle = rectangleComponentMapper.get(entity);
        DrawingComponent drawingComponent = drawingComponentMapper.get(entity);

        if (drawingComponent != null)
            shapeRenderer.setColor(drawingComponent.color);

        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    protected void end() {
        shapeRenderer.end();
    }
}
