package personal_projects.weekly_menu_planner.util;

public class Ingredient {
	private String unit;
	private int amount;
	private String name;

	public Ingredient(String name, int amount, String unit) {
		setName(name);
		setAmount(amount);
		setUnit(unit);
	}
	private void setName(String name) {
		if (name.isEmpty() || name == null) {
			throw new IllegalArgumentException("Invalid Ingredient: Name cannot be null or empty.");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private void setAmount(int amt) {
		if (amt <= 0) {
			throw new IllegalArgumentException(
					"Invalid Ingredient: Amount cannot be zero, negative or contain letters.");
		}
		// check for letters in amt
		amount = amt;
	}

	public int getAmount() {
		return amount;
	}

	private void setUnit(String unit) {
		if (unit == null || unit.isEmpty()) {
			throw new IllegalArgumentException("Invalid Ingredient: Unit cannot be null or empty.");
		}
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public String[] getArray() {
		String[] array = new String[3];
		array[0] = getName();
		array[1] = Integer.toString(getAmount());
		array[2] = getUnit();
		return array;
	}
}
