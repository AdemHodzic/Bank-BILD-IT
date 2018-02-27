import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database {
	private ArrayList<Account> accountList= new ArrayList<>();
	private Path path = Paths.get("files/accounts.txt");
	
	Database(){}
	
	public void read() throws Exception {
		BufferedReader reader = Files.newBufferedReader(path);
		accountList.clear();
		String temp;
		while((temp=reader.readLine())!=null) {
			String[] arr = temp.split(" ");
			Account tempAccount = new Account(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2]));
			accountList.add(tempAccount);
		}
		reader.close();
	}
	
	private void write() throws Exception{
		BufferedWriter writer = Files.newBufferedWriter(path);
		for(Account acc:accountList) {
			writer.write(acc.getAccountNumber() + " " + acc.getAccountName() + " " + acc.getAccountAmount());
			writer.newLine();
		}
		writer.flush();
	}
	
	public void details(int accountNumber) throws Exception {
		read();
		if(exists(accountNumber)) {
			for(Account acc:accountList) {
				if(acc.getAccountNumber()==accountNumber) {
					System.out.println("Details:"
				+ "\nName: " + acc.getAccountName()
				+ "\nNumber: " + acc.getAccountNumber()
				+ "\nAmount: " + acc.getAccountAmount());
				}
			}
		}else {
			System.out.println("We cannot show information about that account!\n"
					+ "We're sorry for inconvience.");
		}
	}
	
	public void createAccount(int accountNumber, String accountName) throws Exception {
		read();
		Account newAccount = new Account(accountNumber, accountName);
		if(validate(newAccount)) {
			accountList.add(newAccount);
			write();
		}else {
			System.out.println("We cannot make an account with those specifications!\n"
					+ "We're sorry for inconvience.");
			newAccount = null;
		}
	}
	
	public void transfer(Account source, Account target, double amount) throws Exception {
		read();
		if(source.getAccountAmount()<amount) {
			System.out.println("We cannot make the transfer\n"
					+ "We're sorry for inconvience");
		}else {
			
			//Very hardcoded
			//If anyone knows how to do it the right please send me a message
			accountList.get(getIndex(source.getAccountNumber()))
			.setAccountAmount(source.getAccountAmount()-amount);
			accountList.get(getIndex(target.getAccountNumber()))
			.setAccountAmount(target.getAccountAmount()+amount);
			write();
			read();
		}
	}
	
	private boolean validate(Account acc) {
		if(acc.getAccountNumber()<0) return false;
		for(Account temp:accountList) {
			if(temp.getAccountNumber()==acc.getAccountNumber()) return false;
		}
		return true;
	}
	
	private boolean exists(int num) {
		for(Account temp:accountList) {
			if(num == temp.getAccountNumber()) return true; //Moguca greska! razlicite reference
		}
		return false;
	}
	
	public Account getAccount(int accountNumber) throws Exception{
		read();
		for(Account temp:accountList) {
			if(temp.getAccountNumber()==accountNumber) return temp;
		}
		return null;
	}

	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	
	private int getIndex(int accNum) {
		for(int i = 0;i<accountList.size();i++) {
			if(accountList.get(i).getAccountNumber()==accNum) return i;
		}
		return -1;
	}
	
}
