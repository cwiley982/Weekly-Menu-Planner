package personal_projects.weekly_menu_planner.gui;

import javax.swing.table.AbstractTableModel;

import personal_projects.weekly_menu_planner.planner.Planner;

public class PlannerTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 1L;
    private String[] columnNames = { "Day", "Meal Planner" };
    private Object[][] data;
    
    public PlannerTableModel(Planner p) {
        updateData(p);
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
    
    protected void updateData(Planner p) {
        data = p.getPlannerArray();
        fireTableDataChanged();
    }
}
