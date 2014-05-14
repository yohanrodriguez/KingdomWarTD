package fr.nwg.kingdomwar.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import fr.nwg.kingdomwar.component.ColumnsComponent;
import fr.nwg.kingdomwar.component.RowsComponent;
import fr.nwg.kingdomwar.listener.AreaClickListenerComponent;
import fr.nwg.kingdomwar.listener.system.TouchedUpSystemListener;

public class GridClickSytem extends EntityProcessingSystem implements TouchedUpSystemListener {
    @Mapper
    private ComponentMapper<AreaClickListenerComponent> areaClickListenerComponentMapper;
    private int y;
    private int x;

    public GridClickSytem() {
        super(Aspect.getAspectForAll(AreaClickListenerComponent.class, RowsComponent.class, ColumnsComponent.class));
    }

    @Override
    protected void process(Entity entity) {
        AreaClickListenerComponent areaClickListenerComponent = areaClickListenerComponentMapper.get(entity);
        System.out.println("size = " + areaClickListenerComponent.size.width + "; " + areaClickListenerComponent.size.height + " position " + areaClickListenerComponent.position.x + "; " + areaClickListenerComponent.position.y);
        if (areaClickListenerComponent.isClicked(x, y)) {
            System.out.println("bla bla bla");
        }
    }

    @Override
    public void touchUp(int i, int i2, int i3, int i4) {
        this.x = i;
        this.y = i2;
        this.process();
        System.out.println(i);
    }

//    @Override
//    protected boolean checkProcessing() {
//        return true;
//    }
}
