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
    dda_line(drawable);
    bresenhamLine(drawable);
  }

  public void dda_line(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    int x1 = 50, y1 = 250;
    int x2 = 200, y2 = 100;
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

  public void bresenhamLine(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    int x1 = 20, y1 = 50;
    int x2 = 200, y2 = 200;
    System.out.println("pos1: (" + x1 + ", " + y1 + ")");
    System.out.println("pos1: (" + x2 + ", " + y2 + ")");

    int dx = Math.abs(x2 - x1);
    int dy = Math.abs(y2 - y1);

    System.out.println("diffX: " + dx);
    System.out.println("diffY: " + dy);

    int p = 2 * dx - dy;

    int x = x1;
    int y = y1;
    while (x < x2) {
      gl.glPointSize(10);
      gl.glColor3d(0, 1, 0);
      gl.glBegin(GL2.GL_POINTS);
      gl.glVertex2i(x, y);
      gl.glEnd();
      x++;
      if (p < 0) {
        p += 2 * dx;
      } else {
        p += 2 * dx - 2 * dy;
        y++;
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
