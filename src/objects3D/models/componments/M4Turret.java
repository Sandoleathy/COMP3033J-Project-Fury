package objects3D.models.componments;

import GraphicsObjects.Point4f;
import objects3D.Cylinder;
import objects3D.DynamicTexCube;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class M4Turret {
    static float[] white = { 1.0f, 1.0f, 1.0f, 1.0f };
    static float[] black = { 0.0f, 0.0f, 0.0f, 1.0f };
    public M4Turret(){

    }
    public void drawTurret(float pitchAngle){
        float radius = 3.5f;

        glColor3f(white[0], white[1], white[2]);

        glTranslatef(0.0f, 0.0f, 0.0f);
        Cylinder cylinder = new Cylinder();
        DynamicTexCube texCube;

        glPushMatrix();{
            //turret basic
            glRotatef(90.0f,1.0f,0,0);
            cylinder.drawClosedCylinder(radius,2.5f,180);

            glPushMatrix();{
                //glRotatef(pitchAngle , 0 ,1 , 0);
                //Gun Mantlet
                glRotatef(-90f,1,0,0);
                glTranslatef(-3.7f,-1.75f,0);

                List<Point4f> pList = new ArrayList<>();
                pList.add(new Point4f(-0,-0,-2.5f,0)); //0
                pList.add(new Point4f(-0,-0,2.5f,0)); //1

                pList.add(new Point4f(-0,1.5f,-2.5f,0)); //2
                pList.add(new Point4f(-0,1.5f,2.5f,0)); //3

                pList.add(new Point4f(1f,-0,-2.5f,0)); //4
                pList.add(new Point4f(1f,-0,2.5f,0)); //5
                pList.add(new Point4f(1f,1.5f,-2.5f,0)); //6
                pList.add(new Point4f(1f,1.5f,2.5f,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();

                glPushMatrix();{
                    //gun pipe
                    glRotatef(pitchAngle , 0 ,0 , 1);
                    glTranslatef(-10f,0.8f,-0f);
                    glRotatef(90,0,1,0);

                    cylinder.drawCylinder(0.3f,12f,180);
                }
                glPopMatrix();
            }
            glPopMatrix();
            glPushMatrix();{
                //back head staff
                glRotatef(-90f,1,0,0);
                glTranslatef(1.8f,-0.1f,0);

                List<Point4f> pList = new ArrayList<>();
                pList.add(new Point4f(-0,-1.5f,-3.0f,0)); //0
                pList.add(new Point4f(-0,-1.5f,3.0f,0)); //1

                pList.add(new Point4f(-0,0f,-3.0f,0)); //2
                pList.add(new Point4f(-0,0f,3.0f,0)); //3

                pList.add(new Point4f(2.5f,-1.5f,-2.0f,0)); //4
                pList.add(new Point4f(2.5f,-1.5f,2.0f,0)); //5
                pList.add(new Point4f(2.5f,0f,-2.0f,0)); //6
                pList.add(new Point4f(2.5f,0f,2.0f,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
