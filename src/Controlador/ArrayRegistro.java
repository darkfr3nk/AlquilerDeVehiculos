package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Registro;

public class ArrayRegistro {
	//Atributos
	private ArrayList<Registro> registros;
	private int numRegistroDia;
	private String ultimaFechaRegistro = "";
	
	//Metodo constructor
	public ArrayRegistro() {
		registros = new ArrayList<Registro>();
		cargar();
		verificarRegistros();
	}
	
	public void verificarRegistros() {
	    try {
	    	if (!registros.isEmpty()) {
	    		Registro ultimo = registros.get(tamanio() - 1);
	    		int numeroReg = ultimo.getNroRegistro();
	    		System.out.println ("Ultimo Numero de registro: " +numeroReg);
	    		ultimaFechaRegistro = String.valueOf(numeroReg).substring(0, 8);
	    		System.out.println ("ultima fecha:"+ ultimaFechaRegistro);
	            String dia = String.valueOf(numeroReg).substring(8);
	            System.out.println ("ultima día:"+ dia);
	            numRegistroDia = Integer.parseInt(dia);
	        }
	    	else {
	    		ultimaFechaRegistro = "";
	    		}
	    	}
	    catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null, "Error al establecer la fecha: " + ex.getMessage());
	    }
	}
	
	//Metodo para calcular el nro de Registro
    public int calcularNroRegistro() {
    	LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatoAnio = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter formatoMes = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd");
        int anio = Integer.parseInt(hoy.format(formatoAnio));
        int mes = Integer.parseInt(formatoMes.format(hoy));
        int dia = Integer.parseInt(formatoDia.format(hoy));
        calcularNroDia(hoy);
        int nroRegistro = anio * 1000000 + mes * 10000 + dia * 100 + numRegistroDia;
        return nroRegistro;
    }

    //Metodo para calcular número correlativo del día
    private void calcularNroDia(LocalDate actual) {
        DateTimeFormatter fFecha = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaActual = actual.format(fFecha);
        if (fechaActual.equals(ultimaFechaRegistro)) {
            numRegistroDia++;
        } else {
            ultimaFechaRegistro = fechaActual;
            numRegistroDia = 1;
        }
    }

	//Metodo para obtener el numero de registros
	public int tamanio() {
		return registros.size();
	}
	
	//Metodo para devolver el registro
	public Registro obtener(int i) {
		return registros.get(i);
	}
	
	//Metodo para devolver registro por su nro de registro
	public Registro buscarRegistro(int nro) {
		for(Registro x : registros) {
			if(x.getNroRegistro()==nro) {
				return x;
			}
		}
		return null;
	}
	
	//Metodo para crear un nuevo registro
	public void crearRegistro(Registro r) {
		registros.add(r);
		guardar();
				
	}
	//Metodo para alamacenar los datos en un array de objetos
	public Object[] crearObjRegistro(Registro r) {
		Object[] objReporte = new Object[11];
		objReporte[0] = r.getNroRegistro();
		objReporte[1] = r.getCliente();
		objReporte[2] = r.getNroDocumento();
		objReporte[3] = r.getNroLicencia();
		objReporte[4] = r.getTelefono();
		objReporte[5] = r.getCodigoVehiculo();
		objReporte[6] = r.getPlaca();
		objReporte[7] = r.getDiasAlquiler();
		objReporte[8] = r.getFechaDevolucion();
		objReporte[9] = r.getFechaRegistro();
		objReporte[10] =r.getHoraRegistro().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		return objReporte;
	}
	
	public void guardar() {
		PrintWriter pw;
		String linea;
		try {
			pw = new PrintWriter(new FileWriter("src/Datos/registros.txt"));
			for(Registro  x : registros) {
				linea = x.getNroRegistro()+";"+
						x.getCliente()+";"+
						x.getNroDocumento()+";"+
						x.getNroLicencia()+";"+
						x.getTelefono()+";"+
						x.getCodigoVehiculo()+";"+
						x.getPlaca()+";"+
						x.getDiasAlquiler()+";"+
						x.getFechaRegistro()+";"+
						x.getFechaDevolucion()+";"+
						x.getHoraRegistro()+";"+
						x.getDireccion()+";"+
						x.getEmail()+";"+
						x.isActivo();
				pw.println(linea);
			}
			pw.close();
		}
		catch(Exception ex) {
			System.out.println("Error en la escritura: " + ex);
		}
	}
	
	public void cargar() {
		File fl = new File("src/Datos/registros.txt");
		BufferedReader br;
		String linea;
		String s[];
		
		int nroRegistro, codVehi,diasAlqui;
		String cliente, nroDoc, nroLic, telefono, placa,direcion,email ;
		LocalDate fechaR, fechaD;
		LocalTime horaR;
		boolean activo;
		
		try {
			if(fl.exists()) {
				br = new BufferedReader(new FileReader(fl));
				while((linea = br.readLine())!= null) {
					s= linea.split(";");
					nroRegistro = Integer.parseInt(s[0]);
					cliente = s[1];
					nroDoc = s[2];
					nroLic = s[3];
					telefono = s[4];
					codVehi = Integer.parseInt(s[5]);
					placa = s[6];
					diasAlqui = Integer.parseInt(s[7]);
					fechaR = LocalDate.parse(s[8]);
					fechaD = LocalDate.parse(s[9]);
					horaR = LocalTime.parse(s[10]);
					direcion = s[11];
					email = s[12];
					activo = Boolean.parseBoolean(s[13]);
					Registro nr = new Registro(nroRegistro, cliente, nroDoc, nroLic, telefono, codVehi, placa, diasAlqui, fechaR, fechaD, horaR, direcion,email, activo);
					registros.add(nr);
				}
				br.close();
			}
		}
		catch(Exception ex) {
			System.out.println("Error al cargar archivo de Registro");
			JOptionPane.showMessageDialog(null, "Error al cargar archivo de Registro: " +ex);
		}
	}
}
