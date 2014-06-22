package fr.nwg.kingdomwar.system.foes;

import com.artemis.systems.VoidEntitySystem;
import fr.nwg.kingdomwar.factory.EnemyFactory;

public class SpawnFoeSystem extends VoidEntitySystem {

    private float delay;
    private float initialDelay = 3000;

    public SpawnFoeSystem() {
        super();
        this.delay = initialDelay;
    }

    @Override
    protected void processSystem() {
        delay -= world.getDelta();
        if(delay <= 0) {
            if (Math.random() < 0.2)
                EnemyFactory.createToughEnemy(world);
            EnemyFactory.createBasicEnemy(world);
            delay = initialDelay;
        }
    }
}