import java.util.ArrayList;
import java.awt.Point;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class ScanLine extends Window {

  // position dot
  ArrayList<ArrayList<Point>> shapes = new ArrayList<>();

  ScanLine() {
    super("ScanLine");

    // add dots
    ArrayList<Point> dots = new ArrayList<>();
    dots.add(new Point(50, 150));
    dots.add(new Point(100, 100));
    dots.add(new Point(100, 250));
    shapes.add(dots);

    // sqaure no overlap
    dots = new ArrayList<>();
    dots.add(new Point(150, 150));
    dots.add(new Point(200, 150));
    dots.add(new Point(200, 200));
    dots.add(new Point(150, 200));
    shapes.add(dots);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

    for (ArrayList<Point> dots : shapes) {
      drawShape(drawable, dots);
    }
  }

  private void drawShape(GLAutoDrawable drawable, ArrayList<Point> dots) {
    ArrayList<Point> scanPoints = calcDots(dots);
    drawLine(drawable, dots, 1, 1, 1);

    int shapeindex = shapes.indexOf(dots);
    if (shapeindex == 1) {
      // split half color
      for (int i = 0; i < scanPoints.size(); i++) {
        if (i < scanPoints.size() / 2) {
          drawDot(drawable, scanPoints.get(i), 1, 0, 0);
        } else {
          drawDot(drawable, scanPoints.get(i), 0, 1, 0);
        }
      }
    } else {
      for (Point p : scanPoints) {
        drawDot(drawable, p, 0, 1, 1);
      }
    }

  }

  private ArrayList<Point> calcDots(ArrayList<Point> dots) {
    // Find min and max y coordinates
    int ymin = dots.stream().mapToInt(p -> (int) p.getY()).min().getAsInt();
    int ymax = dots.stream().mapToInt(p -> (int) p.getY()).max().getAsInt();

    ArrayList<Point> scanPoints = new ArrayList<>();

    for (int y = ymin; y <= ymax; y++) {
      ArrayList<Integer> intersections = new ArrayList<>();

      for (int i = 0; i < dots.size(); i++) {
        Point p1 = dots.get(i);
        Point p2 = dots.get((i + 1) % dots.size());

        if ((p1.getY() <= y && p2.getY() > y) || (p2.getY() <= y && p1.getY() > y)) {
          double m = (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
          double x = p1.getX() + m * (y - p1.getY());
          intersections.add((int) x);
        }
      }

      // sort x_edge values min -> max
      intersections.sort(Integer::compareTo);

      for (int i = 0; i < intersections.size() - 1; i += 2) {
        if (i + 1 < intersections.size()) {
          for (int x = intersections.get(i); x <= intersections.get(i + 1); x++) {
            scanPoints.add(new Point(x, y));
          }
        }
      }
    }

    return scanPoints;
  }

  private void drawDot(GLAutoDrawable drawable, Point p, float r, float g, float b) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glPointSize(1);
    gl.glBegin(GL2.GL_POINTS);
    gl.glColor3f(r, g, b);
    gl.glVertex2d(p.getX(), p.getY());
    gl.glEnd();
  }

  private void drawLine(GLAutoDrawable drawable, ArrayList<Point> dots, float r, float g, float b) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glLineWidth(5);
    gl.glBegin(GL2.GL_LINE_LOOP);
    gl.glColor3f(r, g, b);
    for (int i = 0; i < dots.size(); i++) {
      Point p1 = dots.get(i);
      Point p2 = dots.get((i + 1) % dots.size());
      gl.glVertex2d(p1.getX(), p1.getY());
      gl.glVertex2d(p2.getX(), p2.getY());
    }
    gl.glEnd();
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(0, 0, 0, 1);
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    System.exit(0);
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(0, width, height, 0, -1, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
