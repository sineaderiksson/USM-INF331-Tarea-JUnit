/**
 * 
 */
package machine;
import java.io.*;

/**
 * @author Sinéad Eriksson
 *
 */
public class Recipe {
	
	String name;
	int price;
	int coffeeUnits;
	int milkUnits;
	int chocolateUnits;
	int sugarUnits;
	
	//Constructor
	public Recipe(String name, int price, int coffee, int milk, int chocolate, int sugar) {
		this.name = name;
		this.price = price;
		this.coffeeUnits = coffee;
		this.milkUnits = milk; 
		this.chocolateUnits = chocolate;
		this.sugarUnits = sugar;
	}
	
	public void printRecipe() {
		System.out.println();
	}

}
