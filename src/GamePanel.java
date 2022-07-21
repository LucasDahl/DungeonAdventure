import javax.swing.*;
import java.awt.*;

/**
 *
 * @Author Luke McAlpine
 * Game Screen settings
 */
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private static final int ORIGINAL_TILE_SIZE = 16; //16x16 tile

    private static final int SCALE = 3;

    private static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; //48x48 tile

    private static final int MAX_SCREEN_COLUMN = 16;
    private static final int MAX_SCREEN_ROW = 12;

    private static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN; // 768 pixels
    private static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels

    private int myFPS = 60;

    private KeyHandler myKeyHandler = new KeyHandler();
    // to have the game run until it stops
    private Thread myGameThread;

    // Set player's default position
    private int myPlayerXPosition = 100;
    private int myPlayerYPosition = 100;
    private int myPlayerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        startGameThread();
        this.addKeyListener(myKeyHandler);
        this.setFocusable(true);


    }


    private void startGameThread() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //1000000000 billion nanosecond = 1 second
        double drawInterval = 1000000000 / myFPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (myGameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // 1 UPDATE: update information such as character positions
                update();

                // 2 DRAW: draw the screen with the updated information
                repaint();

                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

//            System.out.println("The game loop is running");


        }
    }

    // update player coordinates
    private void update() {
        if (myKeyHandler.isMyUpPressed()) {
            myPlayerYPosition -= myPlayerSpeed;
        } else if (myKeyHandler.isMyDownPressed()) {
            myPlayerYPosition += myPlayerSpeed;
        } else if (myKeyHandler.isMyLeftPressed()) {
            myPlayerXPosition -= myPlayerSpeed;
        } else if (myKeyHandler.isMyRightPressed()) {
            myPlayerXPosition += myPlayerSpeed;
        }

    }

    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);

        Graphics2D graphics2D = (Graphics2D) theGraphics;

        graphics2D.setColor(Color.white);
        graphics2D.fillRect(myPlayerXPosition, myPlayerYPosition, TILE_SIZE, TILE_SIZE);
        graphics2D.dispose();

    }
}
