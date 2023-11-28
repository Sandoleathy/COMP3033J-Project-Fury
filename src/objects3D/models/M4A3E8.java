package objects3D.models;

import objects3D.iFace.AmericanTanks;
import objects3D.iFace.ShermanTank;
import objects3D.models.componments.M4Body;
import objects3D.models.componments.M4Turret;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;

public class M4A3E8 implements ShermanTank {
    //init color
    static float[] white = { 1.0f, 1.0f, 1.0f, 1.0f };

    public float turretAngle;
    public float pitchAngle;
    public boolean isGunShot;
    public M4Body body;
    public M4Turret turret;
    public boolean drawTurret = true;

    public M4A3E8(){
        turretAngle = 0;
        pitchAngle = 0;
        isGunShot = false;
        body = new M4Body();
        turret = new M4Turret();
    }
    @Override
    public void drawTank(boolean GoodAnimation , List<Texture> tt , float turretAngle , float pitchAngle){


        if(pitchAngle > 10){
            pitchAngle = 10;
            this.pitchAngle = 10;
        }
        else if(pitchAngle <= -10){
            pitchAngle = -10;
            this.pitchAngle = -10;
        }
        if(turretAngle > 360){
            this.turretAngle = 0;
            turretAngle = 0;
        }
        else if(turretAngle < -360){
            this.turretAngle = 0;
            turretAngle = 0;
        }
        //init shapes

        Texture t = tt.get(0);
        t.bind();
        //draw
        glPushMatrix();{
            glColor3f(white[0], white[1], white[2]);
            body.drawBody(tt);

            if(drawTurret){
                glTranslatef(5.7f,3.5f,0);
                glRotatef(turretAngle , 0, 1, 0);
                turret.drawTurret(pitchAngle , tt);
            }
        }
        glPopMatrix();
        if(isGunShot){
            isGunShot = turret.gunShot();
        }
    }

    @Override
    public AmericanTanks getTypes() {
        return AmericanTanks.M4A3E8;
    }

    @Override
    public void shot(){
        //shot gun
        this.isGunShot = true;
    }

    public void ammunitionExplosion(){
        drawTurret = false;
    }
}
