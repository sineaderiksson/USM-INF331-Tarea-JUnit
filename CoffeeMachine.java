package machine;
import org.apache.logging.log4j.*;
import java.util.Scanner;

/**
 * @author Sinéad Eriksson
 *
 */
public class CoffeeMachine {

	//Must be public for tests
	private static Logger logger = LogManager.getLogger(CoffeeMachine.class.getName());
	private static Scanner scanner = new Scanner(System.in);
	public static String[] products = {"Café", "Leche", "Chocolate", "Azúcar"};
	public static int[] inventory = {0,0,0,0};
	private static int[] inventoryLimit = {10, 80, 10, 40};
	private static Recipe sweetCoffee, hotChocolate, glassOfMilk;
	//private static StringBuilder sb = new StringBuilder();
	
	
	/**
	 * 
	 * @param n Highest int allowed as action 
	 * @return An approved int
	 */
	public static int readInputNum(int n) {
		//If it must be an int within the range of [0,n]
		int input;
		boolean complete = false;
			do {			
				while (!scanner.hasNextInt()) {
					scanner.next(); 
					System.out.println("Debe ser un integer positivo. \nVolverás a entrar el numero:");
				}
				   input = scanner.nextInt();
				   if(input < 0 || input > n) {
					   System.out.println(String.format("Número debe ser un integer positivo entre 0 y %d. \nVolverás a entrar el numero:", n));
						complete = false;
				   } else {
					   complete = true; //Unnecessary assign
					   return input;					   
				   }
			}while(!complete);
			
			return 0; //Should never get here. 
	}
	
	public static int readInputNum() {
		//Input just has to be a positive int
		int input;
		boolean complete = false;
		
		do {			
			while (!scanner.hasNextInt()) {
				scanner.next(); 
				System.out.println("Debe ser un integer positivo. \nVolverás a entrar el numero:");
			}
			   input = scanner.nextInt();
			   if(input < 0) {
					System.out.println(String.format("Número debe ser un integer positivo. \nVolverás a entrar el numero:"));
					complete = false;
			   } else {
				   complete = true; //Unnecessary assign
				   return input;					   
			   }
		}while(!complete);
		
		return 0;
	}
	
	private static boolean checkMoney(int money, Recipe choice_recipe) {
		if(money<choice_recipe.price) {
	    	System.out.println(String.format("Lo siento, pero %d no es suficiente para comprar %s, que cuesta %d monedas.\n%dmonedas returnados.", money, choice_recipe.name, choice_recipe.price, money));
	    	return false;
	    }else { 	    	
	    	int change = money-choice_recipe.price;
	    	if(change != 0) {
	    		System.out.println(String.format("Recibes %d monedas como cambio. Tu bebida está en camino...", change));
	    	}
			inventory[0]-=choice_recipe.coffeeUnits;
			inventory[1]-=choice_recipe.milkUnits;
			inventory[2]-=choice_recipe.chocolateUnits;
			inventory[3]-=choice_recipe.sugarUnits;
			
			System.out.println("Su bebida "+choice_recipe.name+" esta lista.");
			return true;
			
	    }
	}
	
	static boolean buyDrink(int choice_int) {
	    
	    Recipe choice_recipe;
	    
	    switch(choice_int) {
		    case 1:
		    	choice_recipe = sweetCoffee;
		    	break;
		    case 2:
		    	choice_recipe = hotChocolate;
		    	break;
		    case 3:
		    	choice_recipe = glassOfMilk;
		    	break;
		    default:
		    	System.out.println("No elegiste una bebida que oferesamos, acción terminada.\nVolvimos al menú pricipal.");
		    	return false;
	    }
	    	if(inventory[0]<choice_recipe.coffeeUnits || inventory[1]<choice_recipe.milkUnits || inventory[2]<choice_recipe.chocolateUnits || inventory[3]<choice_recipe.sugarUnits) {
	    		System.out.println("Lo siento, pero no hay suficiente unidades en el inventario para preparar tu bebida.\nPorfa contacta el inventor!");
	    		return false;
	    	} else {
	    		System.out.println(String.format("Porfa pone monedas en la máquina, el %s cuesta %dmonedas.", choice_recipe.name, choice_recipe.price));
	    		System.out.println("¿Cuantás monedas pones en la máquina?");
	    	    int money = readInputNum();
	    	    return checkMoney(money, choice_recipe);
	    	    
	    		
	    	}
	}
	
	public static StringBuilder printInventory(StringBuilder sb) {
		sb.append("El inventorio hasta ahora esta como suigiente:\nUnidades\tProducto");
		for(int i = 0; i<products.length; i++) {
			sb.append(String.format("\n%d\t%s", inventory[i], products[i]));
		}
		return sb;
	}
	
	public static void addInventory(int product_int, String product_str, int amount) {
    	logger.info("Intenting to add to inventory.");
		int newAmount = inventory[product_int] + amount;
	    if(amount >= 0 && newAmount <= inventoryLimit[product_int]) {	//First statement unnecessary numera
	    	logger.info("Adding units of product in inventory.");
	    	
	    	inventory[product_int] = newAmount;
	    	System.out.println(String.format("Agregado %d unidades %s al inventario.", amount, product_str));
	    	System.out.println(String.format("Ahora hay %d unidades de %s en la máquina.", inventory[product_int], product_str));
	    } else if(amount > 0 && newAmount >= inventoryLimit[product_int]) { //Unnecessary to include second statement
	    	inventory[product_int] = inventoryLimit[product_int];
	    	
	    	System.out.println(String.format("La máquina está llena! Agregamos %d unidades de %s.", inventoryLimit[product_int]-(newAmount-amount), product_str ));
	    	System.out.println(String.format("Ahora hay %d unidades de %s en la máquina.", inventory[product_int], product_str));
	    	
	    } else {
	    	//Will never get here!
	    	System.out.println(String.format("No podemos agregar %s a la máquina. Porfa revisa el cantidad de unidades.", product_str));
	    	logger.info("Adding units of product in inventory.");
	    }
	}
	
	public static void checkAddInventory(int product_int) {
		logger.info("Controlling which product should be incremented");
	    if(product_int == 0) {	//User wished to cancel action
	    	logger.info("No real product from inventory chose, no inventory increased.");
	    	System.out.println("Eligiste que no continuar, volvimos al menú principal.");
	    	return;
	    }
	    product_int--;	//product_int now represents index of product in the lists.
	    //WATCH OUT!!
	    String product_str = products[product_int];
	    System.out.println(String.format("¿Cuántos unidades de %s quieres agregar?", product_str));
	    int amount = readInputNum();
	    addInventory(product_int, product_str, amount);
	    
	    
	    return;
	
	}
	
	public static void checkInventoryPermission(int permission) {
		logger.info("Confirming if user has permission to add inventory");
		if(permission==0) {
			System.out.println("Lo siento, debes que ser el inventor para tener accesso a está acción.\nSe le está redirigiendo al menú principal.");
			return;
		} else { //Doesn't matter if they entered some other int, as long as it wasn't zero we're fine!
			System.out.println("Si quieres agregar al inventario, entra el número que corresponde al producto:");
		    System.out.println("[1] - Café (en polvo)\n[2] - Leche (en polvo)\n[3] - Chocolate (en polvo)\n[4] - Azúcar");
		    int product_int = readInputNum(4);
		    
		    checkAddInventory(product_int);
		}
		
	}
	
	public static boolean performAction(int action) {
		logger.info("determining which action is being performed");
		boolean terminate = false;
	    switch (action) {
		    case 1:
		    	logger.info("Selected action was add to inventory");
		    	System.out.println("¿Eres el inventor?: \n[0] - no soy el inventor\n[1] - sí, ¡soy yo!");
			    int permission = readInputNum();
			    checkInventoryPermission(permission);
		    	break;
		    	
		    case 2:
		    	logger.info("Selected action was print inventory");
		    	StringBuilder sb = new StringBuilder();
		    	sb = printInventory(sb);
		    	System.out.println(sb.toString());
		    	break;
		    	
		    case 3:
		    	logger.info("Selected action was buy drink");
				System.out.println("Cuál bebida quieres comprar?");
			    System.out.println("[1] - Café Dulce\n[2] - Chocolate Calaiente\n[3] - La Sueca");
			    int choice_int = readInputNum(3);
		    	buyDrink(choice_int);
		    	break;
	    	default:
	    		terminate = true;
	    }
	    return terminate;
		
	}
	
	public static boolean runMenu() {
		
		boolean terminate = false;
		
		logger.info("printing menu");
	    System.out.println("Hay tres opciones diferentes de entrada del usuario: ");
	    System.out.println("[1] - Agregar inventario\n[2] - Verificar inventario\n[3] - Comprar bebida");
	    System.out.print("Entra el numero de tu tipo de acción: ");

	    // get the action as an int
	    int action = readInputNum();
	    terminate = performAction(action);
	    
	    
	    return terminate;
	}
	
	public static void main(String[] args) {
		logger.info("initiating machine");
		
		//Create the three initial recipes
		sweetCoffee = new Recipe("Café Dulce", 25, 2, 0, 0, 4);
		hotChocolate = new Recipe("Chocolate Calaiente", 35, 1, 5, 3, 5);
		glassOfMilk = new Recipe("La Sueca", 5, 0, 15, 0, 0);

		logger.info("Machine Launched");
		

	    //Start Coffee Machine
	    System.out.println("Hola y bienvenidos a nuestra maqúina de café!\n\n");
	    
	    boolean terminate = false;
	    while(!terminate) {	//Add the option to terminate execution
	    	terminate = runMenu();
	    }
	    
	    System.out.println("Gracias para todo, nos vemos.");
	    logger.info("session ended");
	}

}
