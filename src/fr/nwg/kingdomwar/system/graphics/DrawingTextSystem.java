package fr.nwg.kingdomwar.system.graphics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.nwg.kingdomwar.component.misc.DrawingTextComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class DrawingTextSystem extends EntityProcessingSystem{

    BitmapFont font;
    SpriteBatch spriteBatch;

    @Mapper
    ComponentMapper<DrawingTextComponent> drawingTextComponentMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    public DrawingTextSystem() {
        super(Aspect.getAspectForAll(DrawingTextComponent.class, PositionComponent.class));
        spriteBatch = KingdomWarData.getInstance().getSpriteBatch();
        font = KingdomWarData.getInstance().font;
    }

    @Override
    protected void begin() {
        super.begin();
        spriteBatch.begin();
    }

    @Override
    protected void end() {
        super.end();
        spriteBatch.end();
    }

    @Override
    protected void process(Entity entity) {
        DrawingTextComponent drawingTextComponent = drawingTextComponentMapper.get(entity);
        PositionComponent position = positionComponentMapper.get(entity);
        font.setScale(1);
        font.setColor(drawingTextComponent.color);
        font.draw(spriteBatch, drawingTextComponent.textToDisplay, position.getRealPositionX(), position.getRealPositionY());
    }
}
