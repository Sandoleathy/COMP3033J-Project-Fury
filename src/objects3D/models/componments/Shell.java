package objects3D.models.componments;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import objects3D.Cylinder;

import static org.lwjgl.opengl.GL11.*;

public class Shell {
    Vector4f velocity;
    public Point4f pos;
    //gun shell class
    public Shell(Point4f pos , Vector4f v){
        this.velocity = v;
        this.pos = pos;
    }
    public void drawShell(){
        Cylinder cylinder = new Cylinder();
        glPushMatrix();{
            cylinder.drawCylinder(0.2f,10,32);
        }
        glPopMatrix();
    }
}
