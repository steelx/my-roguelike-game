package rougelike.game.map;

import com.badlogic.gdx.graphics.Texture;
import rougelike.game.entities.Entity;
import rougelike.game.Enums.*;

public class Tile extends Entity {
    public int size;
    public int row;
    public int col;
    public String code;
    public Texture secondary_texture;
    public Texture texture;
    public TILE_TYPE type;

    public Tile(float x, float y, int size, TILE_TYPE type, Texture texture) {
        super();// calls Entity class
        pos.x = x*size;
        pos.y = y*size;
        this.size = size;
        this.texture = texture;
        this.type = type;
        this.col = (int) x;
        this.row = (int) y;
        this.code = "";
    }

    @Override
    public String toString() {
        return "Tile{" +
                "size=" + size +
                ", row=" + row +
                ", col=" + col +
                ", code='" + code + '\'' +
                ", secondary_texture=" + secondary_texture +
                ", texture=" + texture +
                ", type=" + type +
                '}';
    }

    public boolean isGrass() {
        return type == TILE_TYPE.GRASS;
    }

    public boolean isWater() {
        return type == TILE_TYPE.WATER;
    }

    public boolean isCliff() {
        return type == TILE_TYPE.CLIFF;
    }

    public boolean isPassable() {
        return !isWater() && !isCliff();
    }

    public boolean isNotPassable() {
        return !isPassable();
    }

    public boolean isAllWater() {
        return code.equals("000000000");
    }
    public boolean isNotAllWater() {
        return !isAllWater();
    }
}
