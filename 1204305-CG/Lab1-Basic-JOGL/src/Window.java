import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public abstract class Window implements GLEventListener {
  private static GLWindow window = null;

  Window(String title) {
    GLProfile.initSingleton();
    GLProfile profile = GLProfile.get(GLProfile.GL2);
    GLCapabilities caps = new GLCapabilities(profile);

    window = GLWindow.create(caps);
    window.setTitle(title);
    window.setSize(640, 640);
    window.setResizable(false);
    window.addGLEventListener(this);
    FPSAnimator animator = new FPSAnimator(window, 60);
    animator.start();
    window.setVisible(true);
  }
}
