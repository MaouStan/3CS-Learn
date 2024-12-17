import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Q2 extends Window {
  public Q2() {
    super("Quiz 2");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glColor3f(1, 1, 0); // Yellow color
    gl.glLineWidth(4);

    // House With Loop
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2i(100, 200); // Start from bottom-left
    gl.glVertex2i(100, 100); // Up to top-left
    gl.glVertex2i(150, 50); // roof peak
    gl.glVertex2i(200, 100); // top-right
    gl.glVertex2i(200, 200); // bottom-right (Loop auto back to start)
    gl.glEnd();

    gl.glColor3f(0, 1, 1); // Light-Blue color
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2i(110, 90); // ปล่องไฟ start
    gl.glVertex2i(110, 50); // ปล่องไฟ go up
    gl.glVertex2i(130, 50); // ปล่องไฟ turn right
    gl.glVertex2i(130, 70); // ปล่องไฟ turn right -> bottom
    gl.glEnd();

    gl.glColor3f(1, 0, 1); // Pink color
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2i(120, 200); // door start
    gl.glVertex2i(120, 150); // door go up
    gl.glVertex2i(140, 150); // door turn right
    gl.glVertex2i(140, 200); // door turn down
    gl.glEnd();

    gl.glColor3f(1, 1, 1); // White color
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2i(160, 150); // window start
    gl.glVertex2i(190, 150); // window go right
    gl.glVertex2i(190, 160); // window go down
    gl.glVertex2i(160, 160); // window go left
    gl.glEnd();


    // House With Strip
    gl.glColor3f(1, 0, 0); // Red color
    gl.glBegin(GL2.GL_LINE_STRIP);
    gl.glVertex2i(100, 400); // Start from bottom-left
    gl.glVertex2i(100, 300); // Up to top-left
    gl.glVertex2i(150, 250); // roof peak
    gl.glVertex2i(200, 300); // top-right
    gl.glVertex2i(200, 400); // bottom-right (Loop auto back to start)
    gl.glVertex2i(100, 400); // back to start
    gl.glEnd();

    gl.glColor3f(0, 1, 1); // Light-Blue color
    gl.glBegin(GL2.GL_LINE_STRIP);
    gl.glVertex2i(110, 290); // ปล่องไฟ start
    gl.glVertex2i(110, 250); // ปล่องไฟ go up
    gl.glVertex2i(130, 250); // ปล่องไฟ turn right
    gl.glVertex2i(130, 270); // ปล่องไฟ turn right -> bottom
    gl.glVertex2i(110, 290); // back to start
    gl.glEnd();

    gl.glColor3f(1, 0, 1); // Pink color
    gl.glBegin(GL2.GL_LINE_STRIP);
    gl.glVertex2i(120, 400); // door start
    gl.glVertex2i(120, 350); // door go up
    gl.glVertex2i(140, 350); // door turn right
    gl.glVertex2i(140, 400); // door turn down
    gl.glVertex2i(120, 400); // back to start
    gl.glEnd();

    gl.glColor3f(1, 1, 1); // White color
    gl.glBegin(GL2.GL_LINE_STRIP);
    gl.glVertex2i(160, 350); // window start
    gl.glVertex2i(190, 350); // window go right
    gl.glVertex2i(190, 360); // window go down
    gl.glVertex2i(160, 360); // window go left
    gl.glVertex2i(160, 350); // back to start
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
    gl.glOrtho(0, 640, 640, 0, 0, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
