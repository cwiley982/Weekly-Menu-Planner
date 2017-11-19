package personal_projects.weekly_menu_planner.io;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import personal_projects.weekly_menu_planner.planner.CookBook;

public class FileIOTest {
    
    @Test
    public void testLoad() {
        CookBook cb = FileIO.readFile("cookbook.txt");
        assertEquals("Pasta", cb.getMealAt(0).getMealName());
    }
    
}
