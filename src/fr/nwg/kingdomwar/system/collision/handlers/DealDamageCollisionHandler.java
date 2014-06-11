package fr.nwg.kingdomwar.system.collision.handlers;

import com.artemis.Component;
import com.artemis.ComponentType;
import com.artemis.Entity;
import fr.nwg.kingdomwar.component.BulletPowerComponent;
import fr.nwg.kingdomwar.component.foes.DamageComponent;
import fr.nwg.kingdomwar.component.misc.DeadEntityComponent;
import fr.nwg.kingdomwar.system.collision.CollisionHandler;

/**
 * User: Eptwalabha
 * Date: 05/06/2014
 * Time: 23:44
 */
public class DealDamageCollisionHandler extends CollisionHandler {
    @Override
    public void collide(Entity bullet, Entity foe) {

        bullet.addComponent(new DeadEntityComponent());
        bullet.changedInWorld();

//        BulletPowerComponent bulletPowerComponent = bullet.getComponent(BulletPowerComponent.class);
        Component bulletPowerComponent = bullet.getComponent(ComponentType.getTypeFor(BulletPowerComponent.class));
        foe.addComponent(new DamageComponent(((BulletPowerComponent) bulletPowerComponent).power));

        foe.changedInWorld();
    }
}
