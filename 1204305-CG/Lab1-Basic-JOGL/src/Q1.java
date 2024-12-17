import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;

public class Q1 extends Window {
  private TextRenderer renderer;

  public Q1() {
    super("Quiz 1");
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    gl.glLineWidth(6);

    int windowWidth = drawable.getSurfaceWidth();
    int windowHeight = drawable.getSurfaceHeight();

    // Combined data for all shapes
    int[][][] allPoints = {
        { // Red Lines (GL_LINES)
            { 100, 80 }, // A
            { 200, 50 }, // B
            { 150, 150 }, // E
            { 100, 200 }, // F
            { 200, 220 }, // C
            { 230, 130 } // D
        },
        { // Green Line Strip (GL_LINE_STRIP)
            { 300, 80 }, // A
            { 380, 50 }, // B
            { 430, 100 }, // C
            { 380, 200 }, // D
            { 300, 180 }, // E
            { 330, 130 } // F
        },
        { // Blue Line Loop (GL_LINE_LOOP)
            { 500, 80 }, // A
            { 580, 50 }, // B
            { 630, 100 }, // C
            { 580, 200 }, // D
            { 500, 180 }, // E
            { 530, 130 } // F
        }
    };

    String[][] allLabels = {
        { "A", "B", "E", "F", "C", "D" }, // Labels for red lines
        { "A", "B", "C", "D", "E", "F" }, // Labels for green line strip
        { "A", "B", "C", "D", "E", "F" } // Labels for blue line loop
    };

    float[][] colors = {
        { 1, 0, 0 }, // Red
        { 0, 1, 0 }, // Green
        { 0, 0, 1 } // Blue
    };

    int[] primitives = {
        GL2.GL_LINES,
        GL2.GL_LINE_STRIP,
        GL2.GL_LINE_LOOP
    };

    // Loop through each shape
    for (int shapeIndex = 0; shapeIndex < allPoints.length; shapeIndex++) {
      int[][] points = allPoints[shapeIndex];
      String[] labels = allLabels[shapeIndex];
      float[] color = colors[shapeIndex];
      int primitive = primitives[shapeIndex];

      // Set color
      gl.glColor3f(color[0], color[1], color[2]);

      // Draw the shape
      gl.glBegin(primitive);
      for (int[] point : points) {
        gl.glColor3f(color[0], color[1], color[2]);
        gl.glVertex2i(point[0], point[1]);
      }
      gl.glEnd();

      gl.glPointSize(10.0f);
      gl.glColor3f(0.0f, 0.0f, 0.0f);

      gl.glBegin(GL2.GL_POINTS);
      for (int[] point : points) {
        gl.glVertex2i(point[0], point[1]);
      }
      gl.glEnd();

      // Enable point smoothing for better visibility
      gl.glEnable(GL2.GL_POINT_SMOOTH);
      gl.glHint(GL2.GL_POINT_SMOOTH_HINT, GL2.GL_NICEST);

      // Draw labels
      renderer.beginRendering(windowWidth, windowHeight);
      renderer.setColor(0, 0, 0, 1); // Black color for text
      renderer.setSmoothing(true);

      for (int i = 0; i < points.length; i++) {
        int x = points[i][0];
        int y = points[i][1];
        // Adjust text position
        renderer.draw(labels[i], x - 5, windowHeight - y + 5);
      }

      renderer.endRendering();
    }
  }

  // @Override
  // public void display(GLAutoDrawable drawable) {
  // GL2 gl = drawable.getGL().getGL2();
  // gl.glClearColor(1, 1, 1, 1);
  // gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
  // gl.glLineWidth(6);

  // int windowHeight = drawable.getSurfaceHeight();
  // int windowWidth = drawable.getSurfaceWidth();

  // // Start text rendering
  // renderer.beginRendering(drawable.getSurfaceWidth(), windowHeight);
  // renderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
  // renderer.setSmoothing(true);

  // // Draw text with adjusted coordinates
  // renderer.draw("A", 100 - 10, windowHeight - 80 + 10);
  // renderer.draw("B", 200 - 10, windowHeight - 50 + 10);
  // renderer.draw("E", 150 - 10, windowHeight - 150 + 10);
  // renderer.draw("F", 100 - 10, windowHeight - 200 + 10);
  // renderer.draw("C", 200 - 10, windowHeight - 220 + 10);
  // renderer.draw("D", 230 - 10, windowHeight - 130 + 10);
  // renderer.endRendering();

  // // Rest of the line drawing code remains the same
  // gl.glColor3f(1, 0, 0);
  // gl.glBegin(GL2.GL_LINES);
  // // Line A-B
  // gl.glVertex2i(100, 80); // Point A
  // gl.glVertex2i(200, 50); // Point B
  // // Line E-F
  // gl.glVertex2i(150, 150); // Point E
  // gl.glVertex2i(100, 200); // Point F
  // // Line C-D
  // gl.glVertex2i(200, 220); // Point C
  // gl.glVertex2i(230, 130); // Point D
  // gl.glEnd();

  // // Points and labels for the green line strip
  // int[][] points2 = {
  // { 300, 80 }, // A
  // { 380, 50 }, // B
  // { 430, 100 }, // C
  // { 380, 200 }, // D
  // { 300, 180 }, // E
  // { 330, 130 } // F
  // };
  // String[] labels2 = { "A", "B", "C", "D", "E", "F" };

  // // Draw labels for the green line strip
  // renderer.beginRendering(windowWidth, windowHeight);
  // renderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
  // renderer.setSmoothing(true);

  // for (int i = 0; i < points2.length; i++) {
  // int x = points2[i][0];
  // int y = points2[i][1];
  // renderer.draw(labels2[i], x - 10, windowHeight - y + 10);
  // }
  // renderer.endRendering();

  // // 2. Using GL_LINE_STRIP to connect points A-B-C-D-E-F
  // gl.glColor3f(0, 1, 0); // Green color
  // gl.glBegin(GL2.GL_LINE_STRIP);
  // gl.glVertex2i(300, 80); // Point A
  // gl.glVertex2i(380, 50); // Point B
  // gl.glVertex2i(430, 100); // Point C
  // gl.glVertex2i(380, 200); // Point D
  // gl.glVertex2i(300, 180); // Point E
  // gl.glVertex2i(330, 130); // Point F
  // gl.glEnd();

  // // 3. Using GL_LINE_LOOP to connect points A-B-C-D-E-F
  // gl.glColor3f(0, 0, 1); // Blue color
  // gl.glBegin(GL2.GL_LINE_LOOP);
  // gl.glVertex2i(500, 80); // Point A
  // gl.glVertex2i(580, 50); // Point B
  // gl.glVertex2i(630, 100); // Point C
  // gl.glVertex2i(580, 200); // Point D
  // gl.glVertex2i(500, 180); // Point E
  // gl.glVertex2i(530, 130); // Point F
  // gl.glEnd();
  // }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    // Cleanup code here
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(1, 1, 1, 1);
    renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 12));
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(0, 640, 640, 0, 0, 1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
  }
}
