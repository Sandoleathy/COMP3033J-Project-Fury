package objects3D.models;

import objects3D.iFace.AmericanTanks;
import objects3D.iFace.ShermanTank;
import objects3D.models.componments.TigerBody;
import objects3D.models.componments.TigerTurret;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class TigerI implements ShermanTank {
    public float turretAngle;
    public float pitchAngle;
    public boolean isGunShot;
    TigerBody body;
    TigerTurret turret;

    public TigerI(){
        turretAngle = 0;
        pitchAngle = 0;
        isGunShot = false;
        body = new TigerBody();
        turret = new TigerTurret();

    }
    @Override
    public void drawTank(boolean GoodAnimation, List<Texture> tt, float turretAngle, float pitchAngle) {
        // limit pitch and turret
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

        glPushMatrix();{
            tt.get(6).bind();
            glTranslatef(0,0,0);
            body.drawBody(tt);
            glPushMatrix();{
                glTranslatef(9,6.5f,6);
                glRotatef(turretAngle , 0, 1, 0);
                turret.drawTurret(pitchAngle , tt);
            }
            glPopMatrix();
        }
        glPopMatrix();
        if(isGunShot){
            isGunShot = turret.gunShot();
        }
    }

    @Override
    public AmericanTanks getTypes() {
        return null;
    }

    @Override
    public void shot() {
        this.isGunShot = true;
    }
}
