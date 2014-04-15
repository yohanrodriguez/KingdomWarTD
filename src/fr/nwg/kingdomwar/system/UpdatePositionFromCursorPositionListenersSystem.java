package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.CursorListenerComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class UpdatePositionFromCursorPositionListenersSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    private OrthographicCamera camera;

    public UpdatePositionFromCursorPositionListenersSystem(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(PositionComponent.class, CursorListenerComponent.class));
        inputManager = kingdomWarWorld.getInputManager();
        camera = kingdomWarWorld.getCamera();
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent position = positionComponentMapper.get(entity);
        Vector3 cursorPosition = inputManager.getCursorPosition();
        camera.unproject(cursorPosition);
        position.x = cursorPosition.x;
        position.y = cursorPosition.y;
    }
}
