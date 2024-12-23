import java.util.ArrayList;
import java.util.List;

import com.jogamp.nativewindow.util.Point;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Exercise2 extends Window implements GLEventListener {
  private List<Point> ddaPoints = new ArrayList<>();
  private List<Point> bresenhamPoints = new ArrayList<>();
  private int frameIndex = 0;
  private int maxFrameIndex = 0;
  private DrawLine drawLine = new DrawLine();
  ArrayList<Point> points = new ArrayList<>();

  Exercise2() {
    super("Exercise 2");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glColor3i(0, 0, 0);
    if (ddaPoints.size() == 0) {
      // triangle 3 line
      // line 1 under - go right straight line
      points = drawLine.dda_line(drawable, 50, 100, 200, 100);
      ddaPoints.addAll(points);
      // line 2 right - go top straight line
      points = drawLine.dda_line(drawable, 200, 100, 200, 10);
      ddaPoints.addAll(points);
      // line 3 Diagonal - go left bttom to top right
      points = drawLine.dda_line(drawable, 200, 10, 50, 100);
      ddaPoints.addAll(points);

      // Square 4 line and Cross 2 line
      // line 1 under - go right straight line
      points = drawLine.dda_line(drawable, 50, 250, 200, 250);
      ddaPoints.addAll(points);
      // line 2 right - go top straight line
      points = drawLine.dda_line(drawable, 200, 250, 200, 150);
      ddaPoints.addAll(points);
      // line 3 top - go left straight line
      points = drawLine.dda_line(drawable, 200, 150, 50, 150);
      ddaPoints.addAll(points);
      // line 4 left - go bottom straight line
      points = drawLine.dda_line(drawable, 50, 150, 50, 250);
      ddaPoints.addAll(points);
      // line 5 cross 1 - go top left to bottom right
      points = drawLine.dda_line(drawable, 50, 150, 200, 250);
      ddaPoints.addAll(points);
      // line 6 cross 2 - go top right to bottom left
      points = drawLine.dda_line(drawable, 200, 150, 50, 250);
      ddaPoints.addAll(points);
    }
    if (bresenhamPoints.size() == 0) {
      // triangle 3 line
      // line 1 under - go right straight line
      points = drawLine.bresenhamLine(drawable, 50, 100, 200, 100);
      bresenhamPoints.addAll(points);
      // line 2 right - go top straight line
      points = drawLine.bresenhamLine(drawable, 200, 100, 200, 10);
      bresenhamPoints.addAll(points);
      // line 3 Diagonal - go left bttom to top right
      points = drawLine.bresenhamLine(drawable, 200, 10, 50, 100);
      bresenhamPoints.addAll(points);

      // Square 4 line and Cross 2 line
      // line 1 under - go right straight line
      points = drawLine.bresenhamLine(drawable, 50, 250, 200, 250);
      bresenhamPoints.addAll(points);
      // line 2 right - go top straight line
      points = drawLine.bresenhamLine(drawable, 200, 250, 200, 150);
      bresenhamPoints.addAll(points);
      // line 3 top - go left straight line
      points = drawLine.bresenhamLine(drawable, 200, 150, 50, 150);
      bresenhamPoints.addAll(points);
      // line 4 left - go bottom straight line
      points = drawLine.bresenhamLine(drawable, 50, 150, 50, 250);
      bresenhamPoints.addAll(points);
      // line 5 cross 1 - go top left to bottom right
      points = drawLine.bresenhamLine(drawable, 50, 150, 200, 250);
      bresenhamPoints.addAll(points);
      // line 6 cross 2 - go top right to bottom left
      points = drawLine.bresenhamLine(drawable, 200, 150, 50, 250);
      bresenhamPoints.addAll(points);
    }
    maxFrameIndex = Math.max(ddaPoints.size(), bresenhamPoints.size());
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
      if (index > frameIndex - 10)
        break;
      gl.glPointSize(10);
      gl.glColor3d(0, 0, 1);
      gl.glBegin(GL2.GL_POINTS);
      gl.glVertex2i(point.getX(), point.getY());
      gl.glEnd();
    }

    if (frameIndex >= maxFrameIndex + 100) {
      frameIndex = 0;
    }
    frameIndex++;
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
