package personal_projects.weekly_menu_planner.planner;

import personal_projects.weekly_menu_planner.util.Meal;

public class Planner {
	private Meal[] plannedMeals;
    private CookBook cb;
	private final int DAYS_IN_WEEK = 7;
	private String[] daysInWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	// need some way to keep track of the shopping list

	public Planner() {
		plannedMeals = new Meal[DAYS_IN_WEEK];
        cb = new CookBook();
	}

    public CookBook getCookBook() {
        return cb;
	}

	public void addMealToPlanner(String mealName, int index) {
        for (int i = 0; i < cb.size(); i++) {
            if (cb.getMealAt(i).getMealName().equals(mealName)) {
                plannedMeals[index] = cb.getMealAt(i);
			}
		}
	}

	public boolean removeMealFromPlanner(int index) {
		if (plannedMeals[index] != null) {
			plannedMeals[index] = null;
			return true;
		}
		return false;
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
