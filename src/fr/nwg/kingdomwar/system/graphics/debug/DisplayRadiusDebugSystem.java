package fr.nwg.kingdomwar.system.graphics.debug;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.tower.PerceptionComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class DisplayRadiusDebugSystem extends EntityProcessingSystem{

    private ShapeRenderer shapeRenderer;
    @Mapper
    ComponentMapper<PerceptionComponent> perceptionComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    public DisplayRadiusDebugSystem(KingdomWarWorld world) {
        super(Aspect.getAspectForAll(PositionComponent.class, PerceptionComponent.class));
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

        shapeRenderer.setColor(Color.PINK);
        shapeRenderer.circle(x, y, perceptionComponentMapper.get(entity).radius);

    }

    @Override
    protected void end() {
        super.end();
        shapeRenderer.end();
    }
}
