package personal_projects.weekly_menu_planner.gui;

import java.util.Scanner;

import personal_projects.weekly_menu_planner.io.FileIO;
import personal_projects.weekly_menu_planner.planner.CookBook;
import personal_projects.weekly_menu_planner.planner.Planner;
import personal_projects.weekly_menu_planner.util.Meal;

public class CommandLineSimulator {
    
    private static Planner planner = new Planner();
    private static CookBook cb = new CookBook();
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Welcome to the Weekly Meal Planner! Here, you can add meals to a \"cook book\", "
                        + "\nplan your meals for the week, and create a shopping list for your meals."
                        + " \nEnter \"C\" to manage the cookbook, \"P\" to plan meals, and \"L\" to view your"
                        + " \nshopping list. You can also enter \"Q\" to quit.");
        System.out.println("\nPlease enter a command from the options above: ");
        String command = input.next();
        while (!command.equalsIgnoreCase("Q")) {
            if (command.equalsIgnoreCase("C")) {
                System.out.println("Would you like to add a meal (\"A\"), "
                        + "remove a meal (\"R\"), or view your cookbook (\"V\")? ");
                String command2 = input.next();
                if (command2.equalsIgnoreCase("A")) {
                    // THE PROBLEM IS RIGHT HERE vvv
                    System.out.println("Enter the name of the meal: ");
                    input.nextLine();
                    String mealName = input.nextLine();
                    Meal meal = new Meal(mealName);
                    System.out.println("Enter the number of ingredients for this meal: ");
                    int numOfIng = input.nextInt();
                    for (int i = 0; i < numOfIng; i++) {
                        System.out.println("Enter ingredient #" + (i + 1)
                                + " in the format as follows: name amount unit");
                        String name = input.next();
                        double amt = input.nextDouble();
                        String unit = input.next();
                        meal.addIngredient(name, amt, unit);
                    }
                    cb.addMeal(meal);
                    System.out.println("Meal successfully added!");
                    // maybe ask if they want to add another meal (won't be
                    // in gui)
                } else if (command2.equalsIgnoreCase("R")) {
                    // allow them to remove a meal
                    System.out.println("Enter the name of the meal you wish to remove: ");
                    input.nextLine();
                    String mealName = input.nextLine();
                    cb.removeMeal(mealName);
                    // this might have the same problem ^^^
                } else if (command2.equalsIgnoreCase("V")) {
                    System.out.println(cb.getMealNames());
                } else {
                    System.out.println("Invalid command.");
                    // need a while loop somewhere to re-prompt after each
                    // command entry
                }
            } else if (command.equalsIgnoreCase("P")) {
                System.out.println("Would you like to add a meal to the planner (\"A\"), "
                        + "remove a meal (\"R\"), or view what meals you currently have planned (\"V\")? ");
                String command3 = input.next();
                if (command3.equalsIgnoreCase("A")) {
                    System.out.println(
                            "Enter the name of the meal, as it appears in the cookbook, that you would like to plan: ");
                    input.nextLine();
                    String meal = input.nextLine();
                    System.out.println(
                            "What day would you like to add this meal to? (U, M, T, W, H, F, S) ");
                    String day = input.next();
                    if (day.equalsIgnoreCase("U")) {
                        planner.addMealToPlanner(meal, 0);
                    } else if (day.equalsIgnoreCase("M")) {
                        planner.addMealToPlanner(meal, 1);
                    } else if (day.equalsIgnoreCase("T")) {
                        planner.addMealToPlanner(meal, 2);
                    } else if (day.equalsIgnoreCase("W")) {
                        planner.addMealToPlanner(meal, 3);
                    } else if (day.equalsIgnoreCase("H")) {
                        planner.addMealToPlanner(meal, 4);
                    } else if (day.equalsIgnoreCase("F")) {
                        planner.addMealToPlanner(meal, 5);
                    } else if (day.equalsIgnoreCase("S")) {
                        planner.addMealToPlanner(meal, 6);
                    } else {
                        System.out.println("Invalid commnand.");
                    }
                    // add a meal to planner
                } else if (command3.equalsIgnoreCase("R")) {
                    System.out.println(
                            "What day would you like to remove a meal from? (U, M, T, W, H, F, S) ");
                    String day = input.next();
                    if (day.equalsIgnoreCase("U")) {
                        planner.removeMealFromPlanner(0);
                    } else if (day.equalsIgnoreCase("M")) {
                        planner.removeMealFromPlanner(1);
                    } else if (day.equalsIgnoreCase("T")) {
                        planner.removeMealFromPlanner(2);
                    } else if (day.equalsIgnoreCase("W")) {
                        planner.removeMealFromPlanner(3);
                    } else if (day.equalsIgnoreCase("H")) {
                        planner.removeMealFromPlanner(4);
                    } else if (day.equalsIgnoreCase("F")) {
                        planner.removeMealFromPlanner(5);
                    } else if (day.equalsIgnoreCase("S")) {
                        planner.removeMealFromPlanner(6);
                    } else {
                        System.out.println("Invalid commnand.");
                    }
                } else if (command3.equalsIgnoreCase("V")) {
                    System.out.println(planner.getPlannedMeals());
                } else {
                    System.out.println("Invalid command.");
                }
                // allow user to add and remove meals to planner and view
            } else if (command.equalsIgnoreCase("L")) {
                // display shopping list
            } else {
                System.out.println("Invalid command. ");
            }
            System.out.println("Please enter a command from the main menu: ");
        }
        System.out.println("Before you quit, would you like to save your cookbook (Y/N)? ");
        command = input.next();
        if (command.equalsIgnoreCase("Y")) {
            System.out.println("Please enter the name of the file you want to save to: ");
            String filename = input.next();
            FileIO.saveToFile(cb, filename);
        }
        System.out.println("\nThank you for using Weekly Menu Planner! Goodbye!");
        input.close();
    }
}
