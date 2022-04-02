package rougelike.game.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import rougelike.game.Control;

public class Box2DWorld {
    //Box2d world
    public World world;
    // Used to render objects which would be invisible
    private final Box2DDebugRenderer debugRenderer;

    public Box2DWorld() {
        // init world with no gravity
        this.world = new World(Vector2.Zero, true);
        this.debugRenderer = new Box2DDebugRenderer();
    }

    public void tick(OrthographicCamera camera, Control control) {
        if (control.debug) {
            debugRenderer.render(world, camera.combined);
        }

        world.step(Gdx.app.getGraphics().getDeltaTime(), 6, 2);
        world.clearForces();
    }
}
