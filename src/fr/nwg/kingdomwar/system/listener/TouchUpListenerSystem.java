package fr.nwg.kingdomwar.system.listener;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.listener.TouchUpListenerComponent;

public class TouchUpListenerSystem extends EntityProcessingSystem {

    private int i;
    private int i2;
    private int i3;
    private int i4;

    @Mapper
    private ComponentMapper<TouchUpListenerComponent> touchUpListenerComponentMapper;

    public TouchUpListenerSystem() {
        super(Aspect.getAspectForAll(TouchUpListenerComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        TouchUpListenerComponent touchUpListenerComponent = touchUpListenerComponentMapper.get(entity);
        touchUpListenerComponent.run();
    }

    public void process(int i, int i2, int i3, int i4) {
        this.i = i;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.process();
    }
}
