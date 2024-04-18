package CRAP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameSheet extends JFrame {

    private final int resolution = 680;
    private final int cellSize = 40;
    public JTable gameSheetTable;
    public static Player player;
    public static Ghost ghost1;
    public static Ghost ghost2;
    public static Ghost ghost3;


    public static Object[][] tab = { {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true , true , false, true , true , false, true , false, true , false, true , true , false, true , true , false},
            {false, true , true , false, true , true , false, true , false, true , false, true , true , false, true , true , false},
            {false, false, false, false, false, false, false, true , false, true , false, false, false, false, false, false, false},
            {false, true , true , true , true , false, false, false, false, false, false, false, true , true , true , true , false},
            {false, false, false, false, true , true , true , false, false, false, true , true , true , false, false, false, false},
            {false, true , false, false, false, false, true , true , false, true , true , false, false, false, false, true , false},
            {false, true , true , false, true , false, false, false, false, false, false, false, true , false, true , true , false},
            {false, false, false, false, false, false, true , false, false, false, true , false, false, false, false, false, false},
            {false, true , true , false, true , true , true , false, false, false, true , true , true , false, true , true , false},
            {false, true , true , false, true , false, false, false, false, false, false, false, true , false, true , true , false},
            {false, true , true , false, true , false, false, false, false, false, false, false, true , false, true , true , false},
            {false, false, true , false, true , true , true , true , false, true , true , true , true , false, true , false, false},
            {false, false, false, false, false, true , false, false, false, false, false, true , false, false, false, false, false},
            {false, true , false, false, false, false, false, false, true , false, false, false, false, false, false, true , false},
            {false, true , true , true , true , false, true , true , true , true , true , false, true , true , true , true , false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}};

    private final Object[] columnnames = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};



    public GameSheet(){
        generateFrame();
    }



    public void generateFrame(){


        player = new Player();
        ghost1 = new Ghost();
        ghost2 = new Ghost();
        ghost3 = new Ghost();






        ///DO ZMIANY!!!!!
        DefaultTableModel model = new DefaultTableModel(tab, columnnames);




        gameSheetTable = new JTable(model){

            @Override
            public DefaultListSelectionModel getSelectionModel() {
                return new DisabledListSelectionModel();
            }
        };


        gameSheetTable.setValueAt(player,2,8);
        gameSheetTable.setValueAt(ghost1, 10, 8);

        gameSheetTable.setRowHeight(cellSize);
        gameSheetTable.getColumnModel().getColumn(0).setWidth(cellSize);
        gameSheetTable.setIntercellSpacing(new Dimension(0, 0));
        gameSheetTable.setDefaultRenderer(Object.class, new GameSheetCellRenderer1());
        gameSheetTable.setTableHeader(null);

        getContentPane().add(gameSheetTable);



        //wyjscie skrotem klawiszowym
        Action exitAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        KeyStroke exitKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        this.getRootPane().getInputMap().put(exitKeyStroke, "exit");
        this.getRootPane().getActionMap().put("exit", exitAction);













        //uruchomienie watkow poruszania sie postaci
        PlayerMovementSupThread playerMovementSupThread = new PlayerMovementSupThread(gameSheetTable, player, this);
        playerMovementSupThread.start();

        GhostMovementThread ghostMovementThread = new GhostMovementThread(gameSheetTable, ghost1, this);
        ghostMovementThread.start();

        GhostMovementThread ghostMovementThread1 = new GhostMovementThread(gameSheetTable, ghost2, this);
        ghostMovementThread1.start();

        GhostMovementThread ghostMovementThread2 = new GhostMovementThread(gameSheetTable, ghost3, this);
        ghostMovementThread2.start();







        //wlasciwosci okna
        setTitle("CRAP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(resolution + 20, resolution + 40);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
