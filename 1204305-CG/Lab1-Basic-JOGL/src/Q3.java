import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Q3 extends Window {
  public Q3() {
    super("Quiz 3");
  }

  private void drawStar(GL2 gl, int x, int y, float size) {
    gl.glPointSize(size * 1.5f); // Increased star size
    gl.glBegin(GL2.GL_POINTS);
    gl.glVertex2i(x, y);
    gl.glEnd();
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glLineWidth(6);

    // Background mountains (darkest)
    gl.glColor3f(0.15f, 0.15f, 0.25f);
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex2i(-100, 0); // Left base
    gl.glVertex2i(200, 600); // Peak
    gl.glVertex2i(500, 0); // Right base

    gl.glVertex2i(400, 0); // Left base
    gl.glVertex2i(700, 550); // Peak
    gl.glVertex2i(1000, 0); // Right base
    gl.glEnd();

    // Middle mountain range
    gl.glColor3f(0.2f, 0.2f, 0.3f);
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex2i(-200, 0); // Left base
    gl.glVertex2i(300, 500); // Peak
    gl.glVertex2i(600, 0); // Right base

    gl.glVertex2i(500, 0); // Left base
    gl.glVertex2i(800, 480); // Peak
    gl.glVertex2i(1100, 0); // Right base
    gl.glEnd();

    // Front mountains (lightest)
    gl.glColor3f(0.3f, 0.3f, 0.4f);
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex2i(-300, 0); // Left base
    gl.glVertex2i(200, 450); // Peak
    gl.glVertex2i(500, 0); // Right base

    gl.glVertex2i(400, 0); // Left base
    gl.glVertex2i(700, 420); // Peak
    gl.glVertex2i(1200, 0); // Right base
    gl.glEnd();

    // Background stars
    gl.glColor3f(1, 1, 1);
    for (

        int i = 0; i < 50; i++) {
      drawStar(gl, (int) (Math.random() * 960), (int) (Math.random() * 600),
          (float) (Math.random() * 1.5 + 1.5));
    }

    // Red roof with gradient
    gl.glBegin(GL2.GL_POLYGON);
    gl.glColor3f(0.8f, 0.2f, 0.2f);
    gl.glVertex2i(255, 405);
    gl.glColor3f(1f, 0.4f, 0.4f);
    gl.glVertex2i(360, 255);
    gl.glColor3f(0.8f, 0.2f, 0.2f);
    gl.glVertex2i(465, 405);
    gl.glEnd();

    // Green house front with gradient
    gl.glBegin(GL2.GL_POLYGON);
    gl.glColor3f(0.2f, 0.6f, 0.2f);
    gl.glVertex2i(255, 555);
    gl.glColor3f(0.3f, 0.8f, 0.3f);
    gl.glVertex2i(255, 405);
    gl.glVertex2i(465, 405);
    gl.glColor3f(0.2f, 0.6f, 0.2f);
    gl.glVertex2i(465, 555);
    gl.glEnd();

    // Blue extension with gradient
    gl.glBegin(GL2.GL_POLYGON);
    gl.glColor3f(0.2f, 0.2f, 0.8f);
    gl.glVertex2i(360, 255);
    gl.glColor3f(0.3f, 0.3f, 1f);
    gl.glVertex2i(660, 251);
    gl.glVertex2i(750, 390);
    gl.glColor3f(0.2f, 0.2f, 0.8f);
    gl.glVertex2i(465, 405);
    gl.glEnd();

    // Light purple wall with gradient
    gl.glBegin(GL2.GL_POLYGON);
    gl.glColor3f(0.5f, 0.5f, 0.9f);
    gl.glVertex2i(465, 405);
    gl.glColor3f(0.6f, 0.6f, 1f);
    gl.glVertex2i(750, 390);
    gl.glVertex2i(750, 540);
    gl.glColor3f(0.5f, 0.5f, 0.9f);
    gl.glVertex2i(465, 555);
    gl.glEnd();

    // Main moon
    gl.glPointSize(50f);
    gl.glColor3f(1, 1, 0.5f);
    gl.glBegin(GL2.GL_POINTS);
    gl.glVertex2i(465, 75);
    gl.glEnd();

    // Window decorations
    gl.glColor3f(1f, 1f, 0.7f);
    gl.glBegin(GL2.GL_QUADS);
    gl.glVertex2i(300, 450);
    gl.glVertex2i(360, 450);
    gl.glVertex2i(360, 510);
    gl.glVertex2i(300, 510);
    gl.glEnd();
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    // Cleanup code here
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    System.out.println("init");
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(0, 0, 0, 1);
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(0, 800, 600, 0, 0, 1); // Increased viewport size
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
