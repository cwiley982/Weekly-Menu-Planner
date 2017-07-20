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
			while (!command.equalsIgnoreCase("Q")) {
				if (command.equalsIgnoreCase("C")) {
					System.out.println(planner.getPlannerMeals().toString());
				} else if (command.equalsIgnoreCase("P")) {
					// allow user to add and remove meals and view current
					// planner
				} else if (command.equalsIgnoreCase("L")) {
					// display shopping list
				}
			}
			System.out.println("Please enter a command from the options above: ");
		}
		input.close();
	}
}
