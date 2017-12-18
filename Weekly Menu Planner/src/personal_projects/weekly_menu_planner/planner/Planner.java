package personal_projects.weekly_menu_planner.planner;

import personal_projects.weekly_menu_planner.util.Meal;

public class Planner {
	private Meal[] plannedMeals;
	private final int DAYS_IN_WEEK = 7;
	private String[] daysInWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	// need some way to keep track of the shopping list

	public Planner() {
		plannedMeals = new Meal[DAYS_IN_WEEK];
	}
    
    public String[][] getPlannerArray() {
        String[][] arr = new String[7][2];
        for (int i = 0; i < 7; i++) {
            arr[i][0] = daysInWeek[i];
            arr[i][1] = (plannedMeals[i] != null ? plannedMeals[i].getMealName() : "---");
        }
        return arr;
    }

    public void addMeal(Meal m, int index) {
        plannedMeals[index] = m;
        m.setPlanned();
	}

    public boolean removeMeal(int index) {
		if (plannedMeals[index] != null) {
            plannedMeals[index].unPlan();
			plannedMeals[index] = null;
			return true;
		}
		return false;
	}

    public String getMealName(int index) {
        if (plannedMeals[index] == null) {
            return "---";
        }
        return plannedMeals[index].getMealName();
    }
    
    public Meal getMeal(int i) {
        return plannedMeals[i];
    }
    
	public String getPlannedMeals() {
		String plannerMeals = "";
		if (plannedMeals.length == 0) {
			return "You don't have any meals in your cookbook yet.\n";
		}
		for (int i = 0; i < plannedMeals.length; i++) {
			if (plannedMeals[i] != null) {
				plannerMeals += daysInWeek[i] + ": \t";
				plannerMeals += plannedMeals[i].getMealName() + "\n";
			} else {
				plannerMeals += daysInWeek[i] + ": \t";
				plannerMeals += "---" + "\n";
			}
		}
		return plannerMeals;
	}

}
