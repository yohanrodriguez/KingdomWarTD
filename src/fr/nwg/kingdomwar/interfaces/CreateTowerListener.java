package fr.nwg.kingdomwar.interfaces;

import com.artemis.Entity;
import fr.nwg.kingdomwar.listener.Listener;

public class CreateTowerListener implements Listener{

    @Override
    public void run(Entity entity) {
        System.out.println("RUN");
    }
}
