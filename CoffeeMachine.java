package machine;
import org.apache.logging.log4j.*;
import java.util.Scanner;

/**
 * @author Sinéad Eriksson
 *
 */
public class CoffeeMachine {

	private static Logger machinelogger = LogManager.getLogger(CoffeMachine.class.getName());
	private static Scanner scanner = new Scanner(System.in);
	private static String[] products = {"Café", "Leche", "Chocolate", "Azúcar"};
	private static int[] inventory = {0,0,0,0};
	private static int[] inventoryLimit = {10, 80, 10, 40};
	
	private static boolean buyDrink() {
		System.out.println("Cuál bebida quieres comprar?");
		
		//TODO
		
		
		
		return true;
	}
	
	private static void printInventory() {
		System.out.println("El inventorio hasta ahora esta como suigiente:\nUnidades\tProducto");
		for(int i = 0; i<products.length; i++) {
			System.out.println(String.format("%d\t%s", inventory[i], products[i]));
		}
		return;
	}
	
	private static void addInventory(int permission) {
		if(permission==0) {
			System.out.println("Lo siento, debes que ser el inventor para tener accesso a está acción.\nSe le está redirigiendo al menú principal.");
			return;
		} else { //Doesn't matter if they entered some other int, as long as it wasn't zero we're fine!
			System.out.println("Si quieres agregar al inventario, entra el número que corresponde al producto:");
		    System.out.println("[0] - Café (en polvo)\n[1] - Leche (en polvo)\n[2] - Chocolate (en polvo)\n[3] - Azúcar");
		    int product_int = scanner.nextInt();
		    
		    if(product_int>=products.length && product_int>=0) {
		    	System.out.println("Debes que entrar el número de un de los productos que están escribiendo arriba.\nAcción cancelada.");
		    	return;
		    }
		    String product_str = products[product_int];
		    System.out.println(String.format("Cuántos unidades de %s quieres agregar?", product_str));
		    int amount = scanner.nextInt();
		    int newAmount = inventory[product_int] + amount;
		    if(amount >= 0 && newAmount <= inventoryLimit[product_int]) {
		    	
		    	inventory[product_int] = newAmount;
		    	System.out.println(String.format("Agregado %d unidades %s al inventario.", amount, product_str));
		    	System.out.println(String.format("Ahora hay %d unidades de %s en la máquina.", inventory[product_int], product_str));
		    } else if(amount > 0 && newAmount >= inventoryLimit[product_int]) { //Unnecessary to include second statement
		    	inventory[product_int] = inventoryLimit[product_int];
		    	
		    	System.out.println(String.format("La máquina está llena! Agregamos %d unidades de %s.", inventoryLimit[product_int]-(newAmount-amount), product_str ));
		    	System.out.println(String.format("Ahora hay %d unidades de %s en la máquina.", inventory[product_int], product_str));
		    	
		    } else {

		    	System.out.println(String.format("No podemos agregar %s a la máquina. Porfa revisa el cantidad de unidades.", product_str));
		    }
		    
		    return;
		}
	}
	
	public static void main(String[] args) {
		
		//Create the three initial recipes
		Recipe sweetCoffee = new Recipe("Café Dulce", 25, 2, 0, 0, 4);
		Recipe hotChocolate = new Recipe("Chocolate Calaiente", 35, 1, 5, 3, 5);
		Recipe glassOfMilk = new Recipe("La Sueca", 5, 0, 15, 0, 0);

		System.out.println("Welcome to the coffee machine!");
		machinelogger.info("Machine Launched");
		machinelogger.error("Blev knas");
		machinelogger.debug("runs debug");
		

	    //  prompt for the user's name
	    System.out.println("Hay tres opciones diferentes de entrada del usuario: ");
	    System.out.println("[1] - Agregar inventario\n[2] - Verificar inventario\n[3] - Comprar bebida");
	    System.out.println("Entrar el numero de tu tipo de acción: ");

	    // get the action as an int
	    int action = scanner.nextInt();
	    
	    System.out.println(String.format("Eligiste %d", action));
	    
	    System.out.println("¿Eres el inventor?: \n[0] - no soy el inventor\n[1] - sí, ¡soy yo!");
	    int permission = scanner.nextInt();
	    addInventory(permission);
	    
	    boolean drinkBought = buyDrink();
	    if (!drinkBought){
	    	System.out.println("Lo siento. Bebida no comprada, debrías intentar de nuevo.");
	    }


	}

}
