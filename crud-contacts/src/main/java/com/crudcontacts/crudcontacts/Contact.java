package com.crudcontacts.crudcontacts;

public class Contact {
	
	private String id;
	private String name;
	private String telephone;
	
	public Contact() {
		
	}
	
	public Contact(String id, String name, String telephone) {
		this.id = id;
		this.name = name;
		this.telephone = telephone;
	}
	
	public boolean isNovo() {
		return id == null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
