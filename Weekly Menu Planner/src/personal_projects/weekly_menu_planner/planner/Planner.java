package personal_projects.weekly_menu_planner.planner;

import java.util.ArrayList;

import personal_projects.weekly_menu_planner.util.Meal;

public class Planner {
	private Meal[] plannedMeals;
	private ArrayList<Meal> cookbook;
	private final int DAYS_IN_WEEK = 7;
	// need some way to keep track of the shopping list

	public Planner() {
		plannedMeals = new Meal[DAYS_IN_WEEK];
		cookbook = new ArrayList<Meal>();
	}

	public boolean addMealToCookBook(String mealName) {
		try {
			Meal newMeal = new Meal(mealName);
			cookbook.add(newMeal);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public boolean removeMealFromCookBook(String mealName) {
		for (int i = 0; i < cookbook.size(); i++) {
			if (cookbook.get(i).getMealName().equals(mealName)) {
				cookbook.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Meal> getCookBook() {
		return cookbook;
	}

	public String getCookBookAsString() {
		String mealNames = "";
		if (cookbook.size() == 0) {
			return "You don't have any meals in your cookbook yet.\n";
		}
		for (int i = 0; i < cookbook.size(); i++) {
			mealNames += cookbook.get(i).getMealName() + "\n";
		}
		return mealNames;
	}

	public void addMealToPlanner(String mealName, int index) {
		for (int i = 0; i < cookbook.size(); i++) {
			if (cookbook.get(i).getMealName().equals(mealName)) {
				plannedMeals[index] = cookbook.get(i);
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

	public String[] getPlannedMeals() {
		String[] plannerMeals = new String[plannedMeals.length];
		for (int i = 0; i < plannedMeals.length; i++) {
			plannerMeals[i] = plannedMeals[i].getMealName();
		}
		return plannerMeals;
	}

}
