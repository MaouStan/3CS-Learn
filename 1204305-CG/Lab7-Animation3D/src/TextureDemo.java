import java.io.File;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class TextureDemo extends Window implements KeyListener {
    private int texture;
    private float smallCubeOffsetX = 0.0f;
    private float smallCubeOffsetY = 0.0f;
    private float smallCubeOffsetZ = 0.0f;

    TextureDemo() {
        super("Texture Demo");

        // add KeyListener
        getWindow().addKeyListener(this);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glCullFace(GL2.GL_FRONT);
        cubetexture(drawable);

        gl.glPushMatrix();
        gl.glTranslatef(smallCubeOffsetX, smallCubeOffsetY - 0.5f, smallCubeOffsetZ);
        gl.glDisable(GL2.GL_CULL_FACE);
        drawStickMan(drawable);
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glPopMatrix();
    }

    void cubetexture(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        // Position camera inside the cube
        gl.glTranslatef(0.0f, 0.0f, -2.0f); // Center of the cube
        gl.glRotatef(180, 0.0f, 1.0f, 0.0f); // Rotate to look at a wall
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        cubeshape(drawable, 0.0f, 0.0f, 0.0f, 1.0f);
    }

    void cubeshape(GLAutoDrawable drawable, float posX, float posY, float posZ, float size) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (-1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (-1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (1.0f * size), posZ + (1.0f * size));
        // Back Face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (-1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (-1.0f * size), posZ + (-1.0f * size));
        // Top Face
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (1.0f * size), posZ + (-1.0f * size));
        // Bottom Face
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (-1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (-1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (-1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (-1.0f * size), posZ + (1.0f * size));
        // Right Face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (-1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(posX + (1.0f * size), posY + (-1.0f * size), posZ + (1.0f * size));
        // Left Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (-1.0f * size), posZ + (-1.0f * size));
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (-1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (1.0f * size), posZ + (1.0f * size));
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(posX + (-1.0f * size), posY + (1.0f * size), posZ + (-1.0f * size));
        gl.glEnd();
    }

    void drawSmallCube(GLAutoDrawable drawable, float posX, float posY, float posZ, float size) {
        cubeshape(drawable, posX, posY, posZ, size);
    }

    void drawColoredSmallCube(GLAutoDrawable drawable, float posX, float posY, float posZ, float size) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_QUADS);
        // Front face: red
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(posX - size, posY - size, posZ + size);
        gl.glVertex3f(posX + size, posY - size, posZ + size);
        gl.glVertex3f(posX + size, posY + size, posZ + size);
        gl.glVertex3f(posX - size, posY + size, posZ + size);
        // Back face: green
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(posX - size, posY - size, posZ - size);
        gl.glVertex3f(posX - size, posY + size, posZ - size);
        gl.glVertex3f(posX + size, posY + size, posZ - size);
        gl.glVertex3f(posX + size, posY - size, posZ - size);
        // Top face: blue
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(posX - size, posY + size, posZ - size);
        gl.glVertex3f(posX - size, posY + size, posZ + size);
        gl.glVertex3f(posX + size, posY + size, posZ + size);
        gl.glVertex3f(posX + size, posY + size, posZ - size);
        // Bottom face: yellow
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(posX - size, posY - size, posZ - size);
        gl.glVertex3f(posX + size, posY - size, posZ - size);
        gl.glVertex3f(posX + size, posY - size, posZ + size);
        gl.glVertex3f(posX - size, posY - size, posZ + size);
        // Right face: magenta
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(posX + size, posY - size, posZ - size);
        gl.glVertex3f(posX + size, posY + size, posZ - size);
        gl.glVertex3f(posX + size, posY + size, posZ + size);
        gl.glVertex3f(posX + size, posY - size, posZ + size);
        // Left face: cyan
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(posX - size, posY - size, posZ - size);
        gl.glVertex3f(posX - size, posY - size, posZ + size);
        gl.glVertex3f(posX - size, posY + size, posZ + size);
        gl.glVertex3f(posX - size, posY + size, posZ - size);
        gl.glEnd();
    }

    void drawUniformCube(GLAutoDrawable drawable, float posX, float posY, float posZ, float size,
            float r, float g, float b) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glColor3f(r, g, b);
        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glVertex3f(posX - size, posY - size, posZ + size);
        gl.glVertex3f(posX + size, posY - size, posZ + size);
        gl.glVertex3f(posX + size, posY + size, posZ + size);
        gl.glVertex3f(posX - size, posY + size, posZ + size);
        // Back Face
        gl.glVertex3f(posX - size, posY - size, posZ - size);
        gl.glVertex3f(posX - size, posY + size, posZ - size);
        gl.glVertex3f(posX + size, posY + size, posZ - size);
        gl.glVertex3f(posX + size, posY - size, posZ - size);
        // Top Face
        gl.glVertex3f(posX - size, posY + size, posZ - size);
        gl.glVertex3f(posX - size, posY + size, posZ + size);
        gl.glVertex3f(posX + size, posY + size, posZ + size);
        gl.glVertex3f(posX + size, posY + size, posZ - size);
        // Bottom Face
        gl.glVertex3f(posX - size, posY - size, posZ - size);
        gl.glVertex3f(posX + size, posY - size, posZ - size);
        gl.glVertex3f(posX + size, posY - size, posZ + size);
        gl.glVertex3f(posX - size, posY - size, posZ + size);
        // Right Face
        gl.glVertex3f(posX + size, posY - size, posZ - size);
        gl.glVertex3f(posX + size, posY + size, posZ - size);
        gl.glVertex3f(posX + size, posY + size, posZ + size);
        gl.glVertex3f(posX + size, posY - size, posZ + size);
        // Left Face
        gl.glVertex3f(posX - size, posY - size, posZ - size);
        gl.glVertex3f(posX - size, posY - size, posZ + size);
        gl.glVertex3f(posX - size, posY + size, posZ + size);
        gl.glVertex3f(posX - size, posY + size, posZ - size);
        gl.glEnd();
    }

    void drawStickMan(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        // Head (red)
        gl.glPushMatrix(); // push head transform
        gl.glTranslatef(0.0f, 0.35f, 0.0f); // head center
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.1f, 1.0f, 0.0f, 0.0f);
        // Left Eye (white)
        gl.glPushMatrix();
        gl.glTranslatef(-0.06f, 0.02f, -0.11f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.03f, 1.0f, 1.0f, 1.0f);
        gl.glPopMatrix();
        // Right Eye (white)
        gl.glPushMatrix();
        gl.glTranslatef(0.06f, 0.02f, -0.11f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.03f, 1.0f, 1.0f, 1.0f);
        gl.glPopMatrix();
        // Mouth (white)
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.07f, -0.11f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.03f, 1.0f, 1.0f, 1.0f);
        gl.glPopMatrix();
        gl.glPopMatrix();
        // Torso (green)
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.15f, 0.0f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.075f, 0.0f, 1.0f, 0.0f);
        gl.glPopMatrix();
        // Left Arm (blue)
        gl.glPushMatrix();
        gl.glTranslatef(-0.1f, 0.15f, 0.0f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 1.0f);
        gl.glPopMatrix();
        // Right Arm (magenta)
        gl.glPushMatrix();
        gl.glTranslatef(0.1f, 0.15f, 0.0f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.05f, 1.0f, 0.0f, 1.0f);
        gl.glPopMatrix();
        // Left Leg (yellow)
        gl.glPushMatrix();
        gl.glTranslatef(-0.06f, -0f, 0.0f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.05f, 1.0f, 1.0f, 0.0f);
        gl.glPopMatrix();
        // Right Leg (cyan)
        gl.glPushMatrix();
        gl.glTranslatef(0.06f, -0f, 0.0f);
        drawUniformCube(drawable, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f, 1.0f, 1.0f);
        gl.glPopMatrix();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);
        // Setup 3D settings
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        // Draw in wireframe mode instead of filled
        // gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);

        // enable texture
        gl.glEnable(GL2.GL_TEXTURE_2D);
        // open file image texture
        try {
            // /assets/images/gound.jpg
            File file = new File("assets\\images\\ground.jpg");
            if (!file.exists()) {
                System.err.println("Texture file not found: " + file.getAbsolutePath());
            }
            Texture t = TextureIO.newTexture(file, true);
            texture = t.getTextureObject(gl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glCullFace(GL2.GL_FRONT);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        System.exit(0);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();
        if (height <= 0)
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // Perspective view
        // (field of view, aspect ratio, near plane, far plane)
        glu.gluPerspective(45.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    // --- KeyListener methods ---
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            smallCubeOffsetX += 0.01f;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            smallCubeOffsetX -= 0.01f;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            smallCubeOffsetY += 0.01f;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            smallCubeOffsetY -= 0.01f;
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            smallCubeOffsetZ += 0.01f;
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            smallCubeOffsetZ -= 0.01f;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
