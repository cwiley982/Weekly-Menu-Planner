package personal_projects.weekly_menu_planner.manager;

import personal_projects.weekly_menu_planner.planner.CookBook;
import personal_projects.weekly_menu_planner.planner.Planner;
import personal_projects.weekly_menu_planner.util.Meal;

public class MealManager {
    private CookBook cb;
    private Planner planner;
    
    public MealManager() {
        cb = new CookBook();
        planner = new Planner();
    }
    
    public void addMealToCookBook(Meal m) {
        cb.addMeal(m);
    }
    
    public CookBook getCookBook() {
        return cb;
    }
    
    public void addMealToPlanner(String mealName, int index) {
        for (int i = 0; i < cb.size(); i++) {
            if (cb.getMealAt(i).getMealName().equals(mealName)) {
                planner.addMeal(cb.getMealAt(i), index);
            }
        }
    }
    
    public boolean removeMealFromPlanner(int index) {
        return planner.removeMeal(index);
    }
    
    public void removeMealFromCookBook(String mealName) {
        cb.removeMeal(mealName);
    }
    
    public String getPlannedMeals() {
        return planner.getPlannedMeals();
    }
    
    public Planner getPlanner() {
        return planner;
    }
}
