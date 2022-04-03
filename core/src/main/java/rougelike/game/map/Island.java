package rougelike.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import rougelike.game.entities.Entity;
import rougelike.game.Enums;
import rougelike.game.Media;
import rougelike.game.box2d.Box2DHelper;
import rougelike.game.box2d.Box2DWorld;
import rougelike.game.entities.Tree;

import java.util.ArrayList;
import java.util.Arrays;

public class Island {

    public Tile centerTile;
    public Tile clickedTile;

    // CHUNKS TODO: Add multiple chunks
    // public Map<Integer, ArrayList<Chunk> chunks = new Map<Integer, ArrayList<Chunk>();

    // ONE CHUNK
    public Chunk chunk;
    public ArrayList<Entity> entities = new ArrayList<>();

    // TRACK CLICK
    int current_tile_no;
    int current_col;
    int current_row;


    // Creating a code to describe a tiles neighbours allows us to make graphic and logic decisions.
    // 1 = walkable | 0 = not-walkable
    // The centre tile shown below has a code of "111000000"
    /*
     * Grass Grass
     * top   right
     * ----- -----
     *  000   100
     *  000   100
     *  111   100
     */
    // Arrays for mapping code to texture
    String[] a_grass_left = {"001001001", "001001001", "001001000", "000001001"};
    String[] a_grass_right = {"100100100","100100000","000100100"};
    String[] a_grass_r_end = {"100000000"};
    String[] a_grass_l_end = {"001000000"};
    String[] a_grass_top = {"000000111", "000000011","000000110"};
    String[] a_grass_top_right = {"000000100"};
    String[] a_grass_top_left = {"000000001"};

    public Island(Box2DWorld box2DWorld){
        reset(box2DWorld);
    }

    public void reset(Box2DWorld box2DWorld) {
        entities.clear();
        box2DWorld.clearAllBodies();
        setup_tiles();
        code_tiles();
        generateHitBoxes(box2DWorld);
        addEntities(box2DWorld);
    }

    /*
    * Looping all of the tiles and checking that they are not passable
    * but not all water we are finding the edge of the island,
    * these are the tiles that should stop the hero moving
    * */
    private void generateHitBoxes(Box2DWorld box2DWorld) {
        for (ArrayList<Tile> row: chunk.tiles) {
            for (Tile tile: row) {
                if (tile.isNotPassable() && tile.isNotAllWater()) {
                    Box2DHelper.createBody(
                        box2DWorld.world,
                        chunk.tile_size,
                        chunk.tile_size,
                        0,0,tile.pos,
                        BodyDef.BodyType.StaticBody
                    );
                }
            }
        }
    }

    private void setup_tiles(){
        chunk = new Chunk(33,33, 8);

        int current_row = 0;
        int rng_w = MathUtils.random(5,8);
        int rng_h = MathUtils.random(5,8);

        int centre_tile_row = chunk.number_rows / 2;
        int centre_tile_col = chunk.number_cols / 2;
        int first_tile_row = centre_tile_row - (rng_h);

        int max_row = centre_tile_row + rng_h;
        int min_row = centre_tile_row - rng_h;
        int max_col = centre_tile_col + rng_w;
        int min_col = centre_tile_col - rng_w;

        // CHUNK ROW
        ArrayList<Tile> chunk_row = new ArrayList<>();

        // If number of tiles is needed.
        // int num_tiles = ((max_col - min_col)-1) * ((max_row - min_row)-1);


        /*
        * first we fill entire map with Water
        * then with max row & col number we fill grass
        * e.g. with rng_w = 8 and rng_h = 5
        * then around center tile 33/2 = 16 (int) we build small island
        * if tile falls btw min and max cols and rows we fill grass tile
        * if its edge we choose appropriate type e.g. a cliff
        * */
        for(int row = 0; row < chunk.number_rows; row ++){
            for(int col = 0; col < chunk.number_cols; col ++){
                // Create TILE
                Tile tile = new Tile(col, row, chunk.tile_size, Enums.TILE_TYPE.WATER, random_water());

                // Make a small island
                if(row > min_row && row < max_row && col > min_col && col < max_col){
                    tile.texture = random_grass();
                    tile.type = Enums.TILE_TYPE.GRASS;

                    if(row == first_tile_row + 1){
                        tile.texture = Media.cliff;
                        tile.type = Enums.TILE_TYPE.CLIFF;
                    } else {
                        // Chance to add trees etc
                    }
                }

                // ADD TILE TO CHUNK
                if(current_row == row){
                    // Add tile to current row
                    chunk_row.add(tile);

                    // Last row and column?
                    if (row == chunk.number_rows - 1 && col == chunk.number_cols - 1){
                        chunk.tiles.add(chunk_row);
                    }
                } else {
                    // New row
                    current_row = row;

                    // Add row to chunk
                    chunk.tiles.add(chunk_row);

                    // Clear chunk row
                    chunk_row = new ArrayList<Tile>();

                    // Add first tile to the new row
                    chunk_row.add(tile);
                }
            }
        }

        // Set centre tile for camera positioning
        centerTile = chunk.get_tile(centre_tile_row, centre_tile_col);
    }

    private void update_image(Tile tile) {
        // Secondary Texture is to add edges to tiles
        // TODO: Add array of textures per tile
        if(Arrays.asList(a_grass_left).contains(tile.code)){
            tile.secondary_texture = Media.grass_left;
        } else if(Arrays.asList(a_grass_right).contains(tile.code)){
            tile.secondary_texture = Media.grass_right;
        } else if(Arrays.asList(a_grass_r_end).contains(tile.code)){
            tile.secondary_texture = Media.grass_left_upper_edge;
        } else if(Arrays.asList(a_grass_l_end).contains(tile.code)){
            tile.secondary_texture = Media.grass_right_upper_edge;
        } else if(Arrays.asList(a_grass_top).contains(tile.code)){
            tile.secondary_texture = Media.grass_top;
        } else if(Arrays.asList(a_grass_top_right).contains(tile.code)){
            tile.secondary_texture = Media.grass_top_right;
        } else if(Arrays.asList(a_grass_top_left).contains(tile.code)){
            tile.secondary_texture = Media.grass_top_left;
        }
    }

    private Texture random_grass(){
        Texture grass;

        int tile = MathUtils.random(20);
        switch (tile) {
            case 1:
                grass = Media.grass_01;
                break;
            case 2:
                grass = Media.grass_02;
                break;
            case 3:
                grass = Media.grass_03;
                break;
            case 4:
                grass = Media.grass_04;
                break;
            default:
                grass = Media.grass_01;
                break;
        }

        return grass;
    }

    private Texture random_water(){
        Texture water;

        int tile = MathUtils.random(20);
        switch (tile) {
            case 2:
                water = Media.water_02;
                break;
            case 3:
                water = Media.water_03;
                break;
            case 4:
                water = Media.water_04;
                break;
            default:
                water = Media.water_01;
                break;
        }

        return water;
    }

    private void code_tiles() {
        // Loop all tiles and set the initial code

        // 1 CHUNK ONLY ATM
        for(ArrayList<Tile> row : chunk.tiles){
            for(Tile tile : row){
                // Check all surrounding tiles and set 1 for walkable, 0 for non-walkable
                // 0 0 0
                // 0 X 0
                // 0 0 0

                int[] rows = {1,0,-1};
                int[] cols = {-1,0,1};

                for(int r: rows){
                    for(int c: cols){
                        tile.code += chunk.get_tile_code(tile.row + r, tile.col + c);
                        update_image(tile);
                    }
                }
            }
        }
    }

    void addEntities(Box2DWorld box2DWorld) {
        // Loop all tiles and add random trees
        for (ArrayList<Tile> row: chunk.tiles) {
            for (Tile tile: row) {
                if (tile.isGrass()) {
                    if (MathUtils.random(100) > 90) {
                        entities.add(new Tree(tile.pos, box2DWorld));
                    }
                }
            }
        }
    }

    public Vector3 getCenterTilePos() {
        return centerTile.pos;
    }

    public void dispose() {

    }
}
