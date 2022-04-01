package rougelike.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import rougelike.game.Enums.*;

public class Entity{
    public Enums.ENTITY_TYPE entity_type;
    public float speed;
    public Vector2 pos;
    public Texture texture;
    public float width;
    public float height;

    public Entity() {
        this.pos = new Vector2();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, width, height);
    }
}
