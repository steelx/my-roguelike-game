package rougelike.game.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import rougelike.game.Control;
import rougelike.game.Enums.*;
import rougelike.game.Media;
import rougelike.game.box2d.Box2DHelper;
import rougelike.game.box2d.Box2DWorld;

public class Hero extends Entity {

    public Hero(Vector3 position, Box2DWorld box2DWorld) {
        entity_type = ENTITY_TYPE.HERO;
        height = 8;
        width = 8;
        texture = Media.hero;
        speed = 7.7f;
        pos.set(position);
        body = Box2DHelper.createBody(
            box2DWorld.world,
            width/2, height/2,
            0, height/4, pos,
            BodyDef.BodyType.DynamicBody
        );
    }

    public void reset(Vector3 position, Box2DWorld box2d) {
        pos.set(position);
        body = Box2DHelper.createBody(
            box2d.world,
            width/2, height/2,
            width/4, 0, pos,
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

        pos.x = body.getPosition().x - width/2;
        pos.y = body.getPosition().y - height/2;
    }

    public float getCenterX() {
        return pos.x + (width/2);
    }
    public float getCenterY() {
        return pos.y + (height/2);
    }
}
