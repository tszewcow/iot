package org.iot.server.document;


import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String salt;
	private String createdOn;
	private String lastLogin;
	private String userRole;
	private boolean active;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString()
	{
		return new StringBuilder().append(id).append(", ").
				append(firstName).append(", ").
				append(lastName).append(", ").
				append(email).append(", ").
				append(password).append(", ").
				append(salt).append(", ").
				append(createdOn).append(", ").
				append(lastLogin).append(", ").
				append(userRole).append(", ").
				append(active).
				toString();
	}
}
