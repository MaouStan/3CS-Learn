import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Exercise5 extends Window {

  public Exercise5() {
    super("Exercise 5 - Segmented Circle with X");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glColor3f(1, 1, 1);
    drawSemicircles(gl, 200, 200, 140);

    gl.glColor3f(1, 1, 1);
    drawLine(gl, 100, 100, 300, 300);

    gl.glColor3f(1, 1, 1);
    drawLine(gl, 300, 100, 100, 300);
  }

  private void drawSemicircles(GL2 gl, int centerX, int centerY, int radius) {
    int x = 0;
    int y = radius;
    int p = 1 - radius;

    gl.glBegin(GL2.GL_POINTS);
    while (x <= y) {

      gl.glVertex2i(centerX + x, centerY - y);
      gl.glVertex2i(centerX - x, centerY - y);

      gl.glVertex2i(centerX + x, centerY + y);
      gl.glVertex2i(centerX - x, centerY + y);

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

  private void drawLine(GL2 gl, int x1, int y1, int x2, int y2) {
    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;
    int err = dx - dy;

    gl.glBegin(GL2.GL_POINTS);
    while (true) {
      gl.glVertex2i(x1, y1);
      if (x1 == x2 && y1 == y2)
        break;
      int e2 = 2 * err;
      if (e2 > -dy) {
        err -= dy;
        x1 += sx;
      }
      if (e2 < dx) {
        err += dx;
        y1 += sy;
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
