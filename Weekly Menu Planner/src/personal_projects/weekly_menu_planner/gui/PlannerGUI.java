package personal_projects.weekly_menu_planner.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private JTable tblCookBook;
    private JPanel pnlMealCreator;
    private PlannerTableModel plannerTableModel;
    private static final String COOKBOOK_PANEL = "Cook Book Panel";
    private static final String PLANNER_PANEL = "Planner Panel";
    private static final String MEAL_CREATOR_PANEL = "Meal Creator Panel";
    
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        PlannerGUI UI = new PlannerGUI();
    }
    
    public PlannerGUI() {
        super();
        setSize(800, 1000);
        setLocation(200, 50);
        manager = new MealManager();
        
        Meal pasta = new Meal("Pasta");
        pasta.addIngredient("Pasta", .5, "lb");
        pasta.addIngredient("Water", 1, "cup");
        manager.addMealToCookBook(pasta);
        manager.addMealToPlanner(pasta, 0);
        pnlPlanner = new PlannerPanel();
        pnlCookBook = new CookBookPanel();
        pnlMealCreator = new MealCreatorPanel();
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(pnlPlanner, PLANNER_PANEL);
        panel.add(pnlCookBook, COOKBOOK_PANEL);
        panel.add(pnlMealCreator, MEAL_CREATOR_PANEL);
        
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
            plannerTableModel = new PlannerTableModel(manager.getPlanner());
            tblSchedule = new JTable(plannerTableModel);
            tblSchedule.setRowHeight(28);
            tblSchedule.setPreferredSize(new Dimension(250, 196));
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
        
        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnClearPlanner) {
                // ask if they're sure they want to clear it
            } else if (e.getSource() == btnPlanMeal) {
                // add meal to planner, get selected row from cookbook and add
                // that meal. Add pop-up box to let them choose what day they
                // want to add it to
                int row = tblCookBook.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(PlannerGUI.this,
                            "No menu selected in the cook book.");
                } else {
                    // ask what day(s) they want to add meal to
                    JOptionPane jop = new JOptionPane((Object) "Select a day to add this meal to",
                            JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
                            (Object[]) daysOfWeek);
                    // TODO
                    JDialog jd = jop.createDialog(PlannerGUI.this, "Day selector");
                    jd.show();
                    switch ((String) jop.getValue()) {
                    case "Sunday":
                        // add to sunday
                        manager.addMealToPlanner(manager.getCookBook().getMealAt(row), 0);
                        break;
                    case "Monday":
                        // add to monday
                        manager.addMealToPlanner(manager.getCookBook().getMealAt(row), 1);
                        break;
                    case "Tuesday":
                        // add to tuesday
                        manager.addMealToPlanner(manager.getCookBook().getMealAt(row), 2);
                        break;
                    case "Wednesday":
                        // add to wednesday
                        manager.addMealToPlanner(manager.getCookBook().getMealAt(row), 3);
                        break;
                    case "Thursday":
                        // add to thursday
                        manager.addMealToPlanner(manager.getCookBook().getMealAt(row), 4);
                        break;
                    case "Friday":
                        // add to friday
                        manager.addMealToPlanner(manager.getCookBook().getMealAt(row), 5);
                        break;
                    case "Saturday":
                        // add to saturday
                        manager.addMealToPlanner(manager.getCookBook().getMealAt(row), 6);
                        break;
                    }
                }
                plannerTableModel.updateData(manager.getPlanner());
            } else if (e.getSource() == btnRemoveMeal) {
                // remove meal from where they clicked
                manager.removeMealFromPlanner(tblSchedule.getSelectedRow());
                ingTableModel.updateData(null);
                plannerTableModel.updateData(manager.getPlanner());
                // update schedule table
            }
            PlannerGUI.this.repaint();
        }
    }
    
    private class CookBookPanel extends JPanel implements ActionListener {
        /** default */
        private static final long serialVersionUID = 1L;
        private JButton btnImport;
        private JButton btnExport;
        private JTable tblIngredients;
        private IngredientTableModel ingTableModel;
        private CookBookTableModel cbTableModel;
        
        public CookBookPanel() {
            cbTableModel = new CookBookTableModel();
            tblCookBook = new JTable(cbTableModel);
            tblCookBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tblCookBook.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    ingTableModel.updateData(
                            manager.getCookBook().getMealAt(tblCookBook.getSelectedRow()));
                    
                    PlannerGUI.this.repaint();
                }
            });
            JScrollPane cbScrollPane = new JScrollPane(tblCookBook);
            cbScrollPane.setPreferredSize(new Dimension(500, 100));
            
            JPanel action = new JPanel();
            action.setLayout(new BorderLayout());
            ingTableModel = new IngredientTableModel();
            tblIngredients = new JTable(ingTableModel);
            tblIngredients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane ingScrollPane = new JScrollPane(tblIngredients);
            ingScrollPane.setPreferredSize(new Dimension(500, 100));
            btnImport = new JButton("Import Cook Book");
            btnImport.addActionListener(this);
            btnExport = new JButton("Export Cook Book");
            btnExport.addActionListener(this);
            action.add(ingScrollPane, BorderLayout.NORTH);
            action.add(btnExport, BorderLayout.EAST);
            action.add(btnImport, BorderLayout.WEST);
            
            add(cbScrollPane, BorderLayout.NORTH);
            add(action, BorderLayout.SOUTH);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./");
            fc.setApproveButtonText("Select");
            int returnVal = -1;
            if (e.getSource() == btnImport) {
                // prompt for file to import
                fc.setDialogTitle("Choose file to import.");
                returnVal = fc.showOpenDialog(this);
                if (returnVal == 0) {
                    // user chose file to open
                    File f = fc.getSelectedFile();
                    manager.loadCookBook(f.getAbsolutePath());
                    cbTableModel.updateData(manager.getCookBook());
                }
                // else, they closed the dialog or hit cancel so there's nothing
                // to do
            } else if (e.getSource() == btnExport) {
                // ask for file to export to
                fc.setDialogTitle("Choose file to save to.");
                returnVal = fc.showSaveDialog(this);
                if (returnVal == 0) {
                    File f = fc.getSelectedFile();
                    manager.saveCookBook(f.getAbsolutePath());
                }
            }
            PlannerGUI.this.repaint();
        }
    }
    
    private class MealCreatorPanel extends JPanel implements ActionListener {
        private JButton btnAddMeal;
        private JButton btnAddIngredients;
        private JLabel lblMealName;
        private JLabel lblNumIngredients;
        private JLabel lblIngredientName;
        private JLabel lblIngredientAmount;
        private JLabel lblIngredientUnit;
        private JTextField txtMealName;
        private JTextField txtNumIngredients;
        private JTextField txtIngredientName;
        private JTextField txtAmount;
        private JComboBox<String> cbUnits;
        private String[] units = { "cup", "lb", "oz", "tsp", "tbsp", "N/A", "pinch", "handful",
                "to taste" };
        
        public MealCreatorPanel() {
            btnAddMeal = new JButton("Add Meal");
            btnAddIngredients = new JButton("Add Ingredients");
            btnAddIngredients.setEnabled(false);
            lblMealName = new JLabel("Meal Name:");
            lblMealName.setHorizontalAlignment(JLabel.RIGHT);
            lblNumIngredients = new JLabel("Number of Ingredients: ");
            lblIngredientName = new JLabel("Name:");
            lblIngredientName.setHorizontalAlignment(JLabel.RIGHT);
            lblIngredientAmount = new JLabel("Amount:");
            lblIngredientAmount.setHorizontalAlignment(JLabel.RIGHT);
            lblIngredientUnit = new JLabel("Unit:");
            lblIngredientUnit.setHorizontalAlignment(JLabel.RIGHT);
            txtMealName = new JTextField();
            txtNumIngredients = new JTextField();
            txtNumIngredients.getDocument().addDocumentListener(new DocumentListener() {
                
                @Override
                public void removeUpdate(DocumentEvent e) {
                    changed();
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    changed();
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                    changed();
                }
                
                public void changed() {
                    try {
                        Integer.parseInt(txtNumIngredients.getText());
                        btnAddIngredients.setEnabled(true);
                    } catch (NumberFormatException n) {
                        btnAddIngredients.setEnabled(false);
                    }
                    PlannerGUI.this.repaint();
                }
            });
            txtIngredientName = new JTextField();
            txtAmount = new JTextField();
            cbUnits = new JComboBox<>(units);
            
            JPanel mealInfo = new JPanel(new GridLayout(1, 4));
            mealInfo.add(lblMealName);
            mealInfo.add(txtMealName);
            mealInfo.add(lblNumIngredients);
            mealInfo.add(txtNumIngredients);
            
            JPanel ingredientName = new JPanel(new GridLayout(1, 2));
            ingredientName.add(lblIngredientName);
            ingredientName.add(txtIngredientName);
            
            JPanel ingredientInfo = new JPanel(new GridLayout(1, 4));
            ingredientInfo.add(lblIngredientAmount);
            ingredientInfo.add(txtAmount);
            ingredientInfo.add(lblIngredientUnit);
            ingredientInfo.add(cbUnits);
            
            setLayout(new GridLayout(4, 1));
            add(mealInfo);
            add(btnAddIngredients);
            add(ingredientName);
            add(ingredientInfo);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            PlannerGUI.this.repaint();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
