import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Exercise8 extends Window {

  public Exercise8() {
    super("Exercise 8 - Parabolas");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glColor3f(1, 1, 1);

    // Vertical parabolas
    drawParabola(gl, 200, 200, 0.01, true, true);
    drawParabola(gl, 200, 200, 0.01, true, false);

    // Horizontal parabolas
    drawParabola(gl, 400, 200, 0.01, false, true);
    drawParabola(gl, 400, 200, 0.01, false, false);
  }

  private void drawParabola(GL2 gl, int centerX, int centerY, double a, boolean vertical, boolean positive) {
    gl.glBegin(GL2.GL_POINTS);

    for (int i = -200; i <= 200; i++) {
      if (vertical) {

        int x = i;
        int y = (int) (a * x * x);
        if (positive) {
          gl.glVertex2i(centerX + x, centerY - y);
        } else {
          gl.glVertex2i(centerX + x, centerY + y);
        }
      } else {

        int y = i;
        int x = (int) (a * y * y);
        if (positive) {
          gl.glVertex2i(centerX + x, centerY + y);
        } else {
          gl.glVertex2i(centerX - x, centerY + y);
        }
      }
    }

    gl.glEnd();
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(0, 0, 0, 1);
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(0, 600, 400, 0, -1, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
