package Main;

/*
Rundown: The whole idea of creating this "Game" class is to create a game loop
where we will invoke update() & render() based on a time-ticking system.

But... rather than just creating a normal while loop, we're gonna put the while loop inside a separate Game Thread.
A process can have multiple threads -- e.g. one to update, one to get user inputs.
And threads can run concurrently, which optimizes CPU.

Does it really make sense for a Snake game?? Ehh, maybe not. But whatever its cool  :)

The thread class has an abstract method called "run()", and Runnable is a class made specifically to override
when thread starts, run() is called. And implementing Runnable allows u to override this run()
*/


import java.awt.*;

public class Game implements Runnable {

    private Thread gameThread;
    private final int FPS = 100, UPS = 3;
    //tweaking these values directly changes the snake's speed
    private final long NANO_PER_FRAME = 20_000_000/FPS, NANO_PER_UPDATE = 20_000_000/UPS;

    private GamePanel gamePanel;
    private GameWindow gameWindow;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long startTimeFrames = System.nanoTime();
        long startTimeUpdates = System.nanoTime();

        while(true){
            long currentTimeFrames = System.nanoTime();
            long currentTimeUpdates = System.nanoTime();

            if(currentTimeFrames-startTimeFrames >= NANO_PER_FRAME){
                render();
                startTimeFrames = currentTimeFrames;
            }

            if(currentTimeUpdates-startTimeUpdates >= NANO_PER_UPDATE){
                update();
                startTimeUpdates = currentTimeUpdates;
            }


        }
    }

    private void render() {
        gamePanel.repaint();
    }

    private void update() {
        GamePanel.snake.update();
    }
}
