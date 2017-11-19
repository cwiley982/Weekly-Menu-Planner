package personal_projects.weekly_menu_planner.planner;

import personal_projects.weekly_menu_planner.util.ArrayList;
import personal_projects.weekly_menu_planner.util.Meal;

public class CookBook {
    private ArrayList<Meal> meals;
    
    public CookBook() {
        meals = new ArrayList<Meal>();
    }
    
    public int size() {
        return meals.size();
    }
    
    public Meal getMealAt(int index) {
        return meals.get(index);
    }
    
    public void addMeal(Meal m) {
        meals.add(m);
    }
    
    public boolean removeMeal(String mealName) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getMealName().equals(mealName)) {
                meals.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public String[][] getCookBookArray() {
        String[][] cb = new String[meals.size()][3];
        for (int i = 0; i < meals.size(); i++) {
            cb[i][0] = meals.get(i).getMealName();
            cb[i][1] = Integer.toString(meals.get(i).getNumIngredients());
            cb[i][2] = (meals.get(i).getPlanned() ? "Y" : "");
        }
        return cb;
    }
    
    public String getMealNames() {
        StringBuilder mealNames = new StringBuilder();
        if (meals.size() == 0) {
            return "You don't have any meals in your cookbook yet.\n";
        }
        for (int i = 0; i < meals.size(); i++) {
            mealNames.append(meals.get(i).getMealName()).append("\n");
        }
        return mealNames.toString();
    }
}
