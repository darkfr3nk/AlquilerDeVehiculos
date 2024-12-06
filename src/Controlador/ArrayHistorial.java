package Controlador;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Historial;

public class ArrayHistorial {
	
    private ArrayList<Historial> historiales;

    public ArrayHistorial() {
        historiales = new ArrayList<>();
        cargar();
    }

    //Metodo para obtener el tamaño del historial
    public int tamanio() {
        return historiales.size();
    }

    //Metodo para obtener un historial específico
    public Historial obtener(int index) {
        return historiales.get(index);
    }

    //Metodo para buscar un historial por número de registro
    public Historial buscarPorNroRegistro(int nroRegistro) {
        for (Historial h : historiales) {
            if (h.getNroRegistro() == nroRegistro) {
                return h;
            }
        }
        return null;
    }

    //Metodo para agregar un nuevo historial
    public void agregarHistorial(Historial h) {
        historiales.add(h);
        guardar();
    }

    //Metodo para convertir un historial en un arreglo de objetos (para tablas o reportes)
    public Object[] convertirAObjeto(Historial h) {
        Object[] objHistorial = new Object[9];
        objHistorial[0] = h.getNroRegistro();
        objHistorial[1] = h.getCliente();
        objHistorial[2] = h.getPlaca();
        objHistorial[3] = h.getKmRecorridos()+" Km";
        objHistorial[4] = h.getFechaAlq();
        objHistorial[5] = h.getFechaPago();
        objHistorial[6] = "S/."+h.getTotal();
        objHistorial[7] = "S/."+h.getSubtotal();
        objHistorial[8] = "S/."+h.getIgv();
        return objHistorial;
    }

    //Metodo para guardar los historiales en un archivo
    public void guardar() {
        PrintWriter pw;
        String linea;
        try {
            pw = new PrintWriter(new FileWriter("src/Datos/historial.txt"));
            for (Historial h : historiales) {
                linea = h.getNroRegistro() + ";" +
                        h.getCliente() + ";" +
                        h.getPlaca() + ";" +
                        h.getKmRecorridos() + ";" +
                        h.getFechaAlq() + ";" +
                        h.getFechaPago() + ";" +
                        h.getTotal() + ";" +
                        h.getSubtotal() + ";" +
                        h.getIgv();
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e);
        }
    }

    //Metodo para cargar los historiales desde un archivo
    public void cargar() {
        File file = new File("src/Datos/historial.txt");
        BufferedReader br;
        String linea;
        String[] datos;

        int nroRegistro, kmRecorridos;
        String cliente, placa;
        LocalDate fechaAlq, fechaPago;
        double total, subtotal, igv;

        try {
            if (file.exists()) {
                br = new BufferedReader(new FileReader(file));
                while ((linea = br.readLine()) != null) {
                    datos = linea.split(";");
                    nroRegistro = Integer.parseInt(datos[0]);
                    cliente = datos[1];
                    placa = datos[2];
                    kmRecorridos = Integer.parseInt(datos[3]);
                    fechaAlq = LocalDate.parse(datos[4]);
                    fechaPago = LocalDate.parse(datos[5]);
                    total = Double.parseDouble(datos[6]);
                    subtotal = Double.parseDouble(datos[7]);
                    igv = Double.parseDouble(datos[8]);

                    Historial historial = new Historial(nroRegistro, cliente, placa, kmRecorridos, fechaAlq, fechaPago, total, subtotal, igv);
                    historiales.add(historial);
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cargar archivo de historial: " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar archivo de historial: " + e);
        }
    }
}
