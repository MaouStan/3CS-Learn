import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

public class Piramid extends Window {
    float angle = 0;

    Piramid() {
        super("Piramid");
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
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -300);
        gl.glRotatef(angle, 0, 1, 0);
        angle = (angle + 1) % 360;

        // Draw base (square)
        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3f(0.5f, 0.5f, 0.5f);
            gl.glVertex3f(-50, 0, -50);
            gl.glVertex3f(50, 0, -50);
            gl.glVertex3f(50, 0, 50);
            gl.glVertex3f(-50, 0, 50);
        gl.glEnd();

        // Draw sides (triangles)
        gl.glBegin(GL2.GL_TRIANGLES);
            // Front face
            gl.glColor3f(1, 0, 0);
            gl.glVertex3f(0, 80, 0);      // apex
            gl.glVertex3f(-50, 0, 50);
            gl.glVertex3f(50, 0, 50);
            // Right face
            gl.glColor3f(0, 1, 0);
            gl.glVertex3f(0, 80, 0);
            gl.glVertex3f(50, 0, 50);
            gl.glVertex3f(50, 0, -50);
            // Back face
            gl.glColor3f(0, 0, 1);
            gl.glVertex3f(0, 80, 0);
            gl.glVertex3f(50, 0, -50);
            gl.glVertex3f(-50, 0, -50);
            // Left face
            gl.glColor3f(1, 1, 0);
            gl.glVertex3f(0, 80, 0);
            gl.glVertex3f(-50, 0, -50);
            gl.glVertex3f(-50, 0, 50);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();
        if (height <= 0) height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        System.exit(0);
    }
}
