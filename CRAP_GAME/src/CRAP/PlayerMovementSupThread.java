package CRAP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static CRAP.GameSheet.*;

public class PlayerMovementSupThread extends Thread{
    private Player player;
    private JTable tab;
    private GameSheet gameSheet;
    private int playerRow;
    private int playerCol;
    private int rowShift;
    private int colShift;

    public static boolean moving;



    public PlayerMovementSupThread(JTable tab, Player player, GameSheet gameSheet){
        this.player = player;
        this.tab = tab;
        this.playerRow = 2;
        this.playerCol = 8;
        this.rowShift = 0;
        this.colShift = 0;
        this.gameSheet = gameSheet;

        this.moving = true;
    }



    @Override
    public void run(){

        tab.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event){
                int key = event.getKeyCode();
                if(key==KeyEvent.VK_UP){
                    setDir(-1,0);
                }else if(key==KeyEvent.VK_DOWN){
                    setDir(1,0);
                }else if(key==KeyEvent.VK_RIGHT){
                    setDir(0,1);
                }else if(key==KeyEvent.VK_LEFT){
                    setDir(0,-1);
                }


            }
        });

        ConstantMoveThread constantMoveThread = new ConstantMoveThread();
        constantMoveThread.start();

    }
    protected class ConstantMoveThread extends Thread{
        @Override
        public void run() {
            while(true){
                if(moving){
                    movePlayer(rowShift, colShift);
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }







    protected void setDir(int rowShift, int colShift){
        this.colShift = colShift;
        this.rowShift = rowShift;
    }


    protected void movePlayer(int rowShift, int colShift){

        int newPlayerRow = playerRow+rowShift;
        int newPlayerCol = playerCol+colShift;

        if(newPlayerCol >= 0 && newPlayerCol < tab.getColumnCount() && newPlayerRow >= 0 && newPlayerRow < tab.getRowCount() && !tab.getValueAt(newPlayerRow, newPlayerCol).equals(true) && !(tab.getValueAt(newPlayerRow, newPlayerCol) instanceof Ghost)){

            tab.setValueAt(false, playerRow, playerCol);
            tab.setValueAt(player, newPlayerRow, newPlayerCol);

            playerRow = newPlayerRow;
            playerCol = newPlayerCol;
        } else if (newPlayerCol >= 0 && newPlayerCol < tab.getColumnCount() && newPlayerRow >= 0 && newPlayerRow < tab.getRowCount() && !tab.getValueAt(newPlayerRow, newPlayerCol).equals(true) && tab.getValueAt(newPlayerRow, newPlayerCol) instanceof Ghost) {

            tab.setValueAt(false, playerRow, playerCol);
            tab.setValueAt(player, newPlayerRow, newPlayerCol);

            playerRow = newPlayerRow;
            playerCol = newPlayerCol;

            System.out.println("Game Over");
            player.setDead(true);
            moving = false;


            //SwingUtilities.invokeLater(()-> new GameOver());
            SwingUtilities.invokeLater(() -> {
                gameSheet.setVisible(false);
                gameSheet.dispose();
                new GameOver();
            });

        }

    }




}
