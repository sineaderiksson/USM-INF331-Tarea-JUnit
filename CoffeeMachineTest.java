package machine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * @author Sinéad Eriksson
 *
 */
public class CoffeeMachineTest {
	//Create new machine
	CoffeeMachine machine = new CoffeeMachine();
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}

	/*
	@Test
	public void testReadInputNum() {
		
		//Arrange
		for(int i = 0; i < machine.inventory.length; i++) {
			machine.addInventory(i, machine.products[i], i);
		}
		
		//Act
		
		
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
		System.setIn(in);

		// do your thing

		// optionally, reset System.in to its original
		System.setIn(sysInBackup);
		
		
		//Assert
		System.out.println("Hello Baeldung Readers!!");
        
	    assertEquals("Hello Baeldung Readers!!", outputStreamCaptor.toString().trim());
		
	}*/
	
	@Test
	public void testPrintEmptyInventory() {
		String expected = "El inventorio hasta ahora esta como suigiente:\nUnidades	Producto\n0	Café\n0	Leche\n0	Chocolate\n0	Azúcar";
		String actual = machine.printInventory(new StringBuilder()).toString();
		
		//Assert
		assertEquals(expected, actual);
	}
	
	/*
	public void testBuyDrinkEmpty() {
		//Arrange
		String expected = "Lo siento, pero no hay suficiente unidades en el inventario para preparar tu bebida.\nPorfa contacta el inventor!";
		
		//Act
		boolean b = machine.buyDrink(0);
		String actual = machine.sbGlob.toString();
		
		assertThat(containsString(expected), actual);
	}
	
	public void testInsufficientFunda() {
		//Recipe choice_recipe = machine.glassOfMilk;
		int money = 1;
		String expected = "Lo siento, pero %d no es suficiente para comprar %s, que cuesta %d monedas.\n%dmonedas returnados.", money, choice_recipe.name, choice_recipe.price, money)
	
		//Act machine.checkMoney(money, choice_recipe);
	}
	*/
	
	/*
	public void testAddInventory() {
		String expected = "-12\nNúmero debe ser un integer positivo.\nVolverás a entrar el numero:";
		InputStream stdin = System.in;
	    System.setIn(new ByteArrayInputStream("1\n1\n1\n-12".getBytes()));
	
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(byteArrayOutputStream);
	    PrintStream stdout = System.out;
	    System.setOut(ps);
		
	    CoffeeMachine.main(new String[0]);
	    
	    System.setIn(stdin);
	    System.setOut(stdout);
	    
	    String outputText = byteArrayOutputStream.toString();
	    String key = "¿Cuántos unidades de Café quieres agregar?";
	    String output = outputText.substring(outputText.indexOf(key) + 30).trim();
	    assertEquals(output, expected);
	    
	    

	}
	
	public void testAddInventory() {
		String expected = "-12\nNúmero debe ser un integer positivo.\nVolverás a entrar el numero:";
	
	    String userInput = String.format("1%s1%s1%s-12",
	    		System.lineSeparator(),
	    		System.lineSeparator(),
	            System.lineSeparator());
	    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
	    System.setIn(bais);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream printStream = new PrintStream(baos);
	    System.setOut(printStream);
	    
	    CoffeeMachine.main(null);
	    String[] lines = baos.toString().split(System.lineSeparator());
	    String actual = lines[lines.length-1]+lines[lines.length-2]+lines[lines.length-3];	    String first = lines[0];
	    assertEquals("Hola y bienvenidos a nuestra maqúina de café!", lines[0]);
	   // assertEquals(expected,actual);
	}
	*/
	
	@After
	public void tearDown() {
	    System.setOut(standardOut);
	}

}
