import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handling key inputs
 */
public class KeyHandler implements KeyListener {


    private boolean myUpPressed, myDownPressed, myLeftPressed, myRightPressed;


    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        int keyCode = theEvent.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            myUpPressed = true;

        }
        if (keyCode == KeyEvent.VK_A) {
            myLeftPressed = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            myDownPressed = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            myRightPressed = true;
        }

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param theEvent the event to be processed
     */
    @Override
    public void keyReleased(final KeyEvent theEvent) {
        int keyCode = theEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            myUpPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            myLeftPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            myDownPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            myRightPressed = false;
        }
    }

    public boolean isMyUpPressed() {
        return myUpPressed;
    }

    public boolean isMyDownPressed() {
        return myDownPressed;
    }

    public boolean isMyLeftPressed() {
        return myLeftPressed;
    }

    public boolean isMyRightPressed() {
        return myRightPressed;
    }
}
