package rougelike.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import rougelike.game.map.Island;
import rougelike.game.map.Tile;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RoguelikeGdx extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	// Display
	private OrthographicCamera camera;
	private Control control;
	private int displayW, displayH;

	// Island
	Island island;

	// Entities
	Hero hero;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		// CAMERA
		displayW = Gdx.graphics.getWidth();
		displayH = Gdx.graphics.getHeight();
		// For 800x600 we will get 266*200
		int h = (int) (displayH/Math.floor(displayH/160));
		int w = (int) (displayW/(displayH/ (displayH/Math.floor(displayH/160))));
		camera = new OrthographicCamera(w, h);
		camera.zoom = .4f;

		// Keyboard Input
		control = new Control(displayW, displayH, camera);
		Gdx.input.setInputProcessor(control);

		// Load Assets
		Media.load_assets();

		island = new Island();
		hero = new Hero(island.centre_tile.pos);
	}

	@Override
	public void render () {
		ScreenUtils.clear(new Color(0x30346DFF));

		// GAME LOGIC
		hero.update(control);

		camera.position.x = hero.pos.x;
		camera.position.y = hero.pos.y;
		camera.update();

		// GAME DRAW
		batch.setProjectionMatrix(camera.combined);// draw only visible in camera
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


		batch.begin();

		// Draw all tiles in the chunk / chunk rows
		for (ArrayList<Tile> row: island.chunk.tiles) {
			for (Tile tile: row) {
				batch.draw(tile.texture, tile.pos.x, tile.pos.y, tile.size, tile.size);
				if (tile.secondary_texture != null) {
					batch.draw(tile.secondary_texture, tile.pos.x, tile.pos.y, tile.size, tile.size);
				}
			}
		}

		hero.draw(batch);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}