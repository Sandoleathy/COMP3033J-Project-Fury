package objects3D.iFace;

import org.newdawn.slick.opengl.Texture;

import java.util.List;

public interface ShermanTank {
    public void drawTank(boolean GoodAnimation , List<Texture> tt , float turretAngle , float pitchAngle);
    public AmericanTanks getTypes();
    public void shot();
}
