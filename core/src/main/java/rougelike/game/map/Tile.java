package rougelike.game.map;

import com.badlogic.gdx.graphics.Texture;
import rougelike.game.Entity;
import rougelike.game.Enums;

public class Tile extends Entity {
    public int size;
    public int row;
    public int col;
    public String code;
    public Texture secondary_texture;
    public Texture texture;
    public Enums.TILETYPE type;

    public Tile(float x, float y, int size, Enums.TILETYPE type, Texture texture) {
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

    public boolean is_grass() {
        return type == Enums.TILETYPE.GRASS;
    }

    public boolean is_water() {
        return type == Enums.TILETYPE.WATER;
    }

    public boolean is_cliff() {
        return type == Enums.TILETYPE.CLIFF;
    }

    public boolean is_passable() {
        return !is_water() && !is_cliff();
    }

    public boolean is_not_passable() {
        return !is_passable();
    }
}
