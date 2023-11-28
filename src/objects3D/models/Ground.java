package objects3D.models;

import objects3D.TexCube;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Ground {
    private TexCube cube;
    public Ground(){
        cube = new TexCube();
    }
    public void drawGround(Texture ground){
        glPushMatrix();{
            glColor3f(255,255,255);
            ground.bind();
            glTranslatef(0,-0.1f,0);
            GL11.glScalef(1000f, 0.1f, 1000f);
            cube.drawTexCube();
        }
        glPopMatrix();
    }
}
