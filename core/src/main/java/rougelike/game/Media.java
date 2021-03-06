package rougelike.game;

import com.badlogic.gdx.graphics.Texture;

public class Media {
    // TILE
    // class variables
    public static Texture grass_01, grass_02, grass_03, grass_04;
    public static Texture grass_left, grass_right;
    public static Texture grass_left_upper_edge, grass_right_upper_edge;
    public static Texture grass_top, grass_top_right, grass_top_left;
    public static Texture water_01, water_02, water_03, water_04;
    public static Texture cliff, water;
    public static Texture hero, tree;

    public static void load_assets(){
        // Source https://opengameart.org/content/micro-tileset-overworld-and-dungeon
        // Example
        // http://opengameart.org/sites/default/files/styles/watermarked/public/Render_0.png
        grass_01 = new Texture("8x8/grass/grass_01.png");
        grass_02 = new Texture("8x8/grass/grass_02.png");
        grass_03 = new Texture("8x8/grass/grass_03.png");
        grass_04 = new Texture("8x8/grass/grass_04.png");

        grass_left = new Texture("8x8/grass/right_grass_edge.png");
        grass_right = new Texture("8x8/grass/left_grass_edge.png");

        grass_left_upper_edge = new Texture("8x8/grass/left_upper_edge.png");
        grass_right_upper_edge = new Texture("8x8/grass/right_upper_edge.png");

        grass_top = new Texture("8x8/grass/top.png");
        grass_top_right = new Texture("8x8/grass/top_right.png");
        grass_top_left = new Texture("8x8/grass/top_left.png");

        water_01 = new Texture("8x8/water/water_01.png");
        water_02 = new Texture("8x8/water/water_02.png");
        water_03 = new Texture("8x8/water/water_03.png");
        water_04 = new Texture("8x8/water/water_04.png");
        cliff    = new Texture("8x8/cliff.png");
        hero = new Texture("entities/hero/hero.png");
        tree = new Texture("entities/tree.png");
    }

    public void dispose() {
        grass_01.dispose();
        grass_02.dispose();
        grass_03.dispose();
        grass_04.dispose();
        grass_left.dispose();
        grass_right.dispose();
        grass_left_upper_edge.dispose();
        grass_right_upper_edge.dispose();
        grass_top.dispose();
        grass_top_right.dispose();
        grass_top_left.dispose();
        water_01.dispose();
        water_02.dispose();
        water_03.dispose();
        water_04.dispose();
        cliff.dispose();
        hero.dispose();
        tree.dispose();
    }
}
