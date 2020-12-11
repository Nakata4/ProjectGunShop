package GunShopSales;
import java.util.Scanner;

public class Login {
public Login(GunSalesSystem gunSalesSystem) {
	
	}

public static void main(String args[]) {
	Scanner Naka = new Scanner(System.in);
	String idInput;
	System.out.println("Please type ID: ");
	idInput = Naka.nextLine();
	String passwordInput;
	System.out.print("Please type Password: ");
	passwordInput = Naka.nextLine();
	
	if(passwordInput.equals("you") && (idInput.equals("tube"))) {
		
		System.out.println("Authentification complete!");
	}else {
		System.out.println("Type ID or Password!");
	}
}
}
