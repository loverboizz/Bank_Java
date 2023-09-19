package Model;

public class User {
	private String PN;
	private String Name;
	private String Balance;
	private String Email;
	public User(String pN, String name, String balance, String email) {
		super();
		PN = pN;
		Name = name;
		Balance = balance;
		Email = email;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPN() {
		return PN;
	}
	public void setPN(String pN) {
		PN = pN;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	
}
