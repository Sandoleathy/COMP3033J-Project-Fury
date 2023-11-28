package objects3D.models.componments;

import GraphicsObjects.Point4f;
import objects3D.Cylinder;
import objects3D.DynamicTexCube;

import objects3D.TexCube;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class M4Turret {
    static float[] white = { 1.0f, 1.0f, 1.0f, 1.0f };
    static float[] black = { 0.0f, 0.0f, 0.0f, 1.0f };

    private float gunPipeRecoil;
    private int gunShotSection;
    private boolean gunSmoke;
    private List<org.newdawn.slick.opengl.Texture> smokeList;
    public M4Turret(){
        this.gunShotSection = 0;
        this.gunPipeRecoil = 0;
        smokeList = new ArrayList<>();
        gunSmoke = false;
    }
    public void drawTurret(float pitchAngle,List<Texture> tt){
        tt.get(0).bind();
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
                    glRotatef(pitchAngle , 0 ,0 , 1);
                    glTranslatef(-10f + gunPipeRecoil,0.8f,-0f);
                    //gun pipe
                    //pipe smoke
                    if(gunSmoke){
                        glColor4f(white[0], white[1], white[2],1.0f);
                        glPushMatrix();{
                            TexCube particle = new TexCube();
                            glTranslatef(-1f,0,0);
                            //glScalef(0.3f,0.3f,0.3f);
                            Smoke s = new Smoke();
                            s.drawSmoke(smokeList.get(2));
                        }
                        glPopMatrix();
                    }
                    tt.get(0).bind();
                    //pipe
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
    public boolean gunShot(){
        gunSmoke = false;
        //gun shot animation
        if(gunShotSection == 0){
            // render somke
            //pipe recoil
            this.gunPipeRecoil += 0.5f;
            if(gunPipeRecoil >= 2){
                //move to next animation section
                gunShotSection = 1;
            }
            if(gunPipeRecoil <= 2){
                gunSmoke = true;
            }else{
                gunSmoke = false;
            }
            //System.out.println(gunPipeRecoil);
            return true;
        }
        if(gunShotSection == 1){
            this.gunPipeRecoil -= 0.2f;
            if(gunPipeRecoil <= 0){
                gunPipeRecoil = 0;
                gunShotSection = 2;
                return true;
            }
        }
        if(gunShotSection == 2){
            //reset section
            gunShotSection = 0;
            return false;
        }
        return true;
    }
    public void setSmokeList(List<Texture> textures){
        this.smokeList = textures;
    }
}
