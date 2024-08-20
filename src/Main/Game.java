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


import Buttons.MenuInterface;
import GameStates.GameState;
import GameStates.MenuState;

import java.awt.*;

public class Game implements Runnable {

    private Thread gameThread;
    private final int FPS = 100, UPS = 3;
    //tweaking these values directly changes the snake's speed, it feels pretty good rn
    private final long NANO_PER_FRAME = 14_000_000/FPS, NANO_PER_UPDATE = 14_000_000/UPS;

    private static GamePanel gamePanel;
    public static GameWindow gameWindow;
    private static MenuState menuState;
    public static MenuInterface menuInterface;

    public Game(){
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);

        menuInterface = new MenuInterface();
        menuState = new MenuState();
        gameWindow.frame.add(menuState);
        gameWindow.frame.setVisible(true);

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
                if(GameState.state == GameState.PLAY) {
                    gamePanel.repaint();
                }
                startTimeFrames = currentTimeFrames;
            }

            if(currentTimeUpdates-startTimeUpdates >= NANO_PER_UPDATE){
                update();
                startTimeUpdates = currentTimeUpdates;
            }


        }
    }

    public void render(Graphics g) {

        /*
        if(GameState.state == GameState.MENU){
            try{
                menuState.render(g);
            } catch (Exception e) {
                System.out.println("Menu State not created yet");
                //this will always print, because I have to instantiate MenuState & MenuInterface after GamePanel & GameWindow,
                //cuz MenuState & MenuInterface need the latter two's attributes
            }

        }else {
            gamePanel.drawLevel(g);
            GamePanel.apple.render(g);
            GamePanel.snake.render(g);

            gameWindow.topBar.repaint();
        }

         */

        if(GameState.state == GameState.PLAY) {
            gamePanel.drawLevel(g);
            GamePanel.apple.render(g);
            GamePanel.snake.render(g);
        }
    }

    private void update() {
        if(GameState.state == GameState.PLAY) {
            GamePanel.snake.update();
        }
    }
}
