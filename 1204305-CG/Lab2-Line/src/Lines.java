import java.util.ArrayList;
import java.util.List;

import com.jogamp.nativewindow.util.Point;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Lines extends Window implements GLEventListener {
  private List<Point> ddaPoints = new ArrayList<>();
  private List<Point> bresenhamPoints = new ArrayList<>();
  private int frameIndex = 0;

  Lines() {
    super("DDA Line Drawing Algorithm");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glColor3i(0, 0, 0);
    if (ddaPoints.size() == 0) {
      dda_line(drawable, 50, 250, 200, 100);
    }
    if (bresenhamPoints.size() == 0) {
      bresenhamLine(drawable, 100, 200, 200, 50);
    }
    for (Point point : ddaPoints) {
      int index = ddaPoints.indexOf(point);
      if (index > frameIndex)
        break;
      gl.glPointSize(10);
      gl.glColor3d(1, 0, 0);
      gl.glBegin(GL2.GL_POINTS);
      gl.glVertex2i(point.getX(), point.getY());
      gl.glEnd();
    }

    for (Point point : bresenhamPoints) {
      int index = bresenhamPoints.indexOf(point);
      if (index > frameIndex)
        break;
      gl.glPointSize(10);
      gl.glColor3d(0, 0, 1);
      gl.glBegin(GL2.GL_POINTS);
      gl.glVertex2i(point.getX(), point.getY());
      gl.glEnd();
    }
    if (frameIndex >= Math.max(ddaPoints.size(), bresenhamPoints.size())) {
      frameIndex = 0;
    }
    System.out.println("" + bresenhamPoints.size());
    System.out.println("" + ddaPoints.size());
    System.out.println(frameIndex);
    frameIndex++;
  }

  public void dda_line(GLAutoDrawable drawable, int x1, int y1, int x2, int y2) {
    GL2 gl = drawable.getGL().getGL2();
    // System.out.println("pos1: (" + x1 + ", " + y1 + ")");
    // System.out.println("pos1: (" + x2 + ", " + y2 + ")");

    int dx = x2 - x1;
    int dy = y2 - y1;

    // System.out.println("diffX: " + dx);
    // System.out.println("diffY: " + dy);

    float steps = Math.max(Math.abs(dx), Math.abs(dy));
    float xinc = dx / steps;
    float yinc = dy / steps;

    // System.out.println("Steps: " + steps);
    // System.out.println("X-Increment: " + xinc);
    // System.out.println("Y-Increment: " + yinc);

    int x = x1, y = y1;

    for (int i = 0; i < steps; i++) {
      // gl.glPointSize(10);
      // gl.glColor3d(1, 0, 0);
      // gl.glBegin(GL2.GL_POINTS);
      // gl.glVertex2i(x, y);
      // gl.glEnd();
      ddaPoints.add(new Point(Math.round(x), Math.round(y)));
      x += xinc;
      y += yinc;
    }
  }

  public void bresenhamLine(GLAutoDrawable drawable, int x1, int y1, int x2, int y2) {
    GL2 gl = drawable.getGL().getGL2();
    // System.out.println("pos1: (" + x1 + ", " + y1 + ")");
    // System.out.println("pos1: (" + x2 + ", " + y2 + ")");

    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);

    // System.out.println("diffX: " + dx);
    // System.out.println("diffY: " + dy);

    int p = 2 * dx - dy;

    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;

    int x = x1, y = y1;

    boolean flagX = false;

    while (true) {
      // gl.glPointSize(10);
      // gl.glColor3d(0, 1, 0);
      // gl.glBegin(GL2.GL_POINTS);
      // gl.glVertex2i(x, y);
      // gl.glEnd();
      bresenhamPoints.add(new Point(x, y));
      flagX = false;

      if (sx < 0 && x < x2) {
        flagX = true;
      } else if (sx > 0 && x >= x2) {
        flagX = true;
      }

      if (flagX) {
        break;
      }

      // System.out.println("x: " + x + " y: " + y);

      x += sx;
      if (p <= 0) {
        p += 2 * dx;
      } else {
        p += 2 * dx - 2 * dy;
        y += sy;
      }
    }
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
    gl.glOrtho(0, 300, 300, 0, 0, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }

}
