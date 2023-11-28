package objects3D;

import GraphicsObjects.Utils;
import org.newdawn.slick.opengl.Texture;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
public class Human {

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
	public Human() {

	}

	// Implement using notes in Animation lecture
	public void drawHuman(float delta, boolean GoodAnimation , List<Texture> tt){
		float theta = (float) (delta * 2 * Math.PI);
		float LimbRotation;
		float legRotation;
		if (GoodAnimation) {
			LimbRotation = (float) Math.cos(theta) * 45;
			legRotation = (float) Math.sin(theta) * 45;
		} else {
			LimbRotation = 0;
			legRotation = 0;
		}

		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();
		TexSphere texSphere = new TexSphere();
		Texture texture;

		glPushMatrix();

		{
			glColor3f(white[0], white[1], white[2]);
			texture = tt.get(0);
			texture.bind();
			glTranslatef(0.0f, 0.5f, 0.0f);
			texSphere.DrawTexSphere(0.5f, 32, 32,texture);
			//sphere.drawSphere(0.5f, 32, 32);

			// chest
			glColor3f(white[0], white[1], white[2]);
			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
			glPushMatrix();
			{
				glTranslatef(0.0f, 0.5f, 0.0f);
				//texture = tt.get(1);
				//texture.bind();

				//sphere.drawSphere(0.5f, 32, 32);
				texSphere.DrawTexSphere(0.5f,32,32,texture);
				// neck
				glColor3f(orange[0], orange[1], orange[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
				glPushMatrix();
				{
					glTranslatef(0.0f, 0.0f, 0.0f);
					glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
					// glRotatef(45.0f,0.0f,1.0f,0.0f);
					cylinder.drawCylinder(0.15f, 0.7f, 32);

					// head
					glColor3f(white[0], white[1], white[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					glPushMatrix();
					{

						glTranslatef(0.0f, 0.0f, 1.0f);

						//sphere.drawSphere(0.5f, 32, 32);
						texSphere.DrawTexSphere(0.5f,32,32,texture);

						glTranslatef(0.2f, 0.4f, 0.0f);
						glColor3f(black[0], black[1], black[2]);
						sphere.drawSphere(0.1f, 32, 32);
						glTranslatef(-0.4f, 0.0f, 0.0f);
						sphere.drawSphere(0.1f, 32, 32);



						glPopMatrix();
					}
					glPopMatrix();

					// left shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					glPushMatrix();
					{
						glTranslatef(0.5f, 0.4f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);

						// left arm
						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

							glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
							 glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// left elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.75f);
								sphere.drawSphere(0.2f, 32, 32);

								// left forearm
								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									// glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// left hand
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);

									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
					// to chest
					// now we are in chest level
					// right shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						//push into right arm
						glTranslatef(-0.5f, 0.4f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);

						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						glPushMatrix();
						{
							//push into right arm
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

							glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
							 glRotatef(-27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								//push into below
								glTranslatef(0.0f, 0.0f, 0.75f);
								sphere.drawSphere(0.2f, 32, 32);

								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								glPushMatrix();
								{
									//push into forarm
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// left hand
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);
									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();
				// now we are in chest
				// pelvis

				// left hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					glTranslatef(-0.5f, -0.2f, 0.0f);

					sphere.drawSphere(0.25f, 32, 32);

					// left high leg
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

						glRotatef((-LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						// left knee
						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							sphere.drawSphere(0.25f, 32, 32);

							// left low leg
							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								 glRotatef(-120.0f,1.0f,0.0f,0.0f);
								  glRotatef((-legRotation / 2) + 90,1.0f,0.0f,0.0f);
								 //System.out.println(legRotation);
								 cylinder.drawCylinder(0.15f, 0.7f, 32);

								// left foot
								glColor3f(blue[0], blue[1], blue[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.drawSphere(0.3f, 32, 32);

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();
				// now we are in chest node
				// pelvis
				// right hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					glTranslatef(0.5f, -0.2f, 0.0f);

					sphere.drawSphere(0.25f, 32, 32);

					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						//push into high leg
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(0.0f, 0.0f, 0.0f, 0.0f);

						glRotatef((LimbRotation / 2) + 90, 1.0f, 0.0f, 0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							//push into knee
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							sphere.drawSphere(0.25f, 32, 32);

							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								//push into low leg
								glTranslatef(0.0f, 0.0f, 0.0f);
								 glRotatef(-120.0f,1.0f,0.0f,0.0f);
								 glRotatef((-legRotation / 2) + 90,1.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// left foot
								glColor3f(blue[0], blue[1], blue[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									//push into foot
									glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.drawSphere(0.3f, 32, 32);
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();
			}
			glPopMatrix();

		}

	}

}

/*
 * 
 * 
 * }
 * 
 */
