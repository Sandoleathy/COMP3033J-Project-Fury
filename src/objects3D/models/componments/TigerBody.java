package objects3D.models.componments;

import GraphicsObjects.Point4f;
import objects3D.Cylinder;
import objects3D.DynamicTexCube;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class TigerBody {
    static float[] white = { 1.0f, 1.0f, 1.0f, 1.0f };
    public void drawBody(List<Texture> tt){
        glColor3f(white[0], white[1], white[2]);

        glTranslatef(0.0f, 0.5f, 0.0f);

        float width = 12.0f;
        DynamicTexCube texCube;
        Cylinder cylinder = new Cylinder();
        List<Point4f> pList = new ArrayList<>();
        glPushMatrix();{
            //tank body
            pList.add(new Point4f(-0,-0,0,0)); //0
            pList.add(new Point4f(-0,-0,width,0)); //1
            pList.add(new Point4f(0.3f,3.5f,0,0)); //2
            pList.add(new Point4f(0.3f,3.5f,width,0)); //3

            pList.add(new Point4f(18f,-0,0,0)); //4
            pList.add(new Point4f(18f,-0,width,0)); //5
            pList.add(new Point4f(18.4f,3.5f,0,0)); //6
            pList.add(new Point4f(18.4f,3.5f,width,0)); //7

            texCube = new DynamicTexCube(pList);
            texCube.drawTexCube();

            glPushMatrix();{
                pList = new ArrayList<>();
                //front armor
                glTranslatef(-2.8f,0f,0);
                //glRotatef(15,0,0,1);
                pList.add(new Point4f(-0,-0,0,0)); //0
                pList.add(new Point4f(-0,-0,width,0)); //1
                pList.add(new Point4f(-0,0.3f,0,0)); //2
                pList.add(new Point4f(-0,0.3f,width,0)); //3

                pList.add(new Point4f(3f,-0,0,0)); //4
                pList.add(new Point4f(3f,-0,width,0)); //5
                pList.add(new Point4f(3f,1.5f,0,0)); //6
                pList.add(new Point4f(3f,1.5f,width,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();
            }
            glPopMatrix();
            glPushMatrix();{
                //chassis
                float w = 2 * width /3;
                pList = new ArrayList<>();
                glTranslatef(0f,0f,0);
                glRotatef(180,1,0,0);
                glTranslatef(-2.8f,0,-width+(width/6)+0.05f);

                pList.add(new Point4f(-0,-0,0,0)); //0
                pList.add(new Point4f(-0,-0,w,0)); //1
                pList.add(new Point4f(-0,1.5f,0,0)); //2
                pList.add(new Point4f(-0,1.5f,w,0)); //3

                pList.add(new Point4f(3f,-0,0,0)); //4
                pList.add(new Point4f(3f,-0,w,0)); //5
                pList.add(new Point4f(3f,2.0f,0,0)); //6
                pList.add(new Point4f(3f,2.0f,w,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();

                glPushMatrix();{
                    // chassis back
                    pList = new ArrayList<>();
                    glTranslatef(3f,0f,0);
                    pList.add(new Point4f(-0,-0,0,0)); //0
                    pList.add(new Point4f(-0,-0,w,0)); //1
                    pList.add(new Point4f(-0,2.0f,0,0)); //2
                    pList.add(new Point4f(-0,2.0f,w,0)); //3

                    pList.add(new Point4f(17.8f,-0,0,0)); //4
                    pList.add(new Point4f(17.8f,-0,w,0)); //5
                    pList.add(new Point4f(17.8f,1.3f,0,0)); //6
                    pList.add(new Point4f(17.8f,1.3f,w,0)); //7

                    texCube = new DynamicTexCube(pList);
                    texCube.drawTexCube();


                    glPushMatrix();{
                        //engine pipe
                        glRotatef(90,1,0,0);
                        glTranslatef(18,2,-1.3f);

                        glRotatef(10,0,1,0);
                        cylinder.drawClosedCylinder(0.8f,5,180);

                        glPushMatrix();{
                            //another engine pipe
                            glTranslatef(0,4f,0);
                            cylinder.drawClosedCylinder(0.8f,5,180);
                        }
                        glPopMatrix();
                    }
                    glPopMatrix();
                }
                glPopMatrix();
            }
            glPopMatrix();
            //back to body
            glPushMatrix();{
                //skirt right
                pList = new ArrayList<>();
                glTranslatef(0f,0.2f,0);
                glRotatef(180,1,0,0);
                glRotatef(-20,1,0,0);
                pList.add(new Point4f(-0,-0,0,0)); //0
                pList.add(new Point4f(-0,-0,1,0)); //1
                pList.add(new Point4f(-0,0.2f,0,0)); //2
                pList.add(new Point4f(-0,0.2f,1,0)); //3

                pList.add(new Point4f(18.6f,-0,0,0)); //4
                pList.add(new Point4f(18.6f,-0,1,0)); //5
                pList.add(new Point4f(18.6f,0.2f,0,0)); //6
                pList.add(new Point4f(18.6f,0.2f,1,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();
            }
            glPopMatrix();
            glPushMatrix();{
                //skirt left
                pList = new ArrayList<>();
                glTranslatef(0f,0f,width);
                glRotatef(20,1,0,0);
                pList.add(new Point4f(-0,-0,0,0)); //0
                pList.add(new Point4f(-0,-0,1,0)); //1
                pList.add(new Point4f(-0,0.2f,0,0)); //2
                pList.add(new Point4f(-0,0.2f,1,0)); //3

                pList.add(new Point4f(18.6f,-0,0,0)); //4
                pList.add(new Point4f(18.6f,-0,1,0)); //5
                pList.add(new Point4f(18.6f,0.2f,0,0)); //6
                pList.add(new Point4f(18.6f,0.2f,1,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();
            }
            glPopMatrix();
            //back to body
            glPushMatrix();{
                //right track
                tt.get(7).bind();
                glTranslatef(-3f,-0.3f,10f);

                pList = new ArrayList<>();
                pList.add(new Point4f(2,-3f,0,0)); //0
                pList.add(new Point4f(2,-3f,2,0)); //1
                pList.add(new Point4f(-0,0f,0,0)); //2
                pList.add(new Point4f(-0,0f,2,0)); //3

                pList.add(new Point4f(19f,-3f,0,0)); //4
                pList.add(new Point4f(19f,-3f,2,0)); //5
                pList.add(new Point4f(21f,0f,0,0)); //6
                pList.add(new Point4f(21f,0f,2,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();
            }
            glPopMatrix();

            glPushMatrix();{
                //left track
                glTranslatef(-3f,-0.3f,0f);

                pList = new ArrayList<>();
                pList.add(new Point4f(2,-3f,0,0)); //0
                pList.add(new Point4f(2,-3f,2,0)); //1
                pList.add(new Point4f(-0,0f,0,0)); //2
                pList.add(new Point4f(-0,0f,2,0)); //3

                pList.add(new Point4f(19f,-3f,0,0)); //4
                pList.add(new Point4f(19f,-3f,2,0)); //5
                pList.add(new Point4f(21f,0f,0,0)); //6
                pList.add(new Point4f(21f,0f,2,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }
}
