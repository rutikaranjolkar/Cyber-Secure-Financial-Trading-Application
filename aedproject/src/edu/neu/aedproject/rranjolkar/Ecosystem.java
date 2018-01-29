package edu.neu.aedproject.rranjolkar;

public class Ecosystem extends IdObject {
	private String name;

	private NetworkDirectory networkDirectory;

	public Ecosystem() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NetworkDirectory getNetworkDirectory() {
		return networkDirectory;
	}

	public void setNetworkDirectory(NetworkDirectory networkDirectory) {
		this.networkDirectory = networkDirectory;
	}

	@Override
	public String toString() {
		return name;
	}

}
