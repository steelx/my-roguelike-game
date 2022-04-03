package rougelike.game.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.jetbrains.annotations.NotNull;
import rougelike.game.Enums.*;
import rougelike.game.Media;
import rougelike.game.box2d.Box2DHelper;
import rougelike.game.box2d.Box2DWorld;

public class Tree extends Entity{

    public Tree(Vector3 position, @NotNull Box2DWorld box2DWorld) {
        super();
        entity_type = ENTITY_TYPE.TREE;
        width = 8;
        height = 8;
        pos = position;
        texture = Media.tree;
        body = Box2DHelper.createBody(
            box2DWorld.world,
            width/2, height/2,
            width/4, 0,
            pos,
            BodyDef.BodyType.StaticBody
        );
    }
}
