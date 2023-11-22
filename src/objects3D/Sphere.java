package objects3D;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Sphere {
	private float incphi;
	private float inctheta;
	public Sphere() {

	}

	// Implement using notes and examine Tetrahedron to aid in the coding look at
	// lecture 7 , 7b and 8
	// 7b should be your primary source, we will cover more about circles in later
	// lectures to understand why the code works
	public void drawSphere(float radius, float nSlices, float nSegments) {
		float rPhi , rPhi1;
		float theta1 , phi1;
		//four group of x,y,z
		//each group is a point of one plane of the sphere
		float x,y,z;
		float x1,y1,z1;
		float x2,y2,z2;
		float x3,y3,z3;
		inctheta = (float)((2 * Math.PI)/nSlices);
		incphi = (float)Math.PI/nSegments;
		GL11.glBegin(GL11.GL_TRIANGLES);
		//loop through the sphere
		for(float theta=(float)-Math.PI; theta<Math.PI; theta+=inctheta){
			for(float phi = (float)-(Math.PI/2); phi<(Math.PI/2); phi += incphi){
				theta1 = theta + inctheta;	//like nextTheta
				phi1 = phi + incphi;		//nextPhi
				rPhi = (float)(radius * Math.cos(phi));
				rPhi1 = (float)(radius * Math.cos(phi1));

				x = (float) (rPhi * Math.cos(theta));
				y = (float) (rPhi * Math.sin(theta));
				z = (float) (radius * Math.sin(phi));

				x1 = (float) (rPhi1 * Math.cos(theta));
				y1 = (float) (rPhi1 * Math.sin(theta));
				z1 = (float) (radius * Math.sin(phi1));

				x2 = (float) (rPhi * Math.cos(theta1));
				y2 = (float) (rPhi * Math.sin(theta1));
				z2 = (float) (radius * Math.sin(phi));

				x3 = (float) (rPhi1 * Math.cos(theta1));
				y3 = (float) (rPhi1 * Math.sin(theta1));
				z3 = (float) (radius * Math.sin(phi1));

				GL11.glNormal3f(x,y,z);
				GL11.glNormal3f(x1,y1,z1);
				GL11.glNormal3f(x2,y2,z2);
				GL11.glNormal3f(x3,y3,z3);
				//draw two triangles to fill the rectangle plane
				GL11.glVertex3f(x,y,z);
				GL11.glVertex3f(x1,y1,z1);
				GL11.glVertex3f(x2,y2,z2);

				GL11.glVertex3f(x3,y3,z3);
				GL11.glVertex3f(x1,y1,z1);
				GL11.glVertex3f(x2,y2,z2);

			}
		}
		GL11.glEnd();
	}
}
