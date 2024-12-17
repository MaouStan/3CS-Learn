import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class EvenListener implements GLEventListener {

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glLineWidth(10);
    System.out.println("Display");

    // Points
    gl.glColor3d(0.5, 1, 1);
    gl.glPointSize(5);
    gl.glBegin(GL2.GL_POINTS);
    gl.glVertex2i(0, 0);
    // gl.glVertex2i(0, 10);
    gl.glEnd();

    // Lines
    gl.glColor3d(0.1, 0.5, 1);
    gl.glBegin(GL2.GL_LINES);

    gl.glVertex2i(0, 0);
    gl.glVertex2i(500, 500);

    gl.glVertex2i(640, 0);
    gl.glVertex2i(640-500, 500);
    gl.glEnd();

    // Strip
    gl.glColor3d(1, 0, 0);
    gl.glBegin(GL2.GL_LINE_STRIP);

    gl.glVertex2i(50, 50);
    gl.glVertex2i(50, 500);

    gl.glVertex2i(50, 500);
    gl.glVertex2i(550, 500);

    gl.glVertex2i(550, 500);
    gl.glVertex2i(550, 50);

    gl.glVertex2i(550, 50);
    gl.glVertex2i(50, 50);
    gl.glEnd();

    // Z Strip
    gl.glColor3d(0, 0, 0);
    gl.glBegin(GL2.GL_LINE_STRIP);

    gl.glVertex2i(100, 100);
    gl.glVertex2i(200, 100);

    gl.glVertex2i(200, 100);
    gl.glVertex2i(100, 200);

    gl.glVertex2i(100, 200);
    gl.glVertex2i(200, 200);
    gl.glEnd();

    // Inverse
    gl.glColor3d(0, 0, 1);
    gl.glBegin(GL2.GL_LINE_STRIP);
    gl.glVertex2i(500, 300);
    gl.glVertex2i(300, 300);

    gl.glVertex2i(300, 300);
    gl.glVertex2i(500, 400);

    gl.glVertex2i(500, 400);
    gl.glVertex2i(300, 400);
    gl.glEnd();


    // Line Loop
    gl.glColor3d(0, 1, 1);
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glVertex2i(111, 300);
    gl.glVertex2i(222, 222);
    gl.glVertex2i(300, 300);
    gl.glVertex2i(500, 400);
    gl.glEnd();

    // Square
    gl.glColor3d(0.2, 0.5, 1);
    gl.glRecti(10, 10, 60, 200);
    gl.glEnd();

    // Polygon
    gl.glColor3d(1, 0.5, 1);
    gl.glBegin(GL2.GL_POLYGON);
    gl.glVertex2i(500, 500);
    gl.glVertex2i(400, 600);
    gl.glVertex2i(600, 600);
    gl.glVertex2i(500, 500);
    gl.glEnd();



  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    // TODO Auto-generated method stub
    System.out.println("dispose");
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    System.out.println("init");
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();

    // void glOrtho( GLdouble left,
    // GLdouble right,
    // GLdouble bottom,
    // GLdouble top,
    // GLdouble nearVal,
    // GLdouble farVal);
    gl.glOrtho(0, 640, 640, 0, 0, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);

    System.out.println("reshape");

  }

}
