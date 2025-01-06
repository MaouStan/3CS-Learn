import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Cicles extends Window {

  public Cicles() {
    super("Circles and Ellipses");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glColor3f(1, 0, 0);
    drawMidpointCircle(gl, 100, 100, 50, true);

    gl.glColor3f(0, 0, 1);
    drawMidpointCircle(gl, 200, 100, 50, false);

    gl.glColor3f(0, 1, 0);
    drawMidpointEllipse(gl, 100, 200, 70, 40, true);

    gl.glColor3f(1, 0, 1);
    drawMidpointEllipse(gl, 200, 200, 70, 40, false);

    gl.glColor3f(1, 0.5f, 0);
    drawMidpointEllipse(gl, 150, 50, 80, 30, false);

    gl.glColor3f(0, 0.5f, 0.5f);
    drawMidpointEllipse(gl, 250, 150, 30, 80, false);
  }

  private void drawMidpointCircle(GL2 gl, int centerX, int centerY, int radius, boolean usePoints) {
    int x = 0;
    int y = radius;
    int p = 1 - radius;

    if (usePoints) {
      gl.glBegin(GL2.GL_POINTS);
    } else {
      gl.glBegin(GL2.GL_LINE_LOOP);
    }

    while (x <= y) {

      plotCirclePoints(gl, centerX, centerY, x, y);
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

  private void plotCirclePoints(GL2 gl, int centerX, int centerY, int x, int y) {
    gl.glVertex2i(centerX + x, centerY + y);
    gl.glVertex2i(centerX - x, centerY + y);
    gl.glVertex2i(centerX + x, centerY - y);
    gl.glVertex2i(centerX - x, centerY - y);
    gl.glVertex2i(centerX + y, centerY + x);
    gl.glVertex2i(centerX - y, centerY + x);
    gl.glVertex2i(centerX + y, centerY - x);
    gl.glVertex2i(centerX - y, centerY - x);
  }

  private void drawMidpointEllipse(GL2 gl, int centerX, int centerY, int rx, int ry, boolean usePoints) {
    int x = 0;
    int y = ry;
    double rx2 = rx * rx;
    double ry2 = ry * ry;
    double p1 = ry2 - (rx2 * ry) + (0.25 * rx2);
    double dx = 2 * ry2 * x;
    double dy = 2 * rx2 * y;

    if (usePoints) {
      gl.glBegin(GL2.GL_POINTS);
    } else {
      gl.glBegin(GL2.GL_LINE_LOOP);
    }

    while (dx < dy) {
      plotEllipsePoints(gl, centerX, centerY, x, y);
      x++;
      dx += 2 * ry2;
      if (p1 < 0) {
        p1 += ry2 + dx;
      } else {
        y--;
        dy -= 2 * rx2;
        p1 += ry2 + dx - dy;
      }
    }

    double p2 = (ry2) * ((x + 0.5) * (x + 0.5)) + (rx2) * ((y - 1) * (y - 1)) - (rx2 * ry2);
    while (y >= 0) {
      plotEllipsePoints(gl, centerX, centerY, x, y);
      y--;
      dy -= 2 * rx2;
      if (p2 > 0) {
        p2 += rx2 - dy;
      } else {
        x++;
        dx += 2 * ry2;
        p2 += rx2 - dy + dx;
      }
    }

    gl.glEnd();
  }

  private void plotEllipsePoints(GL2 gl, int centerX, int centerY, int x, int y) {
    gl.glVertex2i(centerX + x, centerY + y);
    gl.glVertex2i(centerX - x, centerY + y);
    gl.glVertex2i(centerX + x, centerY - y);
    gl.glVertex2i(centerX - x, centerY - y);
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(0, 300, 300, 0, -1, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
