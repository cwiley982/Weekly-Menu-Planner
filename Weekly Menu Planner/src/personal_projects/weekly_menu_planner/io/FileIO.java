package personal_projects.weekly_menu_planner.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import personal_projects.weekly_menu_planner.planner.CookBook;

public class FileIO {
    
    public static CookBook readFile(String filename) {
        CookBook cb = new CookBook();
    }
    
    public static boolean saveToFile(CookBook cb, String filename) {
        try {
            PrintStream out = new PrintStream(new File(filename));
            for (int i = 0; i < cb.size(); i++) {
                out.print(cb.getMealAt(i).toString());
            }
            
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
        
    }
}
