package objects3D.models;

import GraphicsObjects.Vector4f;

public class RigidBody {
    public float x , y , z;
    public float gravity;
    public Vector4f velocity;
    public float rotation;
    public boolean isAnimationFinished = false;
    public float waitCounter = 0;
    public RigidBody(float x , float y , float z , Vector4f v){
        this.x = x;
        this.y = y;
        this.z = z;
        this.gravity = 0.5f;
        this.velocity = v;
        rotation = 0;
    }
    public void update(float deltaTime) {
        // Update location based on speed
        x += velocity.x * deltaTime;
        y = y + (velocity.y * deltaTime);
        z += velocity.z * deltaTime;
        // Simple vertical motion, calculating velocity change based on gravity
        velocity.y -= gravity * deltaTime;
        rotation += 4;
        if(y <= 0){
            y = 0;
            velocity.z = 0;
            velocity.x = 0;
            rotation = 0;
            waitCounter += deltaTime;
            if(waitCounter >= 200){
                this.isAnimationFinished = true;
            }
        }
        //System.out.println(x + " " + y + " " + z + " delta: " + deltaTime + "  v: " + velocity.y);
    }
}
