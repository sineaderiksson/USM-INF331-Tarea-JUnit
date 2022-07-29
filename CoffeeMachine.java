package machine;
import org.apache.logging.log4j.*;
import java.util.Scanner;

/**
 * @author Sinéad Eriksson
 *
 */
public class CoffeMachine {

	private static Logger machinelogger = LogManager.getLogger(CoffeMachine.class.getName());
	private static Scanner scanner = new Scanner(System.in);
	private static String[] products = {"Café", "Leche", "Chocolate", "Azúcar"};
	private static int[] inventory = {0,0,0,0};
	private static int[] inventoryLimit = {10, 80, 10, 40};
	private static Recipe sweetCoffee, hotChocolate, glassOfMilk;
	
	//TODO
	//- Change all indices from 0, so that input=0 can be used to return to main menu.
	
	private static int readInputNum(int n) {
		//If it must be an int within the range of [0,n]
		int input;
		boolean complete = false;
			do {			
				while (!scanner.hasNextInt()) scanner.next(); 
				   input = scanner.nextInt();
				   if(input < 0 || input > n) {
					   System.out.println(String.format("Número debe ser un integer positivo entre 0 y %d.", n));
						complete = false;
				   } else {
					   complete = true; //Unnecessary assign
					   return input;					   
				   }
			}while(!complete);
			
			return 0; //Should never get here. 
	}
	
	private static int readInputNum() {
		//Input just has to be a positive int
		int input;
		boolean complete = false;
		
		do {			
			while (!scanner.hasNextInt()) scanner.next(); 
			   input = scanner.nextInt();
			   if(input < 0) {
					System.out.println(String.format("Número debe ser un integer positivo."));
					complete = false;
			   } else {
				   complete = true; //Unnecessary assign
				   return input;					   
			   }
		}while(!complete);
		
		return 0;
	}
	
	private static boolean buyDrink() {
		System.out.println("Cuál bebida quieres comprar?");
	    System.out.println("[1] - Café Dulce\n[2] - Chocolate Calaiente\n[3] - La Sueca");
	    int choice_int = readInputNum(3);
	    
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
	    			
	    	    }
	    		
	    	}
		
		return true;
	}
	
	private static void printInventory() {
		System.out.println("El inventorio hasta ahora esta como suigiente:\nUnidades\tProducto");
		for(int i = 0; i<products.length; i++) {
			System.out.println(String.format("%d\t%s", inventory[i], products[i]));
		}
		return;
	}
	
	private static void addInventory() {
		

	    System.out.println("¿Eres el inventor?: \n[0] - no soy el inventor\n[1] - sí, ¡soy yo!");
	    int permission = readInputNum();
	    
		if(permission==0) {
			System.out.println("Lo siento, debes que ser el inventor para tener accesso a está acción.\nSe le está redirigiendo al menú principal.");
			return;
		} else { //Doesn't matter if they entered some other int, as long as it wasn't zero we're fine!
			System.out.println("Si quieres agregar al inventario, entra el número que corresponde al producto:");
		    System.out.println("[1] - Café (en polvo)\n[2] - Leche (en polvo)\n[3] - Chocolate (en polvo)\n[4] - Azúcar");
		    int product_int = readInputNum(4);
		    
		    if(product_int == 0) {	//User wished to cancel action
		    	System.out.println("Eligiste que no continuar, volvimos al menú principal.");
		    	return;
		    }
		    /* Not needed anymore iom. check-input-number-function
		    //Check if they just want to exit without adding
		    if(product_int>=products.length && product_int>=0) {
		    	System.out.println("Debes que entrar el número de un de los productos que están escribiendo arriba.\nAcción cancelada.");
		    	return;
		    }
		    */
		    product_int--;	//product_int now represents index of product in the lists.
		    //WATCH OUT!!
		    String product_str = products[product_int];
		    System.out.println(String.format("¿Cuántos unidades de %s quieres agregar?", product_str));
		    int amount = readInputNum();
		    int newAmount = inventory[product_int] + amount;
		    if(amount >= 0 && newAmount <= inventoryLimit[product_int]) {	//First statement unnecessary numera
		    	
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
		    }
		    
		    return;
		}
	}
	
	public static boolean runMenu() {
		
		boolean terminate = false;

	    System.out.println("Hay tres opciones diferentes de entrada del usuario: ");
	    System.out.println("[1] - Agregar inventario\n[2] - Verificar inventario\n[3] - Comprar bebida");
	    System.out.print("Entra el numero de tu tipo de acción: ");

	    // get the action as an int
	    int action = readInputNum();
	    
	    
	    switch (action) {
		    case 1:
		    	addInventory();
		    	break;
		    	
		    case 2:
		    	printInventory();
		    	break;
		    	
		    case 3:
		    	buyDrink();
		    	break;
	    	default:
	    		terminate = true;
	    }
	    
	    return terminate;
	}
	
	public static void main(String[] args) {
		
		//Create the three initial recipes
		sweetCoffee = new Recipe("Café Dulce", 25, 2, 0, 0, 4);
		hotChocolate = new Recipe("Chocolate Calaiente", 35, 1, 5, 3, 5);
		glassOfMilk = new Recipe("La Sueca", 5, 0, 15, 0, 0);

		machinelogger.info("Machine Launched");
		machinelogger.error("Blev knas");
		machinelogger.debug("runs debug");
		

	    //Start Coffee Machine
	    System.out.println("Hola y bienvenidos a nuestra maqúina de café!\n\n");
	    
	    boolean terminate = false;
	    while(!terminate) {	//Add the option to terminate execution
	    	terminate = runMenu();
	    }
	    
	    System.out.println("Gracias para todo, nos vemos.");
	}

}
