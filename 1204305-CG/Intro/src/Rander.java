import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public class Rander {
  private static GLWindow window = null;

  public static void init() {
    GLProfile.initSingleton();
    GLProfile profile = GLProfile.get(GLProfile.GL2);
    GLCapabilities caps = new GLCapabilities(profile);

    window = GLWindow.create(caps);
    window.setTitle("Intro");
    window.setSize(640, 640);
    window.setResizable(false);
    window.addGLEventListener(new EvenListener());
    FPSAnimator animator = new FPSAnimator(window, 60);
    animator.start();
    window.setVisible(true);
  }

  public static void main(String[] args) {
    init();
  }

}
