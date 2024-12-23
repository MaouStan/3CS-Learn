import java.util.ArrayList;

import com.jogamp.nativewindow.util.Point;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class DrawLine {
  public ArrayList<Point> dda_line(GLAutoDrawable drawable, int x1, int y1, int x2, int y2) {
    ArrayList<Point> Points = new ArrayList<>();
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

    float x = x1, y = y1;

    for (int i = 0; i < steps; i++) {
      // gl.glPointSize(10);
      // gl.glColor3d(1, 0, 0);
      // gl.glBegin(GL2.GL_POINTS);
      // gl.glVertex2i(x, y);
      // gl.glEnd();
      Points.add(new Point(Math.round(x), Math.round(y)));
      x += xinc;
      y += yinc;
    }
    return Points;
  }

  public ArrayList<Point> bresenhamLine(GLAutoDrawable drawable, int x1, int y1, int x2, int y2) {
    ArrayList<Point> Points = new ArrayList<>();
    GL2 gl = drawable.getGL().getGL2();

    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);
    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;
    int err = dx - dy;

    while (true) {
      Points.add(new Point(x1, y1));
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
    return Points;
  }
}
