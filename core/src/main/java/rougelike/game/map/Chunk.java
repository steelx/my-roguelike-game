package rougelike.game.map;

import java.util.ArrayList;

public class Chunk {
    int number_rows;
    int number_cols;
    int tile_size;
    // Tiles are split into arrays of rows
    public ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();

    public Chunk(int number_rows, int number_cols, int tile_size) {
        this.number_rows = number_rows;
        this.number_cols = number_cols;
        this.tile_size = tile_size;// scale of a tile
    }

    public Tile get_tile(int row, int col) {
        if (tiles.size() > row && row >= 0) {
            ArrayList<Tile> chunk_row = tiles.get(row);

            if (chunk_row != null && chunk_row.size() > col && col >= 0) {
                return chunk_row.get(col);
            }
        }
        return null;
    }

    /*
    get_tile_code
    a tile code allows us to make graphics and logic decision.
    code 1 is walkable/passable/grass tile,
    code 0 is either water or cliff/edges
    * */
    public String get_tile_code(int row, int col) {
        if(tiles.size() > row && row >= 0){
            ArrayList<Tile> chunk_row = tiles.get(row);

            if(chunk_row != null && chunk_row.size() > col && col >= 0){
                Tile tile = chunk_row.get(col);
                return tile.is_grass() ? "1" : "0";
            }
        }
        return null;
    }

}
