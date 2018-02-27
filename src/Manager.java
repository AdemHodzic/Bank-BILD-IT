import java.util.Scanner;

public class Manager {
	private Database database = new Database();
	
	Manager(){}
	
	public void init() throws Exception {
		Scanner input = new Scanner(System.in);
		database.read();
		while(true) {
			System.out.println("Hello user! Choose your option:"
					+ "\n1.Create an account"
					+ "\n2.Transfer assets"
					+ "\n3.Print details"
					+ "\n4.Exit");
			int option = input.nextInt();
			input.nextLine();
			switch(option) {
			case 1:
				System.out.println("Input account name: ");
				String name = input.nextLine();
				System.out.println("Input account number: ");
				int number = input.nextInt();
				database.createAccount(number, name);
				break;
			case 2:
				System.out.println("Emter source account: ");
				int sourceNumber = input.nextInt();
				System.out.println("Enter target account: ");
				int targetAccount = input.nextInt();
				if(database.getAccount(sourceNumber)!=null && database.getAccount(targetAccount)!=null) {
					System.out.println("Enter amount you want to transfer: ");
					double amount = input.nextDouble();
					database.transfer(database.getAccount(sourceNumber), database.getAccount(targetAccount), amount);
				}else {
					System.out.println("Wront accounts entered!\n"
							+ "We're sorry for inconvience");
				}
				break;
			case 3:
				System.out.println("Enter account number: ");
				int detailsNumber = input.nextInt();
				database.details(detailsNumber);
				break;
			case 4:
				System.out.println("Thank you for using our app!\n"
						+ "Have a nice day!");
				input.close();
				System.exit(0);
			}
		}
	}
	
}
