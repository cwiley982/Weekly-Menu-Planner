package personal_projects.weekly_menu_planner.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlannerGUI {

	private JButton btnAddMeal = new JButton("Add Meal");
	private JButton btnRemoveMeal = new JButton("Remove Meal");
	private JButton btnAddToPlanner = new JButton("Add Meal");
	private JButton btnRemoveFromPlanner = new JButton("Remove Meal");
	private JLabel lblMealName = new JLabel("Name:");
	private JLabel lblIngredientName = new JLabel("Name:");
	private JLabel lblIngredientAmount = new JLabel("Amount:");
	private JLabel lblIngredientUnit = new JLabel("Unit:");
	private JLabel lblDaysOfWeek = new JLabel("Sunday\tMonday\tTuesday\tWednesday\tThursday\tFriday\tSaturday");
	private JPanel planner;
	private JPanel cookbook;
	private JPanel mealEditor;

	public static void main(String[] args) {
		PlannerGUI UI = new PlannerGUI();
	}

	public void PlannerGUI() {

	}

	private void setUpPlannerPanel() {

	}

	private void setUpCookBookPanel() {

	}

	private void setUpMealEditorPanel() {

	}
}
