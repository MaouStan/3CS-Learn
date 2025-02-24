import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public abstract class Window implements GLEventListener {
    private static GLWindow window = null;

    Window(String title) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLProfile.initSingleton();
        GLCapabilities caps = new GLCapabilities(profile);

        window = GLWindow.create(caps);
        window.setTitle(title);
        window.setSize(300, 300);
        window.setResizable(false);
        window.addGLEventListener(this);
        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();
        window.setVisible(true);
    }

    // New: Provide access to the GLWindow for key events
    public static GLWindow getWindow() {
        return window;
    }
}
