package fr.nwg.kingdomwar.system.listener;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.listener.MouseMovedListenerComponent;

public class MouseMovedListenerSystem extends EntityProcessingSystem{
    private int x;
    private int y;

    public MouseMovedListenerSystem() {
        super(Aspect.getAspectForAll(MouseMovedListenerComponent.class));
    }

    @Override
    protected void process(Entity entity) {

    }

    public void process(int x, int y) {
        this.x = x;
        this.y = y;
        this.process();
    }
}
