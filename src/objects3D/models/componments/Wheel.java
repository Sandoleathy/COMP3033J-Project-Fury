package objects3D.models.componments;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lwjgl.opengl.GL11.*;

public class Wheel {
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    public void drawWheel(){
        glColor3f(black[0], black[1], black[2]);
        glBegin(GL_POLYGON);
        for (int i = 0; i < 360; i += 10) {
            float angle = (float) (i * 3.14159 / 180.0);
            float x = (float) (0.5 * cos(angle));
            float y = (float) (0.5 * sin(angle));
            glVertex2f(x, y);
        }
        glEnd();

        // 绘制轮辐
        glColor3f(1.0f, 1.0f, 1.0f);  // 设置辐的颜色为白色
        glBegin(GL_LINES);
        for (int i = 0; i < 360; i += 30) {
            float angle = (float) (i * 3.14159 / 180.0);
            float x1 = 0;
            float y1 = 0;
            float x2 = (float) (0.5 * cos(angle));
            float y2 = (float) (0.5 * sin(angle));
            glVertex2f(x1, y1);
            glVertex2f(x2, y2);
        }
        glEnd();
    }
}
