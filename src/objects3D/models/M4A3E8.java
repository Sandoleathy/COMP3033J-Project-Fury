package objects3D.models;

import objects3D.*;
import objects3D.iFace.AmericanTanks;
import objects3D.iFace.ShermanTank;
import objects3D.models.componments.M4Body;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;
import java.util.List;

public class M4A3E8 implements ShermanTank {
    //init color
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    public M4A3E8(){

    }
    @Override
    public void drawTank(boolean GoodAnimation , List<Texture> tt){
        //init shapes
        M4Body body = new M4Body();
        //draw
        glPushMatrix();{
            glColor3f(white[0], white[1], white[2]);
            body.drawBody();
        }
    }

    @Override
    public AmericanTanks getTypes() {
        return AmericanTanks.M4A3E8;
    }
}
