package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class DynamicTexCube {
    int[][] faces;
    Point4f[] vertices;
    public DynamicTexCube(List<Point4f> pointList){

        faces = new int[][]
                { { 0, 4, 5, 1 }, { 0, 2, 6, 4 }, { 0, 1, 3, 2 }, { 4, 6, 7, 5 }, { 1, 5, 7, 3 },
                { 2, 3, 7, 6 } };

        vertices = new Point4f[8];
        int i = 0;
        for (Point4f p : pointList){
            vertices[i] = p;
            i++;
        }
    }
    public void drawTexCube() {
        glBegin(GL_QUADS);

        for (int face = 0; face < 6; face++) { // per face
            Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
            Vector4f normal = v.cross(w).Normal();
            glNormal3f(normal.x, normal.y, normal.z);

            GL11.glTexCoord2f(0,0);
            glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
            GL11.glTexCoord2f(1,0);
            glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
            GL11.glTexCoord2f(1,1);
            glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
            GL11.glTexCoord2f(0,1);
            glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
        } // per face

        glEnd();
    }
}
