package CRAP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class HighScores extends JFrame {

    public HighScores() {

        Vector<String> highscores = new Vector<>();
        highscores.add("Igor 5349");
        highscores.add("Igofdr 543255349");
        highscores.add("Idr 533449");
        highscores.add("dfr 59");
        highscores.add("Ijdf 5344659");
        highscores.add("Igor 5349");
        highscores.add("Igofdr 543255349");
        highscores.add("Idr 533449");
        highscores.add("dfr 59");


        HighScoresModel highScoresModel = new HighScoresModel(highscores);



        JList jList = new JList();
        jList.setModel(highScoresModel);
        jList.setCellRenderer(new HighScoreRenderer());

        JScrollPane jScrollPane = new JScrollPane(jList);
        JButton buttonBack = new JButton("Back");
        JPanel jPanel = new JPanel(new GridLayout(1,1));


        jPanel.add(buttonBack);



        setLayout(new BorderLayout());
        add(jScrollPane, BorderLayout.CENTER);
        add(jPanel, BorderLayout.PAGE_END);




        this.getContentPane().add(jScrollPane);
        jList.setBackground(Color.GRAY);


        buttonBack.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(()->new Okno());
        });





        Action exitAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };


        KeyStroke exitKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        this.getRootPane().getInputMap().put(exitKeyStroke, "exit");
        this.getRootPane().getActionMap().put("exit", exitAction);


        setSize(680, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
