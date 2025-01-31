import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import java.util.concurrent.TimeUnit;

public class TranslateRectangle extends Window {

  private int x, y, width, height;
  private final int dx = 100, dy = 150; // Target translation values
  private int offsetX = 0, offsetY = 0; // Animated movement
  private boolean movingForward = true; // Toggle direction
  private long lastTime = System.currentTimeMillis();
  private boolean paused = true;

  TranslateRectangle() {
    super("Translate Rectangle");
    // Scanner scanner = new Scanner(System.in);

    // // Get user input for initial rectangle position
    // System.out.print("Enter x, y, width, height: ");
    x = 50;// scanner.nextInt();
    y = 50;// scanner.nextInt();
    width = 100;// scanner.nextInt();
    height = 50;// scanner.nextInt();
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClearColor(0, 0, 0, 1);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

    // Draw original rectangle (red)
    gl.glColor3f(1.0f, 0.0f, 0.0f);
    drawRectangle(gl, x, y, width, height);

    // Draw moving rectangle (green)
    gl.glColor3f(0.0f, 1.0f, 0.0f);
    drawRectangle(gl, x + offsetX, y + offsetY, width, height);

    // Animate translation
    if (paused) {
      if (System.currentTimeMillis() - lastTime >= 3000) {
        paused = false;
        lastTime = System.currentTimeMillis();
      }
    } else {
      if (movingForward) {
        offsetX += 2; // Move right
        offsetY += 3; // Move down
        if (offsetX >= dx || offsetY >= dy) {
          offsetX = dx;
          offsetY = dy;
          movingForward = false; // Reverse direction
          paused = true;
          lastTime = System.currentTimeMillis();
        }
      } else {
        offsetX -= 2; // Move left
        offsetY -= 3; // Move up
        if (offsetX <= 0 || offsetY <= 0) {
          offsetX = 0;
          offsetY = 0;
          movingForward = true; // Forward again
          paused = true;
          lastTime = System.currentTimeMillis();
        }
      }
    }
  }

  private void drawRectangle(GL2 gl, int x, int y, int width, int height) {
    gl.glBegin(GL2.GL_QUADS);
    gl.glVertex2i(x, y);
    gl.glVertex2i(x + width, y);
    gl.glVertex2i(x + width, y + height);
    gl.glVertex2i(x, y + height);
    gl.glEnd();
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
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
