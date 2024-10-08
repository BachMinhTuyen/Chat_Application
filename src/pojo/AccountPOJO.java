package pojo;

public class AccountPOJO {
	private String username;
	private String password;
	private String fullName;
	private String avatar;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public AccountPOJO() {
		
	}
	public AccountPOJO(String username, String password, String fullName, String avatar) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.avatar = avatar;
	}
	public AccountPOJO(AccountPOJO acc) {
		this.username = acc.username;
		this.password = acc.password;
		this.fullName = acc.fullName;
		this.avatar = acc.avatar;
	}
}
