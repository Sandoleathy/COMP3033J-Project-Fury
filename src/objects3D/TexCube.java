package objects3D;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {
	public static int createCount = 0;
	Point4f[] vertices;
	int[][] faces;
	public TexCube() {
		if(createCount == 0){
			System.out.println("Hi I am a Cube. I have 6 faces :)");
			createCount++;
		}
		vertices = new Point4f[]{
				new Point4f(-1,-1,-1,0),	//0
				new Point4f(-1,-1,1,0),	//1
				new Point4f(-1, 1, -1,0),	//2
				new Point4f(-1, 1, 1,0),	//3
				new Point4f(1, -1, -1,0),	//4
				new Point4f(1, -1, 1,0),	//5
				new Point4f(1, 1, -1,0),	//6
				new Point4f(1, 1, 1,0),	//7
		};
		faces = new int[][]{
				{0, 1, 3},
				{0, 3, 2},
				{0, 2, 4},
				{2, 6, 4},
				{0, 1, 4},
				{1, 4, 5},
				{1, 5, 7},
				{1, 7, 3},
				{5, 4, 6},
				{5, 6, 7},
				{2, 3, 7},
				{2, 7, 6}
		};
	}

	// Implement using notes and looking at TexSphere
	public void drawTexCube() {
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(int[] currentFace : faces){
			Vector4f v = vertices[currentFace[1]].MinusPoint(vertices[currentFace[0]]);
			Vector4f w = vertices[currentFace[2]].MinusPoint(vertices[currentFace[0]]);
			Vector4f normal = v.cross(w).Normal();
			//light!!!!!
			GL11.glNormal3f(normal.x, normal.y, normal.z);

			GL11.glTexCoord2f(0,0);

			GL11.glVertex3f(vertices[currentFace[0]].x, vertices[currentFace[0]].y, vertices[currentFace[0]].z);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex3f(vertices[currentFace[1]].x, vertices[currentFace[1]].y, vertices[currentFace[1]].z);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex3f(vertices[currentFace[2]].x, vertices[currentFace[2]].y, vertices[currentFace[2]].z);
			GL11.glTexCoord2f(0,1);

			/*GL11.glVertex3f(vertices[currentFace[0]].x, vertices[currentFace[0]].y, vertices[currentFace[0]].z);
			GL11.glVertex3f(vertices[currentFace[3]].x, vertices[currentFace[3]].y, vertices[currentFace[3]].z);
			GL11.glVertex3f(vertices[currentFace[2]].x, vertices[currentFace[2]].y, vertices[currentFace[2]].z);*/
		}
		GL11.glEnd();
	}

}

/*
 * 
 * 
 * }
 * 
 */
