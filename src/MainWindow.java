
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import GraphicsObjects.Vector4f;
import objects3D.models.*;
import objects3D.models.animations.Section4FOVChange;
import objects3D.models.componments.M4Turret;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;
import objects3D.TexSphere;
import objects3D.Grid;
import objects3D.Human;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {
	/** textures */
	public static List<Texture> textureList;
	public static List<Texture> smokeTextureList;
	public List<Texture> humanTexture;

	/** flag textures */
	public Texture flagTexture;
	public Texture baseTexture;
	public Texture sphereTexture;

	private boolean MouseOnepressed = true;
	private boolean dragMode = false;
	private boolean BadAnimation = true;
	private boolean Earth = false;
	/** position of camera */
	float cameraX = 4240, cameraY = 3380 , cameraZ = 4660;
	int cameraCenterTo = 0;
	float cameraFOV = 45f;
	float cameraCenterX = 1 , cameraCenterY = 0 , cameraCenterZ = 0;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime; // beginAnimiation

	Arcball MyArcball = new Arcball();

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;
	/** Mouse movement */
	int LastMouseX = -1;
	int LastMouseY = -1;

	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.

	int OrthoNumber = 4096; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
							// // do not change this for assignment 3 but you can change everything for your
							// project

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

	/**tank values */
	M4A3E8 easy8 = new M4A3E8();
	TigerI tigerTank = new TigerI();
	Flag flag = new Flag();

	public float easy8X = 0 , easy8Y = 400 , easy8Z = 0;
	public float easy8Rotation = 90;
	public float tigerX = 27000 , tigerY = 250 , tigerZ = 18000;
	public float tigerRotation = 0;

	public float m4X = 0 , m4Y = 0 , m4Z = 0 ;
	RigidBody rigidBody = new RigidBody(m4X,m4Y,m4Z,new Vector4f(10,90,0,0));

	/** animation class */
	Section4FOVChange animation4 = new Section4FOVChange(45);
	Section4FOVChange animation5 = new Section4FOVChange(180);

	/** */
	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	// support method to aid in converting a java float array into a Floatbuffer
	// which is faster for the opengl layer to process


	/** animation flag */
	public int animationSection = 0;

	/** */
	public void start() {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		/** thread setting */
		//设置守护线程防止主线程关闭后线程继续运行
		engineThread.setDaemon(true);
		tigerShellThread.setDaemon(true);
		environmentSoundThread.setDaemon(true);
		shermanKillTigerThread.setDaemon(true);

		environmentSoundThread.start();

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = (int) getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(60); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		// rotation += 0.01f * delta;

		long currentTime = getCurrentTime();
		deltaTime = calculateDeltaTime(currentTime);

		lastFrameTime = currentTime;

		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPosition = Mouse.getDWheel();

		boolean MouseButtonPressed = Mouse.isButtonDown(0);

		if (MouseButtonPressed && !MouseOnepressed) {
			MouseOnepressed = true;
			// System.out.println("Mouse drag mode");
			MyArcball.startBall(MouseX, MouseY, 1200, 800);
			dragMode = true;

		} else if (!MouseButtonPressed) {
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed = false;
			dragMode = false;
		}

		if (dragMode) {
			MyArcball.updateBall(MouseX, MouseY, 1200, 800);
		}

		if (WheelPosition > 0) {
			OrthoNumber += 10;

		}

		if (WheelPosition < 0) {
			OrthoNumber -= 10;
			if (OrthoNumber < 610) {
				OrthoNumber = 610;
			}

			// System.out.println("Orth nubmer = " + OrthoNumber);

		}

		/** rest key is R */
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			//MyArcball.reset();
			BadAnimation = !BadAnimation;

		/* bad animation can be turn on or off using A key) */

		/** this part is for camera control */
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			//
			cameraX = cameraX -80;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			//x += 0.35f * delta;
			cameraX = cameraX + 80;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			//y += 0.35f * delta;
			cameraZ = cameraZ - 80;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			//y -= 0.35f * delta;
			cameraZ = cameraZ + 80;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			cameraY = cameraY + 80;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
			cameraY = cameraY -80;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_C)){
			if(cameraCenterTo == 1){
				cameraCenterTo = 0;
			}else{
				cameraCenterTo = 1;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			cameraFOV -= 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_X)){
			cameraFOV += 0.5f;
		}
		//System.out.println(x + " " + y + " " + z);
		// print the camera position

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotation += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			Earth = !Earth;
		}

		/**easy8 tank control */
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			easy8.pitchAngle += 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			easy8.pitchAngle -= 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			easy8.turretAngle += (float) 24 /60;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			easy8.turretAngle -= (float) 24 /60;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !easy8.isGunShot){
			easy8.shot();
			System.out.println("On way!!!");
		}
		/** */
		
		if (waitForKeyrelease) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {

				DRAWGRID = !DRAWGRID;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
					waitForKeyrelease = true;
				} else {
					waitForKeyrelease = false;

				}
			}
		}

		/** to check if key is released */
		if (Keyboard.isKeyDown(Keyboard.KEY_G) == false) {
			waitForKeyrelease = true;
		} else {
			waitForKeyrelease = false;
		}

		/*// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;*/

		updateFPS(); // update FPS Counter

		LastMouseX = MouseX;
		LastMouseY = MouseY;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public long getDelta() {
		long time = getTime();
		long delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200, 800);
		glMatrixMode(GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000).put(10000).put(1000).put(100).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000).put(1000f).put(1000).put(500).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();
		FloatBuffer lightPos5 = BufferUtils.createFloatBuffer(4);
		lightPos5.put(-10000).put(10000f).put(10000f).put(10000).flip();

		glLight(GL_LIGHT0, GL_POSITION, lightPos); // specify the
													// position
													// of the
													// light
		// glEnable(GL_LIGHT0); // switch light #0 on // I've setup specific materials
		// so in real light it will look abit strange

		glLight(GL_LIGHT1, GL_POSITION, lightPos2); // specify the
													// position
													// of the
													// light
		glEnable(GL_LIGHT1); // switch light #0 on
		glLight(GL_LIGHT1, GL_DIFFUSE, Utils.ConvertForGL(spot));

		glLight(GL_LIGHT2, GL_POSITION, lightPos3); // specify
													// the
													// position
													// of the
													// light
		glEnable(GL_LIGHT2); // switch light #0 on
		glLight(GL_LIGHT2, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT3, GL_POSITION, lightPos4); // specify
													// the
													// position
													// of the
													// light
		glEnable(GL_LIGHT3); // switch light #0 on
		glLight(GL_LIGHT3, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT4, GL_POSITION , lightPos5);
		glEnable(GL_LIGHT4);
		glLight(GL_LIGHT4, GL_AMBIENT, Utils.ConvertForGL(grey));

		glEnable(GL_LIGHTING); // switch lighting on
		glEnable(GL_DEPTH_TEST); // make sure depth buffer is switched
									// on
		glEnable(GL_NORMALIZE); // normalize normal vectors for safety
		glEnable(GL_COLOR_MATERIAL);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in texture

	}

	public void changeOrth() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
		glMatrixMode(GL_MODELVIEW);

		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		glGetFloat(GL_MODELVIEW_MATRIX, CurrentMatrix);

		// if(MouseOnepressed)
		// {

		MyArcball.getMatrix(CurrentMatrix);
		// }

		glLoadMatrix(CurrentMatrix);

	}

	/*
	 * You can edit this method to add in your own objects / remember to load in
	 * textures in the INIT method as they take time to load
	 * 
	 */
	public void renderGL() {
		changeOrth();

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glColor3f(0.5f, 0.5f, 1.0f);

		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		float posn_y = (float) Math.sin(theta);

		/*
		 * This code draws a grid to help you view the human models movement You may
		 * change this code to move the grid around and change its starting angle as you
		 * please
		 */

		cameraOn();
		changeCamera();
		updateAnimation();



		if (DRAWGRID) {
			glPushMatrix();
			Grid MyGrid = new Grid();
			glTranslatef(600, 400, 0);
			glScalef(200f, 200f, 200f);
			MyGrid.DrawGrid();
			glPopMatrix();
		}
		/** sky box */
		glPushMatrix();{
			glColor3f(white[0], white[1], white[2]);
			glRotatef(90,1,0,0);
			glTranslatef(0,0,0);
			TexSphere skyBox = new TexSphere();
			glScalef(1000f, 1000f, 1000f);

			skyBox.DrawTexSphere(1000,32,32,textureList.get(2));
		}
		glPopMatrix();

		/** grid ground */
		glPushMatrix();{
			glTranslatef(0, 0, 0);
			glScalef(100f, 100f, 100f);
			/*Grid ground = new Grid();
			ground.DrawGrid();*/
			Ground g = new Ground();
			Texture t = textureList.get(1);
			g.drawGround(t);
		}
		glPopMatrix();

		/** flag */
		glPushMatrix();{
			glTranslatef( -1000, 0 ,0);
			glScalef(100f, 100f, 100f);
			flag.drawFlag(flagTexture , sphereTexture , baseTexture);
		}
		glPopMatrix();

		/** easy8 tank */
		glPushMatrix();
		{
			glTranslatef(easy8X, easy8Y, easy8Z);
			glScalef(100f, 100f, 100f);
			glRotatef(easy8Rotation , 0 , 1 , 0);
			if (!BadAnimation && animationSection <= 1) {
				// insert your animation code to correct the postion for the human rotating
				/*glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
				glRotatef(thetaDeg,0.0f,1.0f,0.0f);*/
				if(easy8Z < 15000){
					easy8Z += 20;
				}
				if(easy8Z >= 10000){
					if(animationSection == 0){
						animationSection++;
					}
				}
			} else {

				// bad animation version
				//glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
			}

			easy8.turret.setSmokeList(smokeTextureList);
			easy8.drawTank(!BadAnimation, textureList, easy8.turretAngle, easy8.pitchAngle);

			//MyHuman.drawHuman(delta, !BadAnimation , textureList); // give a delta for the Human object ot be animated
			glPushMatrix();{
				Human tang = new Human();
				glTranslatef(5,2.8f,-1.5f);
				glRotatef(90,0,1,0);
				tang.drawHuman(0,false,humanTexture);
			}
			glPopMatrix();
		}
		glPopMatrix();

		/** other sherman tanks*/
		if(animationSection <= 2){
			M4A3E8 tank3 = new M4A3E8();
			glPushMatrix();{
				M4A3E8 tank1 = new M4A3E8();
				glTranslatef(easy8X, easy8Y, easy8Z - 3000);
				glScalef(100f, 100f, 100f);
				glRotatef(easy8Rotation , 0 , 1 , 0);
				tank1.drawTank(!BadAnimation,textureList,0,0);
			}
			glPopMatrix();
			glPushMatrix();{
				M4A3E8 tank1 = new M4A3E8();
				glTranslatef(easy8X, easy8Y, easy8Z-6000);
				glScalef(100f, 100f, 100f);
				glRotatef(easy8Rotation , 0 , 1 , 0);
				tank1.drawTank(!BadAnimation,textureList,0,0);
			}
			glPopMatrix();
			glPushMatrix();{
				glTranslatef(easy8X, easy8Y, easy8Z - 9000);
				glScalef(100f, 100f, 100f);
				glRotatef(easy8Rotation , 0 , 1 , 0);
				m4X = easy8X;
				m4Y = easy8Y;
				m4Z = easy8Z - 9000;

				//rigidBody keep tracking position of tank3
				/*rigidBody.x = m4X;
				rigidBody.y = m4Y;
				rigidBody.z = m4Z;*/

				if(animationSection == 1){
					tank3.ammunitionExplosion();
					M4Turret turret = new M4Turret();
					glPushMatrix();{

						rigidBody.update(delta);
						glScalef(0.01f, 0.01f, 0.01f);
						glTranslatef(rigidBody.z,rigidBody.y,rigidBody.x);
						glScalef(100f, 100f, 100f);
						glRotatef(rigidBody.rotation,0,1,1);
						turret.drawTurret(0,textureList);
						if(rigidBody.isAnimationFinished){
							//move to next section
							animationSection++;
						}
					}
					glPopMatrix();
				}
				tank3.drawTank(!BadAnimation,textureList,0,0);
				//System.out.println(m4X + " " + m4Y + " " + m4Z);
			}
			glPopMatrix();

		}


		/**tiger tank */
		glPushMatrix();{
			glTranslatef(tigerX, tigerY, tigerZ);
			glScalef(90f, 90f, 90f);
			glRotatef(tigerRotation, 0,1,0);
			tigerTank.drawTank(!BadAnimation, textureList, tigerTank.turretAngle, tigerTank.pitchAngle);

		}
		glPopMatrix();

		/** trees */
		glPushMatrix();{
			Tree t = new Tree();
			glScalef(90f, 90f, 90f);

			// trees cause a lot of performant problems
			/*for(float x=300;x<=600;x+=60){
				for(float y=0;y<=600;y+=60){
					glPushMatrix();{
						glTranslatef(x,0,y);
						t.drawTree(textureList);
					}
					glPopMatrix();
				}
			}*/

		}
		glPopMatrix();
		/*
		 * This code puts the earth code in which is larger than the human so it appears
		 * to change the scene
		 */
		if (Earth) {
			// Globe in the centre of the scene
			glPushMatrix();
			TexSphere MyGlobe = new TexSphere();
			// TexCube MyGlobe = new TexCube();
			glTranslatef(500, 500, 500);
			glScalef(140f, 140f, 140f);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

			Color.white.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

			//MyGlobe.DrawTexSphere(8f, 100, 100, texture);
			// MyGlobe.DrawTexCube();
			glPopMatrix();
		}

	}

	public static void main(String[] argv) {

		MainWindow hello = new MainWindow();

		/*AudioPlayer test = new AudioPlayer("test.wav" , AudioPlayer.DEFAULT_MODE);
		Thread testAudio = new Thread(test);
		testAudio.start();
		try{
			Thread.sleep(5000);
		}catch (Exception e){
			e.printStackTrace();
		}
		test.stop();*/

		hello.start();

	}


	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	public void init() throws IOException {

		textureList = new ArrayList<>();
		smokeTextureList = new ArrayList<>();
		humanTexture = new ArrayList<>();
		Texture t;
		try{
			t = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/green_metal.jpg"));
			textureList.add(t);
			t = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/grass_ground.jpg"));
			textureList.add(t);
			t = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/sky.jpg"));
			textureList.add(t);
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/track.png"));
			textureList.add(t);
			t = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/america_staff.jpg"));
			textureList.add(t);
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/armor_front.png"));//5
			textureList.add(t);
			t = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/tiger_paint.jpg"));
			textureList.add(t);
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/tiger_track.png"));//7
			textureList.add(t);

		}catch (Exception e){
			e.printStackTrace();
		}

		try{
			smokeTextureList.add(TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("res/smoke1.png")));
			smokeTextureList.add(TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("res/smoke2.png")));
			smokeTextureList.add(TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("res/fire_flash.png")));
		}catch (Exception e){
			e.printStackTrace();
		}
		try{
			humanTexture.add(TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("res/camouflage.png")));
			humanTexture.add(TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("res/tang_face.png")));

		}catch (Exception e){
			e.printStackTrace();
		}
		try{
			flagTexture = TextureLoader.getTexture("PNG" , ResourceLoader.getResourceAsStream("res/Project_Texture_2023.png"));
			sphereTexture = TextureLoader.getTexture("JPG" , ResourceLoader.getResourceAsStream("res/iron.jpg"));
			baseTexture = TextureLoader.getTexture("JPG" , ResourceLoader.getResourceAsStream("res/marble.jpg"));
		}catch (Exception e){
			e.printStackTrace();
		}

		//texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
		System.out.println("Texture loaded okay ");
	}

	public void changeCamera(){
		GL11.glLoadIdentity();

		if(BadAnimation){
			if(cameraCenterTo == 0){
				cameraCenterX = easy8X;
				cameraCenterY = easy8Y;
				cameraCenterZ = easy8Z;
			} else if (cameraCenterTo == 1) {
				cameraCenterX = tigerX;
				cameraCenterY = tigerY;
				cameraCenterZ = tigerZ;
			}
		}


		if(animationSection <= 1 && !BadAnimation){
			cameraX = easy8X;
			cameraY = easy8Y + 500;
			cameraZ = easy8Z + 300;
			cameraCenterX = -cameraX;
			cameraCenterY = cameraY;
			cameraCenterZ = cameraZ - 1;
		}

		GLU.gluLookAt(-cameraX, cameraY, cameraZ,
				cameraCenterX, cameraCenterY, cameraCenterZ,
				0, 1, 0);
	}
	public void cameraOn() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(cameraFOV, 1.5f, 2.8f, 2000000);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		//System.out.println(cameraFOV);
	}


	private long getCurrentTime() {
		return System.nanoTime() / 1_000_000; // 转换为毫秒
	}

	private float calculateDeltaTime(long currentTime) {
		return (currentTime - lastFrameTime) / 1000.0f; // 转换为秒
	}



	private long lastFrameTime;
	private float deltaTime;
	AudioPlayer environmentSound = new AudioPlayer("environment.wav" , AudioPlayer.LOOP_MODE);
	Thread environmentSoundThread = new Thread(environmentSound);
	AudioPlayer tankEngine = new AudioPlayer("tank_engine_1.wav",AudioPlayer.LOOP_MODE);
	Thread engineThread = new Thread(tankEngine);
	AudioPlayer tigerShell = new AudioPlayer("tiger_shell_coming.wav" , AudioPlayer.DEFAULT_MODE);
	Thread tigerShellThread = new Thread(tigerShell);
	AudioPlayer shermanKillTiger = new AudioPlayer("sherman_kill_tiger.wav" , AudioPlayer.DEFAULT_MODE);
	Thread shermanKillTigerThread = new Thread(shermanKillTiger);
	public void updateAnimation(){
		if(!BadAnimation && animationSection == 0){
			if(!tankEngine.isPlayed){
				engineThread.start();
			}
			//System.out.println(easy8X + " " + easy8Y + " " + easy8Z);
			if(easy8Z >= 9000 && !tigerShell.isPlayed){
				tigerShellThread.start();
			}
		}
		if(animationSection == 2){
			//stop the audio and thread
			tankEngine.stop();
			tigerShell.stop();

			animation4.update();
			this.cameraFOV = animation4.FOV;
			if(animation4.isAnimationFinished){
				animationSection++;
			}
		}
		if(animationSection == 3){
			animation5.updateReverse();
			this.cameraFOV = animation5.FOV;
			if(animation5.isAnimationFinished){
				animationSection++;
			}
			cameraX = -(easy8X-1000);
			cameraY = easy8Y+1000;
			cameraZ = easy8Z-1000;
			cameraCenterX = tigerX;
			cameraCenterY = tigerY;
			cameraCenterZ = tigerZ;
			//change tank position
			if(animation5.FOV >= 160){
				easy8X = 0;
				easy8.turretAngle = -34;
				easy8Z = 0;
				easy8Rotation = 180;

				tigerX = 2000;
				tigerZ = 2000;
				tigerTank.turretAngle = -40;
				tigerTank.pitchAngle = 0;
			}
		}
		//System.out.println(easy8.turretAngle);
		if(animationSection == 3 || animationSection == 4){
			tankEngine.start();

			if(animationSection == 4){
				cameraX = -(easy8X-1000);
				cameraY = easy8Y+1000;
				cameraZ = easy8Z-1000;

				cameraCenterX = tigerX;
				cameraCenterY = tigerY;
				cameraCenterZ = tigerZ;
			}
			easy8X += 15;
			easy8.turretAngle -= 0.18f;
			if(easy8X >= 2600 && easy8X <= 2700 && !easy8.isGunShot){
				easy8.shot();
			}

			tigerTank.turretAngle -= 0.1f;
			tigerX += 5;
			if(easy8X >= 4000){
				animationSection++;
			}
		}
		if(animationSection == 5){
			cameraX = -(tigerX - 1000);
			cameraY = tigerY + 1000;
			cameraZ = tigerZ + 1000;

			cameraCenterX = easy8X;
			cameraCenterY = easy8Y;
			cameraCenterZ = easy8Z;

			tigerX += 5;
			tigerTank.turretAngle -= 0.1f;
			//System.out.println(easy8X);
			easy8X += 15;
			easy8.turretAngle -= 0.18f;
			if(tigerTank.turretAngle < -74 && tigerTank.turretAngle >= -75 && !tigerTank.isGunShot){
				tigerTank.shot();
			}
			if(easy8X >= 8000){
				animationSection++;
			}
		}
		if(animationSection == 6){
			tankEngine.stop();

			easy8Rotation = 90;
			easy8.turretAngle = -120;
			easy8X = 10000;
			easy8Z = 4000;

			tigerTank.turretAngle = -140;

			cameraCenterX = easy8X;
			cameraCenterY = easy8Y;
			cameraCenterZ = easy8Z;

			cameraX = -(easy8X) + 1000;
			cameraY = easy8Y + 8000;
			cameraZ = easy8Z + 1000;

			animationSection++;
		}
		if(animationSection == 7){
			timeCounter = timeCounter + deltaTime;
			//System.out.println(timeCounter);
			if(!shermanKillTiger.isPlayed){
				shermanKillTigerThread.start();
			}
			if(timeCounter < 7.7){
				easy8Z += 2.5f;
			}
			if(timeCounter > 7.7){
				easy8Z -= 2f;
				easy8.turretAngle += 0.03f;
			}
			if(timeCounter >= 25.1 && timeCounter <= 25.2 && !easy8.isGunShot){
				easy8.shot();
			}
			if(timeCounter >= 33.5 && timeCounter <= 33.6 && !easy8.isGunShot){
				easy8.shot();
			}
			if(timeCounter < 25.1){
				tigerTank.turretAngle -= 0.02f;
			}
			if(timeCounter < 33.5 && timeCounter > 25.1){
				tigerTank.turretAngle -= 0.01f;
			}
			//System.out.println(easy8Z);
			if(timeCounter >= 41){
				animationSection++;
			}
		}
	}
	public float timeCounter = 0;
}
