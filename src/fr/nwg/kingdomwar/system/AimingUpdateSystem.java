package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import fr.nwg.kingdomwar.component.AimingComponent;
import fr.nwg.kingdomwar.listener.system.MouseMovedSystemListener;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class AimingUpdateSystem extends EntityProcessingSystem implements MouseMovedSystemListener{

    private final OrthographicCamera camera;
    @Mapper
    private ComponentMapper<AimingComponent> aimingComponentMapper;
    private int x;
    private int y;

    public AimingUpdateSystem(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(AimingComponent.class));
        camera = kingdomWarWorld.getCamera();
    }

    @Override
    protected void process(Entity entity) {
        AimingComponent aimingComponent = aimingComponentMapper.get(entity);
        Vector3 cursorPosition = new Vector3(x, y, 0);
        camera.unproject(cursorPosition);
        aimingComponent.position.x = cursorPosition.x;
        aimingComponent.position.y = cursorPosition.y;
    }

    @Override
    public void mouseMoved(int i, int i2) {
        x = i;
        y = i2;
        process();
    }
}
