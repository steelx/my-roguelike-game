package rougelike.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import org.jetbrains.annotations.NotNull;
import rougelike.game.Enums.*;

public class Entity{
    public ENTITY_TYPE entity_type;
    public float speed;
    public Vector2 pos;
    public Vector3 pos3;
    public Texture texture;
    public float width;
    public float height;

    float dir_x = 0;
    float dir_y = 0;

    public Entity() {
        this.pos = new Vector2();
        this.pos3 = new Vector3();
    }

    public void draw(@NotNull SpriteBatch batch) {
        batch.draw(texture, pos.x, pos.y, width, height);
    }
}
