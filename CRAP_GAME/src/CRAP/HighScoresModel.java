package CRAP;

import javax.swing.*;
import java.util.Vector;

public class HighScoresModel extends AbstractListModel {


    public Vector<String> highscores;

    public HighScoresModel(Vector<String> highscores){
        this.highscores = highscores;
    }

    @Override
    public int getSize() {
        return highscores.size();
    }

    @Override
    public Object getElementAt(int index) {
        return highscores.get(index);
    }




    public void add(String highscore, int index){
        highscores.add(index, highscore);
        fireIntervalAdded(this, index, index);
    }

    public void add(String highscore){
        add(highscore, getSize());
    }

    public void remove(int index){
        highscores.remove(index);
        fireIntervalRemoved(this, index, index);
    }


}
