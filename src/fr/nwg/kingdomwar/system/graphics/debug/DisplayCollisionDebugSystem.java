package fr.nwg.kingdomwar.system.graphics.debug;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class DisplayCollisionDebugSystem extends EntityProcessingSystem{

    private ShapeRenderer shapeRenderer;
    @Mapper
    ComponentMapper<CircleCollisionComponent> circleCollisionComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    public DisplayCollisionDebugSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, CircleCollisionComponent.class));
        shapeRenderer = KingdomWarData.getInstance().getShapeRenderer();
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

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(x, y, circleCollisionComponentMapper.get(entity).radius);

    }

    @Override
    protected void end() {
        super.end();
        shapeRenderer.end();
    }
}
