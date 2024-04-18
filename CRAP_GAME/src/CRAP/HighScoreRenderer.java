package CRAP;

import javax.swing.*;
import java.awt.*;

public class HighScoreRenderer extends JLabel implements ListCellRenderer{

    public HighScoreRenderer(){
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        setText((String) value);
        setFont(new Font(Font.SERIF, Font.BOLD, 16));

        if(isSelected)
            setBackground(Color.LIGHT_GRAY);
        else
            setBackground(Color.GRAY);

        return this;
    }
}
