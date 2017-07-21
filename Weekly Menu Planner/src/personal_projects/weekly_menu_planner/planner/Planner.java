package personal_projects.weekly_menu_planner.planner;

import java.util.ArrayList;

import personal_projects.weekly_menu_planner.util.Meal;

public class Planner {
	private Meal[] weeklyMeals;
	private ArrayList<Meal> mealDirectory;
	private final int DAYS_IN_WEEK = 7;
	// need some way to keep track of the shopping list

	public Planner() {
		weeklyMeals = new Meal[DAYS_IN_WEEK];
		mealDirectory = new ArrayList<Meal>();
	}

	public boolean addMealToList(String mealName) {
		try {
			Meal newMeal = new Meal(mealName);
			mealDirectory.add(newMeal);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public String[] getMealNames() {
		String[] mealNames = new String[mealDirectory.size()];
		for (int i = 0; i < mealDirectory.size(); i++) {
			mealNames[i] = mealDirectory.get(i).getMealName();
		}
		return mealNames;
	}

	public void addMealToPlanner(String mealName, int index) {
		for (int i = 0; i < mealDirectory.size(); i++) {
			if (mealDirectory.get(i).getMealName().equals(mealName)) {
				weeklyMeals[index] = mealDirectory.get(i);
			}
		}
	}

	public boolean removeMealFromPlanner(int index) {
		if (weeklyMeals[index] != null) {
			weeklyMeals[index] = null;
			return true;
		}
		return false;
	}

	public String[] getPlannerMeals() {
		String[] plannerMeals = new String[weeklyMeals.length];
		for (int i = 0; i < weeklyMeals.length; i++) {
			plannerMeals[i] = weeklyMeals[i].getMealName();
		}
		return plannerMeals;
	}

}
