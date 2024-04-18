package CRAP;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static CRAP.GameSheet.*;

public class GameSheetCellRenderer1 extends DefaultTableCellRenderer {
    private final Color wallColor = Color.PINK;
    private final Color pathColor = Color.LIGHT_GRAY;

    private final ImageIcon icon1 = new ImageIcon("/Users/igorszlachta/IdeaProjects/GUI_pacman/src/CRAP/trashcan1.png");
    private final ImageIcon icon2 = new ImageIcon("/Users/igorszlachta/IdeaProjects/GUI_pacman/src/CRAP/trashcan2.png");
    private final ImageIcon truck1 = new ImageIcon("/Users/igorszlachta/IdeaProjects/GUI_pacman/src/CRAP/truck1.png");
    private final ImageIcon truck2 = new ImageIcon("/Users/igorszlachta/IdeaProjects/GUI_pacman/src/CRAP/truck2.png");
    private final ImageIcon truck3 = new ImageIcon("/Users/igorszlachta/IdeaProjects/GUI_pacman/src/CRAP/truck3.png");
    private JLabel playerLabel;
    private JLabel ghostLabel;
    private JPanel panel1;
    private JPanel panel;
    private AnimationThread animationThread;

    public GameSheetCellRenderer1() {
        playerLabel = new JLabel();
        ghostLabel = new JLabel();
        panel1 = new JPanel();
        panel = new JPanel();
        panel.add(playerLabel);
        panel1.add(ghostLabel);
        animationThread = null;

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value.equals(player)) {
            playerLabel.setIcon(icon1);
            panel.setBackground(pathColor);

            animationThread = new AnimationThread();
            animationThread.start();

            return panel;
        }
        else if(value.equals(ghost1)){

            ghostLabel.setIcon(truck1);
            panel1.setBackground(pathColor);


            return panel1;
        }
        else if(value.equals(ghost2)){

            ghostLabel.setIcon(truck2);
            panel1.setBackground(pathColor);


            return panel1;
        }
        else if(value.equals(ghost3)){

            ghostLabel.setIcon(truck3);
            panel1.setBackground(pathColor);


            return panel1;
        }
        else {
            if (value.equals(true)) {
                component.setBackground(wallColor);
                component.setForeground(wallColor);
            } else if (value.equals(false)) {
                component.setBackground(pathColor);
                component.setForeground(pathColor);
            }
            return component;
        }

    }

    private class AnimationThread extends Thread {
        @Override
        public void run() {
            while (true) {
                playerLabel.setIcon(icon2);
                panel.revalidate();
                panel.repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }

                playerLabel.setIcon(icon1);
                panel.revalidate();
                panel.repaint();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }

    }



}
