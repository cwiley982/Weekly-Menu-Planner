package personal_projects.weekly_menu_planner.gui;

import java.util.Scanner;

import personal_projects.weekly_menu_planner.planner.Planner;
import personal_projects.weekly_menu_planner.util.Meal;

public class CommandLineSimulator {

	private static Planner planner;

	public static void main(String[] args) {
		planner = new Planner();
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Weekly Meal Planner! Here, you can add meals to a \"cook book\", "
						+ "\nplan your meals for the week, and create a shopping list for your meals."
						+ " \nEnter \"C\" to manage the cookbook, \"P\" to plan meals, and \"L\" to view your"
						+ " \nshopping list. You can also enter \"Q\" to quit.");
		System.out.println("\nPlease enter a command from the options above: ");
		while (input.hasNext()) {
			String command = input.next();
			if (!command.equalsIgnoreCase("Q")) {
				if (command.equalsIgnoreCase("C")) {
					System.out.println("Would you like to add a meal (\"A\"), "
							+ "remove a meal (\"R\"), or view your cookbook (\"V\")? ");
					String command2 = input.next();
					if (command2.equalsIgnoreCase("A")) {
						// hey :)
						//
						//
						//
						//
						//
						//
						//
						// THE PROBLEM IS RIGHT HERE vvv
						System.out.println("Enter the name of the meal: ");
						String mealName = input.nextLine();
						Meal meal = new Meal(mealName);
						System.out.println("Enter the number of ingridients for this meal: ");
						int numOfIng = input.nextInt();
						for (int i = 0; i < numOfIng; i++) {
							System.out
									.println("Enter ingredient #" + i + " in the format as follows: name amount unit");
							String name = input.next();
							int amt = input.nextInt();
							String unit = input.next();
							meal.addIngredient(name, amt, unit);
						}
						planner.addMealToCookBook(meal);
						System.out.println("Meal successfully added!");
						// maybe ask if they want to add another meal (won't be
						// in gui)
					} else if (command2.equalsIgnoreCase("R")) {
						// allow them to remove a meal
						System.out.println("Enter the name of the meal you wish to remove: ");
						String mealName = input.nextLine();
						planner.removeMealFromCookBook(mealName);
						// this might have the same problem ^^^
					} else if (command2.equalsIgnoreCase("V")) {
						System.out.println(planner.getCookBookAsString());
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
						// add a meal to planner
					} else if (command3.equalsIgnoreCase("R")) {
						System.out.println("What day would you like to remove a meal from? (U, M, T, W, H, F, S) ");
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
					System.out.println("Invalid command.");
				}
			} else {
				System.out.println("\nThank you for using Weekly Menu Planner! Goodbye!");
				break;
				// think this is in the right place ^^
			}
			System.out.println("Please enter a command from the options above: ");
		}
		input.close();
	}
}
