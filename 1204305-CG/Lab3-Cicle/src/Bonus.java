import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Bonus extends Window {

  public Bonus() {
    super("Bonus - Stick Man Pattern");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glColor3f(1, 1, 1); // White color

    gl.glColor3d(1, 0, 0);
    drawStickMan(gl, 125, 200); // Left stickman
    gl.glColor3d(0, 1, 0);
    drawStickMan(gl, 250, 200); // Middle stickman
    gl.glColor3d(0, 0, 1);
    drawStickMan(gl, 375, 200); // Right stickman
  }

  private void drawStickMan(GL2 gl, int centerX, int centerY) {
    // draw centerpoint
    gl.glBegin(GL2.GL_POINTS);
    gl.glVertex2i(centerX, centerY);
    gl.glEnd();

    // Head
    drawMidpointCircle(gl, centerX, centerY - 50, 20);

    // Body
    drawEllipse(gl, centerX, centerY, 10, 40);

    // Arms
    drawEllipse(gl, centerX, centerY - 20, 30, 8);

    // Legs
    drawEllipse(gl, centerX - 15, centerY + 45, 8, 30); // Left leg
    drawEllipse(gl, centerX + 15, centerY + 45, 8, 30); // Right leg

    // Hands
    drawMidpointCircle(gl, centerX - 30, centerY - 20, 5); // Left hand
    drawMidpointCircle(gl, centerX + 30, centerY - 20, 5); // Right hand

    // Feet
    drawMidpointCircle(gl, centerX - 15, centerY + 75, 5); // Left foot
    drawMidpointCircle(gl, centerX + 15, centerY + 75, 5); // Right foot

    // Face features
    drawMidpointCircle(gl, centerX - 8, centerY - 55, 3); // Left eye
    drawMidpointCircle(gl, centerX + 8, centerY - 55, 3); // Right eye
    drawMidpointCircle(gl, centerX, centerY - 45, 3); // Mouth
  }

  private void drawMidpointCircle(GL2 gl, int centerX, int centerY, int radius) {
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

  private void drawEllipse(GL2 gl, int centerX, int centerY, int rx, int ry) {
    int x = 0;
    int y = ry;
    double rx2 = rx * rx;
    double ry2 = ry * ry;

    double p1 = ry2 - rx2 * ry + 0.25 * rx2;
    double dx = 2 * ry2 * x;
    double dy = 2 * rx2 * y;

    gl.glBegin(GL2.GL_POINTS);

    // Region 1 (dx < dy)
    while (dx < dy) {
      gl.glVertex2i(centerX + x, centerY - y);
      gl.glVertex2i(centerX - x, centerY - y);
      gl.glVertex2i(centerX + x, centerY + y);
      gl.glVertex2i(centerX - x, centerY + y);

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

    // Region 2 (dx >= dy)
    double p2 = ry2 * (x + 0.5) * (x + 0.5) + rx2 * (y - 1) * (y - 1) - rx2 * ry2;
    while (y >= 0) {
      gl.glVertex2i(centerX + x, centerY - y);
      gl.glVertex2i(centerX - x, centerY - y);
      gl.glVertex2i(centerX + x, centerY + y);
      gl.glVertex2i(centerX - x, centerY + y);

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

  @Override
  public void dispose(GLAutoDrawable drawable) {
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(0, 0, 0, 1); // Black background
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int rx, int ry) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(0, 500, 400, 0, -1, 1); // 2D orthographic projection
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
