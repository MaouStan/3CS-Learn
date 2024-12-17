import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class QBonus extends Window {
  public QBonus() {
    super("Quiz Bonus");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glLineWidth(4);

    // พื้นผ้า
    gl.glColor3f(1, 0, 0); // Red color
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2i(200, 270);
    gl.glVertex2i(400, 270);
    gl.glVertex2i(400, 370);
    gl.glVertex2i(200, 370);
    gl.glEnd();

    // จตุรัส
    gl.glColor3f(0, 0, 1); // Blue color
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2i(500, 200);
    gl.glVertex2i(550, 200);
    gl.glVertex2i(550, 250);
    gl.glVertex2i(500, 250);
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
    gl.glClearColor(1, 1, 1, 1);
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
