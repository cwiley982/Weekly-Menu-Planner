package personal_projects.weekly_menu_planner.planner;

import personal_projects.weekly_menu_planner.util.Recipe;

public class Planner {
	private Recipe[] meals;
	private final int DAYS_IN_WEEK = 7;

	public void setUp() {
		meals = new Recipe[DAYS_IN_WEEK];
	}
}
