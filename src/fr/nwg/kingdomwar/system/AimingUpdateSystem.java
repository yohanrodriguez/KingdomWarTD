package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.AimingComponent;
import fr.nwg.kingdomwar.component.PositionComponent;
import fr.nwg.kingdomwar.input.InputManager;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class AimingUpdateSystem extends EntityProcessingSystem{

    private final OrthographicCamera camera;
    @Mapper
    private ComponentMapper<AimingComponent> aimingComponentMapper;

    private InputManager inputManager;

    public AimingUpdateSystem(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(AimingComponent.class));
        inputManager = kingdomWarWorld.getInputManager();
        camera = kingdomWarWorld.getCamera();
    }

    @Override
    protected void process(Entity entity) {
        AimingComponent aimingComponent = aimingComponentMapper.get(entity);
        Vector3 cursorPosition = inputManager.getCursorPosition();
        camera.unproject(cursorPosition);
        aimingComponent.position.x = cursorPosition.x;
        aimingComponent.position.y = cursorPosition.y;
    }
}
