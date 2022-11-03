package RBI;


import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class RBIbank {
	public static void main(String[] args) {
		
		
		System.err.println("***** welcome to National Banking System *****");
		System.err.println("\n");
		System.err.println("Do you want to open an account: 1.Yes 2.No");

		Scanner sc = new Scanner(System.in);
		
		String choice = sc.nextLine();
		if (choice.equalsIgnoreCase("Yes")) {
			openAccount obj = new openAccount();
			obj.createAccount();
		}
		if (choice.equalsIgnoreCase("No")) {
			BankAccount obj = new BankAccount();
			obj.showMenu();
		}
	}
}

class openAccount {
	String name;
	int accountNum;
	String AccountType;
	String dateOfBrith;
	String Bank;

	void createAccount() {
		Scanner sc = new Scanner(System.in);
		System.err.println("In which bank you want to open it: 1.SBI 2.AndharaBank 3.CanadaBank");
		int choiceBank = sc.nextInt();
		if (choiceBank == 1) {
			Bank = "SBI";
		}
		else if (choiceBank == 2) {
			Bank = "AndharaBank";
		}
		else if (choiceBank == 3) {
			Bank = "CanadaBank";
		}
		System.err.println("please enter your name: ");
		sc.nextLine();
		name = sc.nextLine();
		System.err.println("please enter your date of birth: ");

		dateOfBrith = sc.nextLine();

		System.err.println("What type of account you want to open: 1.saving 2.current");
		int choice = sc.nextInt();
		if (choice == 1) {
			AccountType = "saving";
		}
		if (choice == 2) {
			AccountType = "current";
		}

		System.err.println("Your account has been opended with following details");
		System.err.println("Bank " + Bank);
		System.err.println("Name " + name);
		System.err.println("DOB " + dateOfBrith);
		System.err.println("AccountType " + AccountType);
		System.err.println("AccountNum: " + Math.random());

		System.err.println("\n");

		BankAccount obj1 = new BankAccount();
		obj1.showMenu();
		sc.close();

	}
}

class BankAccount {
	long Balance;
	long PrivousTransaction;
	String customerName;
	String customerId;
	String accountType;
	double TotalInterest;

	void calulateInterest(long Balance){
		System.err.println("What type of Account you have: 1.saving 2.current");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if (choice == 1) {
			accountType = "Saving";
			int r = 5;
			int t;
			System.err.println("Enter year to calculate interest");
			t = sc.nextInt();

			double finialAmount = Balance * (1 + r * t);

			TotalInterest = finialAmount - Balance;
			System.err.println("Total interest earned is: " + TotalInterest);

		}
		if (choice == 2) {
			accountType = "current";
			int r = 8;
			int t, n;
			System.err.println("Enter year to calculate interest");
			t = sc.nextInt();
			System.err.println("Enter the frequency that interest has been compound in a year");
			n = sc.nextInt();
			double finialAmount = Balance * (Math.pow((n*t), 1 + r / n));

			TotalInterest = finialAmount - Balance;
			System.err.println("Total interest earned is: " + TotalInterest);
			sc.close();

		}
	}

	void deposit(int amount) {
		if (amount != 0) {
			Balance = Balance + amount;
			System.err.println("Balance after deposite " + Balance);
			PrivousTransaction = amount;

		}
	}

	void withdraw(int amount) {
		if (amount != 0) {
			Balance = Balance - amount;
			System.err.println("Balance after withdraw  " + Balance);
			PrivousTransaction = -amount;

		}
	}

	void getPrivousTransaction() {
		FileOutputStream output;
		PrintStream p;
		try {
			output = new FileOutputStream("k.txt");
			p = new PrintStream(output);
			if (PrivousTransaction > 0) {
				p.append("Deposite: " + PrivousTransaction);
				System.err.println("Deposite: " + PrivousTransaction);
			} else if (PrivousTransaction < 0) {
				p.append("Withdrawn: " + PrivousTransaction);
				System.err.println("Withdrawn: " + Math.abs(PrivousTransaction));

			} else {
				System.err.println("No Transaction occured");
			}
			p.close();
		} catch (Exception e) {
			System.err.println("Error in printing the data" + e);
		}
	}

	void showMenu() {
		char option = '\0';
		Scanner sc = new Scanner(System.in);
		System.err.println("Weclome to the menu");
		System.err.println("\n");
		System.err.println("A. check Balance ");
		System.err.println("B. Deposite Amount ");
		System.err.println("C. withdraw Amount ");
		System.err.println("D. see previous transaction ");
		System.err.println("E. calculate Interest");
		System.err.println("F. Exit");

		do {
			System.err.println("********************");
			System.err.println("Enter an option");
			System.err.println("**********************");
			option = sc.next().charAt(0);
			System.err.println("\n");
			switch (option) {
			case 'A':
				System.err.println("---------------------");
				System.err.println("Balance =" + Balance);
				System.err.println("\n");
				break;
			case 'B':
				System.err.println("---------------------");
				System.err.println("Enter the amount deposite =");
				int amount = sc.nextInt();
				deposit(amount);
				System.err.println("\n");
				break;
			case 'C':
				System.err.println("---------------------");
				System.err.println("Enter the amount withdraw = ");
				int amount2 = sc.nextInt();
				deposit(amount2);
				System.err.println("\n");
				break;
			case 'D':
				System.err.println("---------------------");
				getPrivousTransaction();
				System.err.println("\n");
				break;
			case 'E':
				System.err.println("---------------------");
				calulateInterest(Balance);
				System.err.println("\n");
				break;
			case 'F':
				System.err.println("Enter Invalid option!. please try again");
				break;
			}

		} while (option != 'F');
		System.err.println("Thank you for using our services");
		sc.close();
	}

}
