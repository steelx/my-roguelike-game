package rougelike.game.box2d;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class Box2DHelper {
    public static Body createBody(World world, float width, float height, Vector3 pos, BodyDef.BodyType type) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x + width/2, pos.y + height/2);
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type;// Dynamic | Static | Kinematic
        body = world.createBody(bodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = boxShape;
        fixtureDef.restitution = 0.4f;// bounciness

        body.createFixture(fixtureDef);
        boxShape.dispose();

        return body;
    }
}
