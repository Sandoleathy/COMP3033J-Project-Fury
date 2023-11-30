/**
 * This code was written by @Sandoleathy
 * This class is for the Project_Texture_2023 from COMP3033J
 * 29/11/2023
 */

package objects3D.models;

import GraphicsObjects.Point4f;
import objects3D.Cylinder;
import objects3D.DynamicTexCube;
import objects3D.TexSphere;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Flag {
    Cylinder cylinder;
    DynamicTexCube dynamicTexCube;
    TexSphere texSphere;
    float flagRotation;
    boolean flagDirection;
    public Flag(){
        this.cylinder = new Cylinder();
        texSphere = new TexSphere();
        flagRotation = 90;
        flagDirection = true;
    }
    public void drawFlag(Texture flagTexture , Texture sphereTexture , Texture baseTexture){
        flagTexture.bind();
        glPushMatrix();{
            // base
            baseTexture.bind();
            List<Point4f> pList = new ArrayList<>();

            pList.add(new Point4f(-3f , 0f , -3f , 0));
            pList.add(new Point4f(-3f , 0f , 3f , 0));
            pList.add(new Point4f(-3f , 0.5f , -3f , 0));   //top face
            pList.add(new Point4f(-3f , 0.5f , 3f , 0));    // top face

            pList.add(new Point4f(3f ,  0f , -3f , 0));
            pList.add(new Point4f(3f , 0f , 3f , 0));
            pList.add(new Point4f(3f , 0.5f , -3f , 0));    //top face
            pList.add(new Point4f(3f , 0.5f , 3f , 0));     //top face

            dynamicTexCube = new DynamicTexCube(pList);
            dynamicTexCube.drawSimpleTexCube();
            glPushMatrix();{
                //flagpole
                glTranslatef(0,0,0);
                glRotatef(-90,1,0,0);
                cylinder.drawClosedCylinder(0.3f,20,32);
                //reset the angle
                glRotatef(90f,1,0,0);
                glPushMatrix();{
                    //top sphere
                    sphereTexture.bind();
                    glTranslatef(0,20,0);
                    texSphere.DrawTexSphere(0.5f,32,32,sphereTexture);
                }
                glPopMatrix();
                //back to flagpole
                glPushMatrix();{
                    //flag picture
                    glTranslatef( 0 , 17 , 0);
                    flagTexture.bind();
                    glRotatef(flagRotation , 0 , 1 , 0);
                    //glRotatef(180,0,0,1);

                    pList = new ArrayList<>();
                    pList.add(new Point4f(-0.2f , 0f , -0f , 0));
                    pList.add(new Point4f(-0.2f , 0f , 6f , 0));
                    pList.add(new Point4f(-0.2f , 3f , -0f , 0));   //top face
                    pList.add(new Point4f(-0.2f , 3f , 6f , 0));    // top face

                    pList.add(new Point4f(0.2f ,  0f , -0f , 0));
                    pList.add(new Point4f(0.2f , 0f , 6f , 0));
                    pList.add(new Point4f(0.2f , 3f , -0f , 0));    //top face
                    pList.add(new Point4f(0.2f , 3f , 6f , 0));     //top face

                    dynamicTexCube = new DynamicTexCube(pList);
                    dynamicTexCube.drawSimpleTexCube();
                }
                glPopMatrix();
            }
            glPopMatrix();
        }
        glPopMatrix();

        updateFlagRotation();
        //System.out.println(flagRotation);
    }
    private void updateFlagRotation(){
        if(flagDirection && flagRotation <= 100){
            flagRotation += 0.15f;
        } else if (flagRotation > 100) {
            flagDirection = !flagDirection;
        }
        if(!flagDirection && flagRotation >= 85){
            flagRotation -= 0.15f;
        } else if (flagRotation < 85) {
            flagDirection = !flagDirection;
        }
    }
}
