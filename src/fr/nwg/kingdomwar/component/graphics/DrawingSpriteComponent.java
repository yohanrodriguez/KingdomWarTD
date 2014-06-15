package fr.nwg.kingdomwar.component.graphics;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import fr.nwg.kingdomwar.component.physic.PositionComponent;

public class DrawingSpriteComponent extends Component {

    public Sprite sprite;
    public PositionComponent position;
    public float width;
    public float height;

    public DrawingSpriteComponent(Sprite sprite, PositionComponent position) {
        this(sprite, position, sprite.getWidth(), sprite.getHeight());
    }

    public DrawingSpriteComponent(Sprite sprite, PositionComponent position, float width, float height) {
        this.sprite = sprite;
        this.sprite.setScale(width / this.sprite.getWidth(), height / this.sprite.getHeight());
        this.position = position;
        this.width = width;
        this.height = height;
    }
}
