package personal_projects.weekly_menu_planner.gui;

import javax.swing.table.AbstractTableModel;

import personal_projects.weekly_menu_planner.util.Meal;

public class IngredientTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Ingredient", "Amount", "Unit" };
    private Object[][] data;
    
    public IngredientTableModel() {
        updateData(null);
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getRowCount() {
        if (data == null)
            return 0;
        return data.length;
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Object getValueAt(int row, int col) {
        if (data == null)
            return null;
        try {
            return data[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    protected void updateData(Meal m) {
        if (m == null) {
            String[][] empty = new String[0][3];
            data = empty;
        } else {
            data = m.getIngredientsArray();
        }
        fireTableDataChanged();
    }
}
