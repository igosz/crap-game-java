package CRAP;

import javax.swing.table.AbstractTableModel;

import static CRAP.GameSheet.tab;

public class TableModel extends AbstractTableModel {
    @Override
    public int getRowCount() {
        return 17;
    }

    @Override
    public int getColumnCount() {
        return 17;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tab[rowIndex][columnIndex];
    }
}
