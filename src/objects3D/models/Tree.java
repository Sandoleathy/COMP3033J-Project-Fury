package objects3D.models;

import objects3D.Cylinder;
import objects3D.TexSphere;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Tree {
    public Tree(){

    }
    public void drawTree(List<Texture> tt){
        Cylinder cylinder = new Cylinder();
        TexSphere sphere = new TexSphere();
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        glPushMatrix();{
            glTranslatef(0,0,0);
            glRotatef(-90,1,0,0);
            //glColor3f(255,255,255);
            //glColor3f(255,79,0);
            cylinder.drawCylinder(1,20,32);
            glPushMatrix();{
                tt.get(1).bind();
                glTranslatef(0,0,20);
                sphere.DrawTexSphere(10,32,32,tt.get(1));
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
