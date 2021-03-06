package de.tij.sandboxgame;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class BlockHelper {

    public static final float div = 1.0f / 16.0f;

    public static enum SIDE {

        LEFT, RIGHT, TOP, BOTTOM, FRONT, BACK;
    };

    static Vector2f calcOffsetForTextureAt(int x, int y) {
        return new Vector2f(x * div, y * div);
    }

    public static Vector2f getTextureOffsetFor(int type, SIDE side) {
        switch (type) {
            // Grass block
            case 0x1:
                if (side == SIDE.LEFT || side == SIDE.RIGHT || side == SIDE.FRONT || side == SIDE.BACK) {
                    return calcOffsetForTextureAt(3, 0);
                } else if (side == SIDE.TOP) {
                    return calcOffsetForTextureAt(0, 0);
                } else {
                    return calcOffsetForTextureAt(2, 0);
                }
                // Dirt block
            case 0x2:
                return calcOffsetForTextureAt(2, 0);
            // Stone block
            case 0x3:
                return calcOffsetForTextureAt(1, 0);
            // Water block
            case 0x4:
                return calcOffsetForTextureAt(15, 13);
        }

        return calcOffsetForTextureAt(2, 0);
    }

    public static Vector3f getColorOffsetFor(int type, SIDE side) {
        switch (type) {
            // Grass block
            case 0x1:
                if (side == SIDE.TOP) {
                    return new Vector3f(0.1f, 0.75f, 0.1f);
                }
                break;
        }
        return new Vector3f(1.0f, 1.0f, 1.0f);
    }
}