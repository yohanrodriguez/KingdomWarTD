package fr.nwg.kingdomwar.component.tower;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwaaa on 11/06/2014.
 */
public class TargetListComponent extends Component {
    public List<Entity> targetList;

    public TargetListComponent() {
        targetList = new ArrayList<Entity>();
    }
}
