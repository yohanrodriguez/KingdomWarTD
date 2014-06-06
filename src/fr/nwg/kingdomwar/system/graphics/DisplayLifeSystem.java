package fr.nwg.kingdomwar.system.graphics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.nwg.kingdomwar.component.foes.LifeComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

/**
 * Created by eptwalabha on 30/05/2014.
 */
public class DisplayLifeSystem extends EntityProcessingSystem {

    ShapeRenderer shapeRenderer;
    @Mapper
    public ComponentMapper<LifeComponent> lifeComponentMapper;
    @Mapper
    public ComponentMapper<PositionComponent> positionComponentMapper;

    public DisplayLifeSystem() {
        super(Aspect.getAspectForAll(LifeComponent.class, PositionComponent.class));
        this.shapeRenderer = KingdomWarData.getInstance().getShapeRenderer();
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent position = positionComponentMapper.get(entity);
        LifeComponent life = lifeComponentMapper.get(entity);
        float lifePercentage = life.life / life.maxLife;
        if (lifePercentage < 0)
            lifePercentage = 0;
        if (lifePercentage > 1)
            lifePercentage = 1;

        float size = 80f;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.rect(position.getRealPositionX(), position.getRealPositionY() + 70, size + 4, 14);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(position.getRealPositionX() + 2, position.getRealPositionY() + 72, size, 10);
        if (lifePercentage > 0) {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(position.getRealPositionX() + 2, position.getRealPositionY() + 72, size * lifePercentage, 10);
        }
        shapeRenderer.end();
    }
}
