package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void drawCylinder(float radius, float height, int nSegments ) 
	{
		GL11.glBegin(GL11.GL_TRIANGLES);
		float angle , nextAngle;
		float x1,x2,y1,y2;
		//loop through each slices
		for(float i = 0 ; i < nSegments; i += 1.0f){
			angle = (float) (Math.PI * i * 2.0 / nSegments);
			nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);
			/* compute sin & cosine */
			x1 = (float)Math.sin(angle) * radius;
			x2 = (float)Math.sin(nextAngle) * radius;
			y1 = (float)Math.cos(angle) * radius;
			y2 = (float)Math.cos(nextAngle) * radius;
			/* draw top triangle */
			GL11.glNormal3f(x1,y1,0);
			GL11.glNormal3f(x2,y2,0);
			GL11.glNormal3f(x1,y1,0);
			GL11.glVertex3f(x1,y1,0);
			GL11.glVertex3f(x2,y2,height);
			GL11.glVertex3f(x1,y1,height);
			/* draw bottom triangle */
			GL11.glNormal3f(x1,y1,0);
			GL11.glNormal3f(x2,y2,0);
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x1,y1,0);
			GL11.glVertex3f(x2,y2,0);
			GL11.glVertex3f(x2,y2,height);
		}
		GL11.glEnd();
	}
}
