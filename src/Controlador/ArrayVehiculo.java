package Controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import Modelo.Vehiculo;

public class ArrayVehiculo {
	private ArrayList<Vehiculo> vehiculos;

	public ArrayVehiculo() {
		vehiculos = new ArrayList<Vehiculo>();
		cargar();
	}

	public void adicionar(Vehiculo v) {
		vehiculos.add(v);
		guardar();
	}
	
	public int getTamanio() {
		return vehiculos.size();
	}
	
	public Vehiculo obtener(int x) {
		return vehiculos.get(x);
	}
	
	public Vehiculo obtenerXCOdigo(int x) {
		for(Vehiculo v : vehiculos) {
			if(v.getCodigo()==x) {
				return v;
			}
		}
		return null;
	}
	
	public void desactivarVehiculo(Vehiculo x) {
		for(Vehiculo a : vehiculos) {
			if(a.equals(x)) {
				a.setDisponible(false);
			}
		}
	}
	
	public void activarVehiculo(Vehiculo x) {
		for(Vehiculo a : vehiculos) {
			if(a.equals(x)) {
				a.setDisponible(true);
			}
		}
	}
	
	public boolean haySiguiente(Vehiculo x) {
		Iterator<Vehiculo> it = vehiculos.iterator();
		return (it.hasNext())? true : false;
	}
	
	public boolean hayAnterior(Vehiculo x) {
		for(int i = x.getCodigo()-1; i>=0; i--) {
			if(vehiculos.get(i).isDisponible()){
				return true;
			}
		}
		return false;
	}
	
	public int vehiculosDisponibles() {
		int i = 0;
		for(Vehiculo x : vehiculos) {
			if(x.isDisponible()) {
				i++;
			}
		}
		return i;
	}
	
	public Vehiculo primerVehiculoDispnible() {
		for(Vehiculo x : vehiculos) {
			if(x.isDisponible()) {
				return x;
			}
		}
		return null;
	}
	
	public void guardar() {
		PrintWriter pw;
		String linea;
		try {
			pw = new PrintWriter(new FileWriter("src/Datos/vehiculos.txt"));
			for(Vehiculo x : vehiculos) {
				linea = x.getCodigo()+";"+
						x.getMarca()+";"+
						x.getModelo()+";"+
						x.getPlaca()+";"+
						x.getColor()+";"+
						x.getNumPuertas()+";"+
						x.getNumPasajeros()+";"+
						x.getNumMaletas()+";"+
						x.getVelocidadMax()+";"+
						x.getTipoCombustible()+";"+
						x.getKilometraje()+";"+
						x.isDisponible()+";"+
						x.getPrecio()+";"+
						x.getTipo()+";"+
						x.getImagenURL();
				pw.println(linea);
			}
			pw.close();
		}
		catch(Exception ex) {
			System.out.println("Error en la escritura: " + ex);
		}
	}
	
	public void cargar() {
		File fl = new File("src/Datos/vehiculos.txt");
		BufferedReader br;
		String linea;
		String s[];
		
		int codigo,numPuertas,numPasajeros,numMaletas,VelocidadMax,kilometraje;
		String marca,modelo,placa,color,tipoCombustible,tipo,imagenURL;
		boolean disponible;
		double precio;
		try {
			if(fl.exists()) {
				br = new BufferedReader(new FileReader(fl));
				while((linea = br.readLine())!=null) {
					s = linea.split(";");
					codigo = Integer.parseInt(s[0]);
					marca = s[1];
					modelo = s[2];
					placa = s[3];
					color = s[4];
					numPuertas = Integer.parseInt(s[5]);
					numPasajeros = Integer.parseInt(s[6]);
					numMaletas = Integer.parseInt(s[7]);
					VelocidadMax = Integer.parseInt(s[8]);
					tipoCombustible = s[9];
					kilometraje  = Integer.parseInt(s[10]);
					disponible = Boolean.parseBoolean(s[11]);
					precio = Double.parseDouble(s[12]);
					tipo = s[13];
					imagenURL = s[14];
					Vehiculo v = new Vehiculo(codigo,marca,modelo,
							placa,color,numPuertas,numPasajeros,numMaletas
							,VelocidadMax,tipoCombustible,kilometraje,disponible
							,precio,tipo,imagenURL);
					vehiculos.add(v);
				}
				br.close();
			}
		}
		catch(Exception ex) {
			System.out.println("error en la carga: "+ex+" - "+vehiculos.size());
		}
	}

}
