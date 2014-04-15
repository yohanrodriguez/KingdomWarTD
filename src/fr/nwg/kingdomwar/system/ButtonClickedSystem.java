package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.ButtonComponent;
import fr.nwg.kingdomwar.component.ListenerComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.component.SizeComponent;
import fr.nwg.kingdomwar.input.InputManager;
import fr.nwg.kingdomwar.listener.Listener;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class ButtonClickedSystem extends EntityProcessingSystem {
    private final OrthographicCamera camera;

    @Mapper
    private ComponentMapper<PositionComponent> positionComponentMapper;
    @Mapper
    private ComponentMapper<SizeComponent> sizeComponentMapper;
    @Mapper
    private ComponentMapper<ListenerComponent> listenerComponentMapper;

    public ButtonClickedSystem(KingdomWarWorld kingdomWarWorld) {
        //super(Aspect.getAspectForAll(ButtonComponent.class, ButttonListenerComponent.class, PositionComponent.class, SizeComponent.class));
        super(Aspect.getEmpty()); //tmp
        camera = kingdomWarWorld.getCamera();
    }

    @Override
    protected void process(Entity entity) {
        /*if(inputManager.isTouched()) {
            Vector3 cursor = inputManager.getCursorPosition();
            camera.unproject(cursor);
            PositionComponent position = positionComponentMapper.get(entity);
            SizeComponent size = sizeComponentMapper.get(entity);
            if(isInside(cursor, position, size)) {
                ListenerComponent listener = listenerComponentMapper.get(entity);
                listener.touched(entity);
            }
        } else if(inputManager.isTouchedUp()) {
            if(isInside(cursor, position, size))
                listener.touchedUp(entity);
        }*/
    }

    private boolean isInside(Vector3 cursor, PositionComponent position, SizeComponent size) {
        return cursor.x > position.x && cursor.x < position.x + size.width
                && cursor.y > position.y && cursor.y < position.y + size.height;
    }
}
