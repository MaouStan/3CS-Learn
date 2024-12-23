import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Lines extends Window implements GLEventListener {
  Lines() {
    super("DDA Line Drawing Algorithm");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glColor3i(0, 0, 0);
    dda_line(drawable, 50, 250, 200, 100);
    bresenhamLine(drawable, 100, 200, 200, 50);
  }

  public void dda_line(GLAutoDrawable drawable, int x1, int y1, int x2, int y2) {
    GL2 gl = drawable.getGL().getGL2();
    System.out.println("pos1: (" + x1 + ", " + y1 + ")");
    System.out.println("pos1: (" + x2 + ", " + y2 + ")");

    int dx = x2 - x1;
    int dy = y2 - y1;

    System.out.println("diffX: " + dx);
    System.out.println("diffY: " + dy);

    float steps = Math.max(Math.abs(dx), Math.abs(dy));
    float xinc = dx / steps;
    float yinc = dy / steps;

    System.out.println("Steps: " + steps);
    System.out.println("X-Increment: " + xinc);
    System.out.println("Y-Increment: " + yinc);

    int x = x1, y = y1;

    for (int i = 0; i < steps; i++) {
      gl.glPointSize(10);
      gl.glColor3d(1, 0, 0);
      gl.glBegin(GL2.GL_POINTS);
      gl.glVertex2i(x, y);
      gl.glEnd();
      x += xinc;
      y += yinc;
    }
  }

  public void bresenhamLine(GLAutoDrawable drawable, int x1, int y1, int x2, int y2) {
    GL2 gl = drawable.getGL().getGL2();
    System.out.println("pos1: (" + x1 + ", " + y1 + ")");
    System.out.println("pos1: (" + x2 + ", " + y2 + ")");

    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);

    System.out.println("diffX: " + dx);
    System.out.println("diffY: " + dy);

    int p = 2 * dx - dy;

    int sx = x1 < x2 ? 1 : -1;
    int sy = y1 < y2 ? 1 : -1;

    int x = x1, y = y1;

    boolean flagX = false;

    while (true) {
      gl.glPointSize(10);
      gl.glColor3d(0, 1, 0);
      gl.glBegin(GL2.GL_POINTS);
      gl.glVertex2i(x, y);
      gl.glEnd();
      flagX = false;

      if (sx < 0 && x < x2) {
        flagX = true;
      }
      else if (sx > 0 && x >= x2) {
        flagX = true;
      }

      if (flagX) {
        break;
      }

      System.out.println("x: " + x + " y: " + y);

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
