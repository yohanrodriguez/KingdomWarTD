package fr.nwg.kingdomwar.system.graphics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.nwg.kingdomwar.component.graphics.DrawingSpriteComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

import javax.swing.text.Position;

public class DrawingSpriteSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<DrawingSpriteComponent> spriteComponent;
    @Mapper
    ComponentMapper<PositionComponent> positionComponent;

    private SpriteBatch spriteBatch;

    public DrawingSpriteSystem() {
        super(Aspect.getAspectForAll(DrawingSpriteComponent.class, PositionComponent.class));
        spriteBatch = KingdomWarData.getInstance().getSpriteBatch();
    }

    @Override
    protected void begin() {
        super.begin();
        spriteBatch.begin();
    }

    @Override
    protected void process(Entity entity) {

        DrawingSpriteComponent drawingSpriteComponent = spriteComponent.get(entity);
        PositionComponent position = positionComponent.get(entity);
        Sprite sprite = drawingSpriteComponent.sprite;
//        sprite.setScale(0.5f, 2f);
        sprite.setPosition(position.getRealPositionX() - sprite.getWidth() / 2, position.getRealPositionY() - sprite.getHeight() / 2);
        sprite.draw(spriteBatch);
    }

    @Override
    protected void end() {
        super.end();
        spriteBatch.end();
    }
}
