package rougelike.game;

import com.badlogic.gdx.math.Vector2;
import rougelike.game.Enums.*;

public class Hero extends Entity {

    public Hero(Vector2 position) {
        entity_type = ENTITY_TYPE.HERO;
        height = 8;
        width = 8;
        pos.x = position.x;
        pos3.x = position.x;
        pos.y = position.y;
        pos3.y = position.y;
        texture = Media.hero;
        speed = 0.5f;
    }

    public void update(Control control) {
        dir_x = 0;
        dir_y = 0;

        if (control.down) dir_y = -1 ;
        if (control.up) dir_y = 1 ;
        if (control.left) dir_x = -1;
        if (control.right) dir_x = 1;

        pos.x += dir_x * speed;
        pos.y += dir_y * speed;

        pos3.x = pos.x;
        pos3.y = pos.y;
    }

    public float getCenterX() {
        return pos.x + (width/2);
    }
    public float getCenterY() {
        return pos.y + (height/2);
    }
}
