package rougelike.game;

import com.badlogic.gdx.math.Vector2;
import rougelike.game.Enums.*;

public class Hero extends Entity {

    public Hero(Vector2 pos) {
        entity_type = ENTITY_TYPE.HERO;
        height = 8;
        width = 8;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        texture = Media.hero;
        speed = 1;
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
    }
}
