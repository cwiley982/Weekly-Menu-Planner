package personal_projects.weekly_menu_planner.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import personal_projects.weekly_menu_planner.planner.CookBook;
import personal_projects.weekly_menu_planner.util.Meal;

public class FileIO {
    
    public static CookBook readFile(String filename) {
        CookBook cb = new CookBook();
        try {
            Scanner in = new Scanner(new File("output/" + filename));
            while (in.hasNextLine()) {
                String mealInfo = in.nextLine();
                Scanner mealScan = new Scanner(mealInfo);
                Meal m = new Meal(mealScan.next());
                int numIngredients = mealScan.nextInt();
                Scanner lineScan = null;
                for (int i = 0; i < numIngredients; i++) {
                    String ingredient = in.nextLine();
                    lineScan = new Scanner(ingredient);
                    m.addIngredient(lineScan.next(), lineScan.nextDouble(), lineScan.next());
                }
                lineScan.close();
                cb.addMeal(m);
            }
            in.close();
            return cb;
        } catch (FileNotFoundException e) {
            return null;
        }
        
    }
    
    public static boolean saveToFile(CookBook cb, String filename) {
        try {
            PrintStream out = new PrintStream(new File("output/" + filename));
            for (int i = 0; i < cb.size(); i++) {
                out.print(cb.getMealAt(i).toString());
            }
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
        
    }
}
