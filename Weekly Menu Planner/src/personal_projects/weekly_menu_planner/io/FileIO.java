package personal_projects.weekly_menu_planner.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import personal_projects.weekly_menu_planner.planner.CookBook;
import personal_projects.weekly_menu_planner.util.Meal;

public class FileIO {
    
    public static CookBook readFile(String filename) throws FileNotFoundException {
        CookBook cb = new CookBook();
        Scanner in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            String mealInfo = in.nextLine();
            Scanner mealScan = new Scanner(mealInfo);
            Meal m = new Meal(mealScan.next());
            int numIngredients = mealScan.nextInt();
            mealScan.close();
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
        
    }
    
    public static boolean saveToFile(CookBook cb, String filename) throws FileNotFoundException {
        PrintStream out = new PrintStream(new File(filename));
        for (int i = 0; i < cb.size(); i++) {
            out.print(cb.getMealAt(i).toString());
        }
        out.close();
        return true;
    }
}
