package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(TETile[][] world, int x, int y, int s, TETile tile) {
        if (s < 2) {
            throw new IllegalArgumentException("length must be greater than 1");
        }
        for (int i = 0; i < s * 2; i++) {
            int thisY = y + i;

            int width = hexRowWidth(s, i);
            int offset = hexRowOffset(s, i);
            int thisX = offset + x;

            addRow(world, thisX, thisY, width, tile);
        }
    }

    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - effectiveI - 1;
        }
        return s + 2 * effectiveI;
    }

    public static int hexRowOffset(int s, int i) {
        int effctiveI = i;
        if (i >= s) {
            effctiveI = 2 * s - 1 - effctiveI;
        }
        return -effctiveI;
    }

    public static void addRow(TETile[][] world, int x, int y, int s, TETile tile) {
        for (int xi = 0; xi < s; xi++) {
            int tempX = x + xi;
            world[tempX][y] = tile;
        }
    }

    public static void addTesselationOfHexagons(TETile[][] world, int x, int y, int s) {
        for (int i = 0; i < 2 * s - 1; i++) {
            int count = hexColHeight(s, i);
            int thisX = x + hexColOffsetX(s, i);
            int thisY = y + hexColOffsetY(s, i);
            addColumnOfHexagons(world, thisX, thisY, s, count);
        }
    }
    public static int hexColHeight(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 2 - effectiveI;
        }
        return s + effectiveI;
    }
    public static int hexColOffsetX(int s, int i) {
        return (2 * s - 1) * i;
    }
    public static int hexColOffsetY(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 2 - i;
        }
        return -effectiveI * s;
    }

    public static void addColumnOfHexagons(TETile[][] world, int x, int y, int s, int n) {
        for (int i = 0; i < n; i++) {
            int thisY = y + s * 2 * i;
            addHexagon(world, x, thisY, s, randomTile());
        }
    }

    public static TETile randomTile() {
        Random r = new Random();
        int t = r.nextInt(6);
        switch (t) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.AVATAR;
            case 4: return Tileset.FLOOR;
            case 5: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }
    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(70, 70);
        TETile[][] world = new TETile[70][70];
        for (int x = 0; x < 70; x += 1) {
            for (int y = 0; y < 70; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        HexWorld.addTesselationOfHexagons(world, 25, 25, 3);
        ter.renderFrame(world);
    }
}
