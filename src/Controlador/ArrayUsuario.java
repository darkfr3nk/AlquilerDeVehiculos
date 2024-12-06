package Controlador;

import java.io.*;
import java.util.ArrayList;

import Modelo.Usuario;

public class ArrayUsuario {
	
	ArrayList<Usuario> usuarios;
	
	//Metodo constructor
	public ArrayUsuario() {
		usuarios= new ArrayList<Usuario>();
		cargar();
	}
	//Metodo para agregar un usuario al aaray
	public void agregar(Usuario u) {
		usuarios.add(u);
		guardar();
	}
	
	//Metodo para eliminar usuario
	public void eliminar(Usuario u) {
		usuarios.remove(u);
		guardar();
	}
	
	public int getTamanio() {
		return usuarios.size();
	}
	
	public Usuario getUsuario(int x) {
		return usuarios.get(x);
	}
	
	public Usuario getUsuarioXNombre(String n) {
		for(Usuario x : usuarios) {
			if(x.getNombre().equals(n)) {
				return x;
			}
		}
		return null;
	}
	
	public Usuario getUsuarioXUsuario(String n) {
		for(Usuario x : usuarios) {
			if(x.getUser().equals(n)) {
				return x;
			}
		}
		return null;
	}
	
	public boolean existeUsuario(Usuario u) {
		for(Usuario x : usuarios) {
			if(x.getUser().equals(u.getUser())) {
				return true;
			}
		}
		return false;
	}
	
	public void reemplazarUsuario(Usuario u) {
		for(int i = 0; i<getTamanio();i++) {
			if(usuarios.get(i).getUser().equals(u.getUser())) {
				usuarios.get(i).setPass(u.getPass());
				guardar();
			}
		}
	}
	
	public void guardar() {
		PrintWriter pw;
		String linea;
		try {
			pw = new PrintWriter(new FileWriter("src/Datos/usuarios.txt"));
			for(Usuario x : usuarios) {
				linea = x.getUser()+";"+
						x.getPass()+";"+
						x.getNombre()+";"+
						x.isAdmin();
				pw.println(linea);
			}
			pw.close();
		}
		catch(Exception ex) {
			System.out.println("Error al guardar:"+ex);
		}
	}
	
	public void cargar() {
		File fl = new File("src/Datos/usuarios.txt");
		BufferedReader br;
		String linea,s[],user,pass,nombre;
		boolean admin;
		try {
			if(fl.exists()) {
				br = new BufferedReader(new FileReader(fl));
				while((linea = br.readLine())!=null) {
					s = linea.split(";");
					user = s[0];
					pass = s[1];
					nombre = s[2];
					admin = Boolean.parseBoolean(s[3]);
					usuarios.add(new Usuario(user,pass,nombre,admin));
					
				}
				br.close();
				
			}
		}
		catch(Exception ex) {
			System.out.println("Error al cargar: "+ex);
		}
	}
	
	

}
