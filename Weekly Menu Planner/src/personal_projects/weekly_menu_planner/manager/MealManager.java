package personal_projects.weekly_menu_planner.manager;

import java.io.FileNotFoundException;

import personal_projects.weekly_menu_planner.io.FileIO;
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
    
    public void addMealToPlanner(Meal m, int index) {
        planner.addMeal(m, index);
    }
    
    public void addMealToPlanner(String str, int index) {
        for (int i = 0; i < cb.size(); i++) {
            if (cb.getMealAt(i).getMealName().equalsIgnoreCase(str)) {
                planner.addMeal(cb.getMealAt(i), index);
            }
        }
        System.out.println("Invalid meal name.");
    }
    
    public boolean removeMealFromPlanner(int index) {
        if (index < 0) {
            return false;
        }
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
    
    public boolean loadCookBook(String filename) {
        try {
            cb = FileIO.readFile(filename);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    
    public boolean saveCookBook(String filename) {
        try {
            FileIO.saveToFile(cb, filename);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
