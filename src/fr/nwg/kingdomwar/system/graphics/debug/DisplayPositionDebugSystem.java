package fr.nwg.kingdomwar.system.graphics.debug;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class DisplayPositionDebugSystem extends EntityProcessingSystem {

    private ShapeRenderer shapeRenderer;

    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    public DisplayPositionDebugSystem(KingdomWarWorld world) {
        super(Aspect.getAspectForAll(PositionComponent.class));
        shapeRenderer = world.getShapeRenderer();
    }

    @Override
    protected void begin() {
        super.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    }

    @Override
    protected void process(Entity entity) {

        PositionComponent position = positionComponentMapper.get(entity);
        float x = position.getRealPositionX();
        float y = position.getRealPositionY();
        float jp = 20;

        shapeRenderer.setColor(Color.PINK);
        shapeRenderer.line(x - jp, y, x + jp, y);
        shapeRenderer.line(x, y - jp, x, y + jp);


    }

    @Override
    protected void end() {
        super.end();
        shapeRenderer.end();
    }
}
