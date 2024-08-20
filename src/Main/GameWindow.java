package Main;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    public JFrame frame;
    private GamePanel gamePanel;
    //public JPanel topBar;
    public TopBar topBar;

    public GameWindow(GamePanel gamePanel){
        frame = new JFrame("Snake Game");


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gamePanel = gamePanel;

        topBar = new TopBar();

        /*
        I am so pissed that i spent 1.5 hours on this
        The first way I did it, was essentially just create another class that extends JPanel for the top bar itself,
        importing all the set field methods. But even though I replicated my rendering for GamePanel, added all the components
        to the containers, somehow, just somehow, the paintComponent() method refused to re-run
         */

        //TODO: maybe isolate this panel? (make it a panel inside the panel topbar -> only repaint this panel, don't repaint topbar)
        /*
        topBar = new JPanel(new GridBagLayout()){

            public void paintComponent(Graphics g){
                super.paintComponent(g);

                setUpField();
                String text = "Score: ";
                Font font = new Font("Serif", Font.ITALIC, 20);
                FontMetrics metrics = g.getFontMetrics(font);
                g.setFont(font);

                int x = getWidth() - 100;
                int y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();

                renderScore(g,text,x,y);

            }
        };

         */

        frame.add(gamePanel);
        //frame.add(topBar, BorderLayout.NORTH);
        frame.pack();
        gamePanel.requestFocus(); //this line has to come after .pack()

        frame.setLocationRelativeTo(null);

        frame.setResizable(false);

        frame.setTitle(title);

        frame.setVisible(true);



    }

    class TopBar extends JPanel{

        public JPanel topBar;

        public TopBar(){
            topBar = new JPanel(new GridBagLayout());
            setUpField(topBar);
            frame.add(topBar,BorderLayout.NORTH);
        }

        //CANNOT: paintComponent runs immendiately once you add this jpanel to the frame
        //ok... but why is there no score??
        //wait no, what i said in the first line doesnt make sense then, cuz if paintComponent runs, then renderScore must also run
        //ahhh, so paintComponent does NOT run...why???
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            System.out.println("noiyiorev");

            String text = "Score: ";
            Font font = new Font("Serif", Font.ITALIC, 20);
            FontMetrics metrics = g.getFontMetrics(font);
            g.setFont(font);

            int x = getWidth() - 100;
            int y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();

            renderScore(g,text,x,y);

        }
    }


    public void setUpField(JPanel topBar){
        //Create the top horizontal bar for the stats and menu
        topBar.setBackground(new Color (27,34,44));
        topBar.setPreferredSize(new Dimension(450, GamePanel.TILES_LENGTH*2)); // I don't think the width value matters
        topBar.setBorder(null); // this might not do anything
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.gridx = 0; // I'm not even sure if the following four lines do anything
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL; // centers the text vertically
        gbc.insets = new Insets(0, 40, 0, 0);

        JLabel scoreLabel = new JLabel("Cutie :)");
        topBar.add(scoreLabel, gbc);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Serif", Font.ITALIC, 20) );

    }

    public void renderScore(Graphics g, String text, int x, int y){
        System.out.println("rhuofheroig");
        g.setColor(Color.PINK);
        g.drawString(text + GamePanel.snake.getScore(),x,y);
    }




    static String title = "Hiii Princess";
}
