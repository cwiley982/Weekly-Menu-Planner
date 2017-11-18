package personal_projects.weekly_menu_planner.util;

public class Meal implements Comparable<Meal> {

	private String mealName;
	private ArrayList<Ingredient> ingredients;

	public Meal(String name) {
		setMealName(name);
		ingredients = new ArrayList<Ingredient>();
	}

	private void setMealName(String name) {
		if (name.isEmpty() || name == null) {
			throw new IllegalArgumentException("Invalid Meal: Meal Name cannot be null or empty");
		}
		mealName = name;
	}

	public String getMealName() {
		return mealName;
	}

    public void addIngredient(String name, double amount, String unit)
            throws IllegalArgumentException {
		Ingredient ing = new Ingredient(name, amount, unit);
		ingredients.add(ing);
	}

	public boolean removeIngredient(String name) {
		for (int i = 0; i < ingredients.size(); i++) {
			if (ingredients.get(i).getName().equals(name)) {
				ingredients.remove(i);
				return true;
			}
		}
		return false;
	}

	public String[][] getIngredientsArray() {
		String[][] array = new String[ingredients.size()][3];
		for (int i = 0; i < ingredients.size(); i++) {
			array[i] = ingredients.get(i).getArray();
		}
		return array;
	}

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(mealName).append("\n");
        for (int i = 0; i < ingredients.size(); i++) {
            str.append("\t").append(ingredients.get(i).toString()).append("\n");
        }
        return str.toString();
    }
    
    @Override
    public int compareTo(Meal o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
