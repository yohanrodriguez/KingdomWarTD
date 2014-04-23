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
import fr.nwg.kingdomwar.listener.system.MouseMovedSystemListener;
import fr.nwg.kingdomwar.world.KingdomWarWorld;

public class UpdatePositionFromCursorPosition extends EntityProcessingSystem implements MouseMovedSystemListener {

    @Mapper
    ComponentMapper<PositionComponent> positionComponentMapper;

    private OrthographicCamera camera;
    private int x;
    private int y;

    public UpdatePositionFromCursorPosition(KingdomWarWorld kingdomWarWorld) {
        super(Aspect.getAspectForAll(PositionComponent.class, CursorListenerComponent.class));
        camera = kingdomWarWorld.getCamera();
    }

    @Override
    protected void process(Entity entity) {
        PositionComponent position = positionComponentMapper.get(entity);
        Vector3 cursorPosition = new Vector3(x, y, 0);
        camera.unproject(cursorPosition);
        position.x = cursorPosition.x;
        position.y = cursorPosition.y;
    }

    @Override
    public void mouseMoved(int i, int i2) {
        this.x = i;
        this.y = i2;
        this.process();
    }
}
