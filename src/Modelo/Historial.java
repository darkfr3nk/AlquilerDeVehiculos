package Modelo;

import java.time.LocalDate;

public class Historial {
	
	private int nroRegistro;
	private String cliente;
	private String Placa;
	private int kmRecorridos;
	private LocalDate FechaAlq;
	private LocalDate FechaPago;
	private double total;
	private double subtotal;
	private double igv;
	
	public Historial() {
		
	}
	
	public Historial(int nroRegistro, String cliente, String placa, int kmRecorridos, LocalDate fechaAlq,
			LocalDate fechaPago, double total, double subtotal, double igv) {
		super();
		this.nroRegistro = nroRegistro;
		this.cliente = cliente;
		Placa = placa;
		this.kmRecorridos = kmRecorridos;
		FechaAlq = fechaAlq;
		FechaPago = fechaPago;
		this.total = total;
		this.subtotal = subtotal;
		this.igv = igv;
	}
	
	
	public int getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(int nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getPlaca() {
		return Placa;
	}
	public void setPlaca(String placa) {
		Placa = placa;
	}
	public int getKmRecorridos() {
		return kmRecorridos;
	}
	public void setKmRecorridos(int kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}
	public LocalDate getFechaAlq() {
		return FechaAlq;
	}
	public void setFechaAlq(LocalDate fechaAlq) {
		FechaAlq = fechaAlq;
	}
	public LocalDate getFechaPago() {
		return FechaPago;
	}
	public void setFechaPago(LocalDate fechaPago) {
		FechaPago = fechaPago;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getIgv() {
		return igv;
	}
	public void setIgv(double igv) {
		this.igv = igv;
	}
	
	

}
