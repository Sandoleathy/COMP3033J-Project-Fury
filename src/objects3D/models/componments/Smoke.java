package objects3D.models.componments;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Smoke {
    public Smoke(){

    }
    public void drawSmoke(Texture t){
        //glClear(GL_COLOR_BUFFER_BIT);

        // Draw a transparent rectangle
        glBegin(GL_QUADS);
        glColor4f(0f, 0f, 0f, 0f); // 设置颜色并指定透明度
        glVertex2f(10, 10);
        glVertex2f(30, 10);
        glVertex2f(30, 30);
        glVertex2f(10, 30);
        glEnd();

        // Draw a textured quad on top of the transparent rectangle
        glEnable(GL_TEXTURE_2D);
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f); // 恢复颜色为不透明

        t.bind();
        glTranslatef(-2,-3,0);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(2, 2);
        glTexCoord2f(1, 0);
        glVertex2f(4, 2);
        glTexCoord2f(1, 1);
        glVertex2f(4, 4);
        glTexCoord2f(0, 1);
        glVertex2f(2, 4);
        glEnd();

        glRotatef(90,1,0,0);
        glTranslatef(0,-3,-3);
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(2, 2);
        glTexCoord2f(1, 0);
        glVertex2f(4, 2);
        glTexCoord2f(1, 1);
        glVertex2f(4, 4);
        glTexCoord2f(0, 1);
        glVertex2f(2, 4);
        glEnd();
        glDisable(GL_TEXTURE_2D);
    }
}
