import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Exercise6 extends Window {

  public Exercise6() {
    super("Exercise 6 - Concentric Circles and Triangle");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glColor3f(1, 1, 1);
    drawConcentricCircles(gl, 200, 100, 20, 5);
    drawConcentricCircles(gl, 300, 300, 20, 5);
    drawConcentricCircles(gl, 400, 100, 20, 5);

    drawLine(gl, 200, 100, 300, 300);
    drawLine(gl, 300, 300, 400, 100);
    drawLine(gl, 200, 100, 400, 100);
  }

  private void drawConcentricCircles(GL2 gl, int centerX, int centerY, int radiusStep, int numberOfCircles) {
    for (int i = 1; i <= numberOfCircles; i++) {
      drawCircle(gl, centerX, centerY, i * radiusStep);
    }
  }

  private void drawCircle(GL2 gl, int centerX, int centerY, int radius) {
    int x = 0;
    int y = radius;
    int p = 1 - radius;

    gl.glBegin(GL2.GL_POINTS);
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
    gl.glOrtho(0, 600, 400, 0, -1, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
