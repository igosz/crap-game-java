package CRAP;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import static CRAP.GameSheet.player;

public class GhostMovementThread extends Thread{
    private JTable tab;
    public int ghostRow;
    public int ghostCol;
    private int rowShift;
    private int colShift;
    private GameSheet gameSheet;

    public static boolean moving;


    private Ghost ghost;



    public GhostMovementThread(JTable tab, Ghost ghost, GameSheet gameSheet){
        this.tab = tab;
        this.ghostRow = 10;
        this.ghostCol = 8;
        this.rowShift = 0;
        this.colShift = 0;
        this.ghost = ghost;
        this.gameSheet = gameSheet;

        this.moving = true;
    }



    @Override
    public void run(){



        RandomizeThread randomizeThread = new RandomizeThread();
        randomizeThread.start();


        GhostMovementThread.ConstantGhostMoveThread constantGhostMoveThread = new GhostMovementThread.ConstantGhostMoveThread();
        constantGhostMoveThread.start();






    }
    protected class ConstantGhostMoveThread extends Thread{
        @Override
        public void run() {
            while(true){
                if(moving){
                    moveGhost(rowShift, colShift);
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }


    protected class RandomizeThread extends Thread{
        int randomizer;
        @Override
        public void run(){
            while(true){
                randomizer = (int) ((Math.random() * 4) + 1);
                if(randomizer == 1){
                    setDir(-1,0);
                }
                else if(randomizer == 2){
                    setDir(1,0);
                }
                else if(randomizer == 3){
                    setDir(0,1);
                }
                else if(randomizer == 4){
                    setDir(0,-1);
                }

            }
        }
    }







    protected void setDir(int rowShift, int colShift){
        this.colShift = colShift;
        this.rowShift = rowShift;
    }


    protected void moveGhost(int rowShift, int colShift){

        int newGhostRow = ghostRow+rowShift;
        int newGhostCol = ghostCol+colShift;

        if(newGhostCol >= 0 && newGhostCol < tab.getColumnCount() && newGhostRow >= 0 && newGhostRow < tab.getRowCount() && !tab.getValueAt(newGhostRow, newGhostCol).equals(true) && !(tab.getValueAt(newGhostRow, newGhostCol) instanceof Player)){

            tab.setValueAt(false, ghostRow, ghostCol);
            tab.setValueAt(ghost, newGhostRow, newGhostCol);

            ghostRow = newGhostRow;
            ghostCol = newGhostCol;
        }
        else if(newGhostCol >= 0 && newGhostCol < tab.getColumnCount() && newGhostRow >= 0 && newGhostRow < tab.getRowCount() && !tab.getValueAt(newGhostRow, newGhostCol).equals(true) && tab.getValueAt(newGhostRow, newGhostCol) instanceof Player){

            tab.setValueAt(false, ghostRow, ghostCol);
            tab.setValueAt(ghost, newGhostRow, newGhostCol);

            ghostRow = newGhostRow;
            ghostCol = newGhostCol;

            System.out.println("GameOVER Ghost");
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
