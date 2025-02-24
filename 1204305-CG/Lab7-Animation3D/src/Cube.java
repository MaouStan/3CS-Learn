import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

public class Cube extends Window {

    float angle = -360;

    Cube() {
        super("ThreeD");
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -300);
        if (angle > 360) {
            angle = -360;
        }
        gl.glRotatef(angle, 1f, 1f, 1f); // add rotation for 3D effect
        angle++;

        gl.glPushMatrix();
        // Draw a 3D cube (side length 100) centered at origin
        gl.glBegin(GL2.GL_QUADS);
        // Front Face (z = 50)
        gl.glColor3f(1, 0, 0);
        gl.glVertex3f(-50, -50, 50);
        gl.glVertex3f(50, -50, 50);
        gl.glVertex3f(50, 50, 50);
        gl.glVertex3f(-50, 50, 50);
        // Back Face (z = -50)
        gl.glColor3f(0, 1, 0);
        gl.glVertex3f(-50, -50, -50);
        gl.glVertex3f(-50, 50, -50);
        gl.glVertex3f(50, 50, -50);
        gl.glVertex3f(50, -50, -50);
        // Top Face (y = 50)
        gl.glColor3f(0, 0, 1);
        gl.glVertex3f(-50, 50, -50);
        gl.glVertex3f(-50, 50, 50);
        gl.glVertex3f(50, 50, 50);
        gl.glVertex3f(50, 50, -50);
        // Bottom Face (y = -50)
        gl.glColor3f(1, 1, 0);
        gl.glVertex3f(-50, -50, -50);
        gl.glVertex3f(50, -50, -50);
        gl.glVertex3f(50, -50, 50);
        gl.glVertex3f(-50, -50, 50);
        // Right Face (x = 50)
        gl.glColor3f(1, 0, 1);
        gl.glVertex3f(50, -50, -50);
        gl.glVertex3f(50, 50, -50);
        gl.glVertex3f(50, 50, 50);
        gl.glVertex3f(50, -50, 50);
        // Left Face (x = -50)
        gl.glColor3f(0, 1, 1);
        gl.glVertex3f(-50, -50, -50);
        gl.glVertex3f(-50, -50, 50);
        gl.glVertex3f(-50, 50, 50);
        gl.glVertex3f(-50, 50, -50);
        gl.glEnd();
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
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
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
}
