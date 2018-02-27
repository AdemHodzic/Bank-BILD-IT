public class Account {
	private int accountNumber;
	private String accountName;
	private double accountAmount;
	
	
	Account(int accountNumber, String accountName){
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountAmount = 0;
	}
	
	Account(int accountNumber, String accountName, double accountAmount){
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountAmount = accountAmount;
	}
	
	public void transfer(Account account, double amount) {
		this.setAccountAmount(this.getAccountAmount()-amount);
		account.setAccountAmount(this.getAccountAmount()+amount);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(double accountAmount) {
		this.accountAmount = accountAmount;
	}
	
	
}
