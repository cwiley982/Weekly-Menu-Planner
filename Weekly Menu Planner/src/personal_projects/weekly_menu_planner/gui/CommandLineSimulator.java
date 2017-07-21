package personal_projects.weekly_menu_planner.gui;

import java.util.Scanner;

import personal_projects.weekly_menu_planner.planner.Planner;

public class CommandLineSimulator {

	private static Planner planner;

	public static void main(String[] args) {
		planner = new Planner();
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Welcome to the Weekly Meal Planner! Here, you can add meals to a \"cook book\", "
						+ "\nplan your meals for the week, and create a shopping list for your meals."
						+ " \nEnter \"C\" to manage the cookbook, \"P\" to plan meals, and \"L\" to view your"
						+ " \nshopping list. You can also enter \"Q\" to quit.");
		System.out.println("\nPlease enter a command from the options above: ");
		while (input.hasNext()) {
			String command = input.next();
			if (!command.equalsIgnoreCase("Q")) {
				if (command.equalsIgnoreCase("C")) {
					System.out.println(
							"Would you like to add a meal (\"A\"), remove a meal (\"R\"), or view your cookbook (\"V\")? ");
					// maybe give option to go back out to main menu
					String command2 = input.next();
					if (command2.equalsIgnoreCase("A")) {
						// allow them to add a meal
					} else if (command2.equalsIgnoreCase("R")) {
						// allow them to remove a meal
					} else if (command2.equalsIgnoreCase("V")) {
						System.out.println(planner.getCookBookAsString());
					} else {
						System.out.println("Invalid command. Please try again.");
						// need a while loop somewhere to re-prompt after each
						// command entry
					}
				} else if (command.equalsIgnoreCase("P")) {
					// allow user to add and remove meals to planner and view
					// current
					// planner
				} else if (command.equalsIgnoreCase("L")) {
					// display shopping list
				} else {
					System.out.println("Invalid command. Please try again.");
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
