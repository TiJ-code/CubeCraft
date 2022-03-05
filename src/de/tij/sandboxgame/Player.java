package de.tij.sandboxgame;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class Player extends RenderObject {

    float yaw = 135f;
    float pitch = 0f;
    float walkingSpeed = 0.2f;
    float gravity = 0f;
    Vector3f acc = new Vector3f(0.0f, 0.0f, 0.0f);

    World parent = null;

    public Player(World parent) {
        this.parent = parent;
        position = new Vector3f(0f, 128.0f, 0f);
    }

    @Override
    public void render() {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(-position.x, -position.y, -position.z);
    }

    @Override
    public void update(int delta) {
        Vector3f direction = new Vector3f();

        if (acc.x >= 0) {
            direction.x = 1;
        } else {
            direction.x = -1;
        }

        if (acc.z >= 0) {
            direction.z = 1;
        } else {
            direction.z = -1;
        }

        if (parent.isHitting(new Vector3f(getPosition().x + direction.x * 2, getPosition().y - 1.0f, getPosition().z + direction.z * 2))) {
            acc.x = 0.0f;
            acc.z = 0.0f;
        }

        if (!parent.isHitting(new Vector3f(getPosition().x, getPosition().y - 2.0f, getPosition().z))) {
            if (gravity > -1.0f) {
                //gravity -= 0.1f;
            }
        }

        yaw(Mouse.getDX() * 0.1f);
        pitch(-1 * Mouse.getDY() * 0.1f);

        getPosition().y += acc.y + (gravity * 0.1f * delta);
        getPosition().x += acc.x * 0.001f * delta;
        getPosition().z += acc.z * 0.001f * delta;

        if (parent.isHitting(new Vector3f(getPosition().x, getPosition().y - 2.0f, getPosition().z))) {
            gravity = 0.0f;
        }
    }

    public void yaw(float diff) {
        yaw += diff;
    }

    public void pitch(float diff) {
        pitch += diff;
    }

    public void walkForward() {
        acc.x += walkingSpeed * (float) Math.sin(Math.toRadians(yaw));
        acc.z -= walkingSpeed * (float) Math.cos(Math.toRadians(yaw));
    }

    public void walkBackwards() {
        acc.x -= walkingSpeed * (float) Math.sin(Math.toRadians(yaw));
        acc.z += walkingSpeed * (float) Math.cos(Math.toRadians(yaw));
    }

    public void strafeLeft() {
        acc.x += walkingSpeed * (float) Math.sin(Math.toRadians(yaw - 90));
        acc.z -= walkingSpeed * (float) Math.cos(Math.toRadians(yaw - 90));
    }

    public void strafeRight() {
        acc.x += walkingSpeed * (float) Math.sin(Math.toRadians(yaw + 90));
        acc.z -= walkingSpeed * (float) Math.cos(Math.toRadians(yaw + 90));
    }

    public void jump() {
        if (parent.isHitting(new Vector3f(getPosition().x, getPosition().y - 2.0f, getPosition().z))) {
            gravity += 1.0f;
        }
    }
}
