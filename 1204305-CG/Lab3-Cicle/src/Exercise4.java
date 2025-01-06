import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Exercise4 extends Window {

  public Exercise4() {
    super("Exercise 4 - Semicircle");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glColor3f(1, 1, 1);
    drawSemicircle(gl, 200, 200, 140);
  }

  private void drawSemicircle(GL2 gl, int centerX, int centerY, int radius) {
    int x = 0;
    int y = radius;
    int p = 1 - radius;

    gl.glBegin(GL2.GL_POINTS);
    while (x <= y) {

      gl.glVertex2i(centerX + x, centerY - y);
      gl.glVertex2i(centerX - x, centerY - y);
      gl.glVertex2i(centerX + y, centerY - x);
      gl.glVertex2i(centerX - y, centerY - x);

      x++;
      if (p < 0) {
        p += 2 * x + 1;
      } else {
        y--;
        p += 2 * x - 2 * y + 1;
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
    gl.glOrtho(0, 400, 400, 0, -1, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
