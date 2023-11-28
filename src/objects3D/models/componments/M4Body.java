package objects3D.models.componments;

import GraphicsObjects.Point4f;
import objects3D.Cylinder;
import objects3D.DynamicTexCube;
import objects3D.Sphere;
import objects3D.TexSphere;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

public class M4Body {
    static float[] white = { 1.0f, 1.0f, 1.0f, 1.0f };
    static float[] black = { 0.0f, 0.0f, 0.0f, 1.0f };
    public M4Body(){

    }
    public void drawBody(List<Texture> tt){
        float width = 4.5f;
        //y is vertical
        //z is front back
        //x is left right
        glPushMatrix();{
            //here is the front armor
            tt.get(5).bind();
            glColor3f(white[0], white[1], white[2]);

            glTranslatef(0.0f, 0.5f, 0.0f);

            List<Point4f> pList = new ArrayList<>();
            pList.add(new Point4f(-2,-2,-width,0)); //0
            pList.add(new Point4f(-2,-2,width,0)); //1

            pList.add(new Point4f(-2,-1.5f,-width,0)); //2
            pList.add(new Point4f(-2,-1.5f,width,0)); //3

            pList.add(new Point4f(1f,-2,-width,0)); //4
            pList.add(new Point4f(1f,-2,width,0)); //5
            pList.add(new Point4f(1f,1f,-width,0)); //6
            pList.add(new Point4f(1f,1f,width,0)); //7
            DynamicTexCube dynamicTexCube = new DynamicTexCube(pList);
            dynamicTexCube.drawTexCube();

            glPushMatrix();{
                //here is the turret base
                tt.get(0).bind();
                glTranslatef(3.0f, 0.0f, 0.0f);

                pList = new ArrayList<>();
                pList.add(new Point4f(-2,-2,-width,0)); //0
                pList.add(new Point4f(-2,-2,width,0)); //1

                pList.add(new Point4f(-2,1f,-width,0)); //2
                pList.add(new Point4f(-2,1f,width,0)); //3

                pList.add(new Point4f(3,-2,-width,0)); //4
                pList.add(new Point4f(3,-2,width,0)); //5
                pList.add(new Point4f(3,1f,-width,0)); //6
                pList.add(new Point4f(3,1f,width,0)); //7

                dynamicTexCube = new DynamicTexCube(pList);
                dynamicTexCube.drawTexCube();

                glPushMatrix();{

                    // here is the engine

                    glTranslatef(5.0f, 0.0f, 0.0f);

                    pList = new ArrayList<>();
                    pList.add(new Point4f(-2,-2,-width,0)); //0
                    pList.add(new Point4f(-2,-2,width,0)); //1

                    pList.add(new Point4f(-2,1f,-width,0)); //2
                    pList.add(new Point4f(-2,1f,width,0)); //3

                    pList.add(new Point4f(5,-2,-width,0)); //4
                    pList.add(new Point4f(5,-2,width,0)); //5
                    pList.add(new Point4f(5,0f,-width,0)); //6
                    pList.add(new Point4f(5,0f,width,0)); //7

                    dynamicTexCube = new DynamicTexCube(pList);
                    dynamicTexCube.drawTexCube();

                    glPushMatrix();{
                        // here is the staff on engine
                        tt.get(4).bind();
                        glTranslatef(2.5f, 0.0f, 0.0f);

                        pList = new ArrayList<>();
                        pList.add(new Point4f(-0,-0,-(width-0.3f),0)); //0
                        pList.add(new Point4f(-0,-0,(width-0.3f),0)); //1

                        pList.add(new Point4f(-0,1f,-(width-0.3f),0)); //2
                        pList.add(new Point4f(-0,1f,(width-0.3f),0)); //3

                        pList.add(new Point4f(2,-1,-(width-0.3f),0)); //4
                        pList.add(new Point4f(2,-1,(width-0.3f),0)); //5
                        pList.add(new Point4f(2,1f,-(width-0.3f),0)); //6
                        pList.add(new Point4f(2,1f,(width-0.3f),0)); //7

                        dynamicTexCube = new DynamicTexCube(pList);
                        dynamicTexCube.drawTexCube();
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
            glPopMatrix();

            glPushMatrix();{
                tt.get(0).bind();
                // here is the chassis
                //glColor3f(black[0], black[1], black[2]);
                glColor3f(white[0], white[1], white[2]);
                glTranslatef(-2.0f, -1.5f, 0.0f);

                float w = width - 2f;
                float length = 15.5f;
                pList = new ArrayList<>();
                pList.add(new Point4f(2,-1.5f,-(w),0)); //0
                pList.add(new Point4f(2,-1.5f,(w),0)); //1

                pList.add(new Point4f(-0,0f,-(w),0)); //2
                pList.add(new Point4f(-0,0f,(w),0)); //3

                pList.add(new Point4f(length,-1.5f,-(w),0)); //4
                pList.add(new Point4f(length,-1.5f,(w),0)); //5
                pList.add(new Point4f(length,0f,-(w),0)); //6
                pList.add(new Point4f(length,0f,(w),0)); //7

                dynamicTexCube = new DynamicTexCube(pList);
                dynamicTexCube.drawTexCube();

                glPushMatrix();{
                    tt.get(3).bind();
                    //left track
                    //glColor3f(black[0], black[1], black[2]);
                    glTranslatef(0.0f , -0.6f , -4.5f);

                    float trackWidth = 1.5f;
                    pList = new ArrayList<>();
                    pList.add(new Point4f(2,-2.5f,-0,0)); //0
                    pList.add(new Point4f(2,-2.5f,trackWidth,0)); //1

                    pList.add(new Point4f(0.2f,0f,-0,0)); //2
                    pList.add(new Point4f(0.2f,0f,trackWidth,0)); //3

                    pList.add(new Point4f(14f,-2.5f,-0,0)); //4
                    pList.add(new Point4f(14f,-2.5f,trackWidth,0)); //5
                    pList.add(new Point4f(15.8f,0f,-0,0)); //6
                    pList.add(new Point4f(15.8f,0f,trackWidth,0)); //7

                    dynamicTexCube = new DynamicTexCube(pList);
                    dynamicTexCube.drawTexCube();

                }
                glPopMatrix();
                glPushMatrix();{
                    //right track
                    tt.get(3).bind();
                    glTranslatef(0.0f , -0.6f , 3.0f);

                    float trackWidth = 1.5f;
                    pList = new ArrayList<>();
                    pList.add(new Point4f(2,-2.5f,-0,0)); //0
                    pList.add(new Point4f(2,-2.5f,trackWidth,0)); //1

                    pList.add(new Point4f(0.2f,0f,-0,0)); //2
                    pList.add(new Point4f(0.2f,0f,trackWidth,0)); //3

                    pList.add(new Point4f(14f,-2.5f,-0,0)); //4
                    pList.add(new Point4f(14f,-2.5f,trackWidth,0)); //5
                    pList.add(new Point4f(15.8f,0f,-0,0)); //6
                    pList.add(new Point4f(15.8f,0f,trackWidth,0)); //7

                    dynamicTexCube = new DynamicTexCube(pList);
                    dynamicTexCube.drawTexCube();
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
