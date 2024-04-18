package CRAP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameOver extends JFrame {

    public GameOver(){

        JPanel p = new JPanel();
        JButton button1 = new JButton("New Game");
        JButton button2 = new JButton("High Scores");
        JButton button3 = new JButton("Exit");

        ImageIcon deadIcon = new ImageIcon("/Users/igorszlachta/IdeaProjects/GUI_pacman/src/CRAP/youdied0.png");
        JLabel deadLabel = new JLabel(deadIcon);

        add(p);
        getContentPane().add(p);

        p.add(deadLabel);//
        deadLabel.setBounds(220,150,250,140);


        button1.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(()->new GameSheet());
        });
        button2.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(()->new HighScores());
        });
        button3.addActionListener(e -> dispose());



        p.add(button1);
        p.add(button2);
        p.add(button3);





        p.setLayout(null);
        p.setBackground(Color.BLACK);


        button1.setBounds(250,325,180,40);
        button2.setBounds(250,375,180,40);
        button3.setBounds(250,425,180, 40);









        Action exitAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };


        KeyStroke exitKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        this.getRootPane().getInputMap().put(exitKeyStroke, "exit");
        this.getRootPane().getActionMap().put("exit", exitAction);


        setSize(680,680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
