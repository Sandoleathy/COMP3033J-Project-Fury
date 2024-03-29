package objects3D.models.componments;

import GraphicsObjects.Point4f;
import objects3D.Cylinder;
import objects3D.DynamicTexCube;
import objects3D.TexSphere;
import objects3D.models.AudioPlayer;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class TigerTurret {
    static float[] white = { 1.0f, 1.0f, 1.0f, 1.0f };
    private float gunPipeRecoil;
    private int gunShotSection;
    AudioPlayer gunSound;
    Thread gunThread;
    public TigerTurret(){
        this.gunShotSection = 0;
        this.gunPipeRecoil = 0;
        gunSound = new AudioPlayer("tiger_shot.wav" , AudioPlayer.DEFAULT_MODE);
        gunThread = new Thread(gunSound);
        gunThread.setDaemon(true);
    }
    public void drawTurret(float pitchAngle , List<Texture> tt){
        tt.get(6).bind();
        glColor3f(white[0], white[1], white[2]);

        glTranslatef(0.0f, 0.0f, 0.0f);
        float radius = 4.5f;
        DynamicTexCube texCube;
        Cylinder cylinder = new Cylinder();
        List<Point4f> pList = new ArrayList<>();
        glPushMatrix();{
            //turret
            glRotatef(90,1,0,0);
            cylinder.drawClosedCylinder(radius,5.0f,180);

            glPushMatrix();{
                //commander's cupola
                glTranslatef(0,2,-0.5f);
                cylinder.drawClosedCylinder(2f,0.5f,180);
            }
            glPopMatrix();
            glPushMatrix();{
                glRotatef(-90,1,0,0);
                glTranslatef(-6f,-1.05f,0);
                //gun Mantlet
                pList.add(new Point4f(-0,-1.5f,-3f,0)); //0
                pList.add(new Point4f(-0,-1.5f,3f,0)); //1
                pList.add(new Point4f(-0,0.5f,-3f,0)); //2
                pList.add(new Point4f(-0,0.5f,3f,0)); //3

                pList.add(new Point4f(5f,-2f,-4.4f,0)); //4
                pList.add(new Point4f(5f,-2f,4.4f,0)); //5
                pList.add(new Point4f(5f,1f,-4.4f,0)); //6
                pList.add(new Point4f(5f,1f,4.4f,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();

                glPushMatrix();{
                    //gun pipe
                    glRotatef(pitchAngle , 0 ,0 , 1);
                    //change gun pitch
                    glTranslatef(1f + gunPipeRecoil,-0.5f,0);
                    glRotatef(-90,0,1,0);
                    cylinder.drawCylinder(0.5f,16f,180);
                }
                glPopMatrix();
            }
            glPopMatrix();
            //back to turret
            glPushMatrix();{
                glRotatef(-90,1,0,0);
                glTranslatef(3f,-1.05f,0);
                //head back staff
                pList = new ArrayList<>();
                pList.add(new Point4f(-0,-1f,-3f,0)); //0
                pList.add(new Point4f(-0,-1f,3f,0)); //1
                pList.add(new Point4f(-0,1f,-3f,0)); //2
                pList.add(new Point4f(-0,1f,3f,0)); //3

                pList.add(new Point4f(3f,-1f,-2f,0)); //4
                pList.add(new Point4f(3f,-1f,2f,0)); //5
                pList.add(new Point4f(3f,1f,-2f,0)); //6
                pList.add(new Point4f(3f,1f,2f,0)); //7

                texCube = new DynamicTexCube(pList);
                texCube.drawTexCube();
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    public boolean gunShot() {
        //gun shot animation
        if(gunShotSection == 0){
            //play sound
            if(!gunSound.isPlayed){
                //System.out.println(1111111);
                if(gunThread.getState() == Thread.State.NEW){
                    //first time play start thread
                    gunThread.start();
                }
                else {
                    gunSound.reset();
                    gunSound.start();
                }
            }

            // render somke

            //pipe recoil
            this.gunPipeRecoil += 0.5f;
            if(gunPipeRecoil >= 2){
                //move to next animation section
                gunShotSection = 1;
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
            gunSound.isPlayed = false;
            return false;
        }
        return true;
    }
}
