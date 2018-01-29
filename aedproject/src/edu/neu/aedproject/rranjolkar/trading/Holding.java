package edu.neu.aedproject.rranjolkar.trading;

import edu.neu.aedproject.rranjolkar.IdObject;

public class Holding extends IdObject {
	private ClientAccount clientAccount;

	private Security security;

	private double quantity;

	private double price;

	public Holding(ClientAccount clientAccount, Security security,
			double quantity, double price) {
		super();
		this.clientAccount = clientAccount;
		this.security = security;
		this.quantity = quantity;
		this.price = price;
	}

	public ClientAccount getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double totalPrice() {
		return quantity * price;
	}

}
