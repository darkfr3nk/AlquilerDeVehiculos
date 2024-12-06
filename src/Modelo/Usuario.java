package Modelo;

public class Usuario {
	String user;
	String pass;
	String nombre;
	boolean admin;
	
	public Usuario() {	
	}
	
	public Usuario(String user, String pass, String nombre, boolean admin) {
		this.user = user;
		this.pass = pass;
		this.nombre = nombre;
		this.admin = admin;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	

}
