package rougelike.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import org.jetbrains.annotations.NotNull;
import rougelike.game.Enums.*;

public class Entity implements Comparable<Entity>{
    public ENTITY_TYPE entity_type;
    public float speed;
    public Vector3 pos;
    public Texture texture;
    public float width;
    public float height;
    public Body body;

    float dir_x = 0;
    float dir_y = 0;

    public Entity() {
        this.pos = new Vector3();
    }

    public void draw(@NotNull SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, width, height);
    }

    public int compareTo(Entity e) {
        return Float.compare(e.pos.y, pos.y);
    }
}
