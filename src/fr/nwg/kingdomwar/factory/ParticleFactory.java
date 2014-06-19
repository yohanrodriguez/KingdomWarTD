package fr.nwg.kingdomwar.factory;

import com.artemis.Entity;
import com.artemis.World;
import fr.nwg.kingdomwar.Constants;
import fr.nwg.kingdomwar.component.collision.CircleCollisionComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingComponent;
import fr.nwg.kingdomwar.component.graphics.DrawingSpriteComponent;
import fr.nwg.kingdomwar.component.misc.TimeToLiveComponent;
import fr.nwg.kingdomwar.component.physic.PositionComponent;
import fr.nwg.kingdomwar.component.physic.SpeedComponent;
import fr.nwg.kingdomwar.component.physic.VelocityComponent;
import fr.nwg.kingdomwar.world.KingdomWarData;

public class ParticleFactory {

    public static void createExplosionOfParticles(World world, PositionComponent position, int numberOfParticle, float speed) {

        for (int i = 0; i < numberOfParticle; i++) {

            Entity particle = world.createEntity();
            PositionComponent positionParticle = new PositionComponent(position, 0, 0);
            particle.addComponent(positionParticle);
            float velocityX = (float) (Math.random() * 2 - 1);
            float velocityY = (float) (Math.random() * 2 - 1);
            particle.addComponent(new VelocityComponent(velocityX, velocityY));
            particle.addComponent(new SpeedComponent((float) (Math.random() * speed)));

            particle.addComponent(new DrawingSpriteComponent(KingdomWarData.getInstance().getSprite(Constants.Textures.MONSTER_002), positionParticle, (float) (Math.random() * 20), (float) (Math.random() * 20)));
            particle.addComponent(new DrawingComponent(255, 255, 255, 1));

            particle.addComponent(new TimeToLiveComponent(200 + (int) (Math.random() * 300)));
            particle.addToWorld();
        }

    }
}
