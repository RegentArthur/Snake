package Main;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    public JFrame frame;
    private GamePanel gamePanel;
    public JPanel topBar;

    public GameWindow(GamePanel gamePanel){
        frame = new JFrame("Snake Game");


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gamePanel = gamePanel;

        topBar = new JPanel(new GridBagLayout()){
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                String text = "Score: ";
                Font font = new Font("Serif", Font.ITALIC, 20);
                FontMetrics metrics = g.getFontMetrics(font);
                g.setFont(font);

                int x = getWidth() - 100;
                int y = (getHeight() - metrics.getHeight())/2 + metrics.getAscent();

                renderScore(g,text,x,y);

            }
        };

        frame.add(gamePanel);
        frame.add(topBar, BorderLayout.NORTH);
        frame.pack();
        gamePanel.requestFocus(); //this line has to come after .pack()

        frame.setLocationRelativeTo(null);

        frame.setResizable(false);

        frame.setTitle(title);

        frame.setVisible(true);



    }

    public void setUpField(){
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

        //ToDo: implement the display to show the score
        JLabel scoreLabel = new JLabel("Cutie :)");
        topBar.add(scoreLabel, gbc);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Serif", Font.ITALIC, 20) );

    }

    public void renderScore(Graphics g, String text, int x, int y){
        g.setColor(Color.PINK);
        g.drawString(text + GamePanel.snake.getScore(),x,y);
    }




    static String title = "Hiii Princess";
}
