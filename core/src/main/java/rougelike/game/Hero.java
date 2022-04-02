package rougelike.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import rougelike.game.Enums.*;
import rougelike.game.box2d.Box2DHelper;
import rougelike.game.box2d.Box2DWorld;

public class Hero extends Entity {
    Body body;

    public Hero(Vector2 position, Box2DWorld box2DWorld) {
        entity_type = ENTITY_TYPE.HERO;
        height = 8;
        width = 8;
        pos.x = position.x;
        pos3.x = position.x;
        pos.y = position.y;
        pos3.y = position.y;
        texture = Media.hero;
        speed = 6f;
        body = Box2DHelper.createBody(
                box2DWorld.world,
                width, height/2,
                pos3,
                BodyDef.BodyType.DynamicBody
        );
    }

    public void update(Control control) {
        dir_x = 0;
        dir_y = 0;

        if (control.down) dir_y = -1 ;
        if (control.up) dir_y = 1 ;
        if (control.left) dir_x = -1;
        if (control.right) dir_x = 1;

        body.setLinearVelocity(dir_x*speed, dir_y*speed);

        Vector2 p = body.getPosition();
        pos.x = p.x - width/2;
        pos.y = p.y - height/2;

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
