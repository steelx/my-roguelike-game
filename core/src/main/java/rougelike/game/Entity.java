package rougelike.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Entity{
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
