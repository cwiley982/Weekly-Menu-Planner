package personal_projects.weekly_menu_planner.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import personal_projects.weekly_menu_planner.manager.MealManager;
import personal_projects.weekly_menu_planner.util.Meal;

public class PlannerGUI extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private MealManager manager;
    private JButton btnRemoveMeal = new JButton("Remove Meal");
    
    private JLabel lblDaysOfWeek = new JLabel(
            "Sunday\tMonday\tTuesday\tWednesday\tThursday\tFriday\tSaturday");
    private String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday" };
    private JPanel panel;
    private PlannerPanel pnlPlanner;
    private CookBookPanel pnlCookBook;
    private JPanel pnlMealEditor;
    private CardLayout cardLayout;
    
    public static void main(String[] args) {
        PlannerGUI UI = new PlannerGUI();
    }
    
    public PlannerGUI() {
        super();
        setSize(550, 500);
        setLocation(200, 200);
        manager = new MealManager();
        
        Meal pasta = new Meal("Pasta");
        pasta.addIngredient("Pasta", .5, "lb");
        pasta.addIngredient("Water", 1, "cup");
        manager.addMealToCookBook(pasta);
        manager.addMealToPlanner("Pasta", 0);
        pnlPlanner = new PlannerPanel();
        pnlCookBook = new CookBookPanel();
        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
        panel.add(pnlPlanner, "Planner");
        panel.add(pnlCookBook, "Cook Book");
        cardLayout.show(panel, "Planner");
        
        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private class PlannerPanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private JButton btnPlanMeal;
        private JButton btnRemoveMeal;
        private JButton btnClearPlanner;
        private JTable tblIngredients;
        private IngredientTableModel ingTableModel;
        private JScrollPane scrollpane;
        private JTable tblSchedule;
        
        public PlannerPanel() {
            // Creates a table of days of the week and currently scheduled meals
            tblSchedule = new JTable(7, 2);
            tblSchedule.setRowHeight(28);
            tblSchedule.setPreferredSize(new Dimension(250, 196));
            for (int i = 0; i < 7; i++) {
                tblSchedule.setValueAt(daysOfWeek[i], i, 0);
                tblSchedule.setValueAt(manager.getPlanner().getMealName(i), i, 1);
            }
            tblSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tblSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    Meal m = manager.getPlanner().getMeal(tblSchedule.getSelectedRow());
                    ingTableModel.updateData(m);
                    PlannerGUI.this.repaint();
                }
            });
            
            //Creates a list of ingredients for the selected meal from the planner
            ingTableModel = new IngredientTableModel();
            tblIngredients = new JTable(ingTableModel);
            scrollpane = new JScrollPane(tblIngredients);
            scrollpane.setPreferredSize(new Dimension(250, 196));
            
            // Creates a panel with buttons to add, remove and clear all meals
            JPanel pnlActions = new JPanel();
            pnlActions.setLayout(new GridLayout(1, 3));
            btnPlanMeal = new JButton("Plan Meal");
            btnPlanMeal.addActionListener(this);
            btnRemoveMeal = new JButton("Remove Meal");
            btnRemoveMeal.addActionListener(this);
            btnClearPlanner = new JButton("Clear Planner");
            btnClearPlanner.addActionListener(this);
            pnlActions.add(btnClearPlanner);
            pnlActions.add(btnRemoveMeal);
            pnlActions.add(btnPlanMeal);
            
            add(tblSchedule, BorderLayout.WEST);
            add(scrollpane, BorderLayout.EAST);
            add(pnlActions, BorderLayout.SOUTH);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnClearPlanner) {
                // ask if they're sure they want to clear it
            } else if (e.getSource() == btnPlanMeal) {
                // add meal to planner, get selected row from cookbook and add
                // that meal. Add pop-up box to let them choose what day they
                // want to add it to
            } else if (e.getSource() == btnRemoveMeal) {
                // remove meal from where they clicked
                manager.removeMealFromPlanner(tblSchedule.getSelectedRow());
                tblSchedule.clearSelection();
                // update schedule table
            }
        }
        

    }
    
    private class CookBookPanel extends JPanel implements ActionListener {
        private JButton btnImport;
        private JButton btnExport;
        private JTable tblCookBook;
        private CookBookTableModel cbTableModel;
        
        public CookBookPanel() {
            cbTableModel = new CookBookTableModel();
            tblCookBook = new JTable(cbTableModel);
            tblCookBook.setPreferredSize(new Dimension(500, 200));
            tblCookBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            btnImport = new JButton("Import Cook Book");
            btnImport.addActionListener(this);
            btnExport = new JButton("Export Cook Book");
            btnExport.addActionListener(this);
            
            JPanel actionButtons = new JPanel();
            actionButtons.add(btnExport, BorderLayout.EAST);
            actionButtons.add(btnImport, BorderLayout.WEST);
            add(tblCookBook, BorderLayout.NORTH);
            add(actionButtons, BorderLayout.SOUTH);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnImport) {
                // prompt for file to import
            } else if (e.getSource() == btnExport) {
                // ask for file to export to
            }
        }
    }
    
    private class MealCreatorPanel extends JPanel implements ActionListener {
        private JButton btnAddMeal = new JButton("Add Meal");
        private JLabel lblMealName = new JLabel("Meal Name:");
        private JLabel lblIngredientName = new JLabel("Name:");
        private JLabel lblIngredientAmount = new JLabel("Amount:");
        private JLabel lblIngredientUnit = new JLabel("Unit:");
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
