package Modelo;

import java.time.*;

public class Registro {
	
	//atributos de clase
	private int nroRegistro;
	private String cliente;
	private String nroDocumento;
	private String nroLicencia;
	private String telefono;
	private int codigoVehiculo;
	private String placa;
	private int diasAlquiler;
	private LocalDate fechaRegistro;
	private LocalDate fechaDevolucion;
	private LocalTime horaRegistro;
	private String direccion;
	private String email;
	private boolean activo;
	
	
	//Metodo constructor vacio
    public Registro() {
    	
    }

	public Registro(int nroRegistro, String cliente, String nroDocumento, String nroLicencia, String telefono,
			int codigoVehiculo, String placa, int diasAlquiler, LocalDate fechaRegistro, LocalDate fechaDevolucion,
			LocalTime horaRegistro, String direccion, String email, boolean activo) {
		super();
		this.nroRegistro = nroRegistro;
		this.cliente = cliente;
		this.nroDocumento = nroDocumento;
		this.nroLicencia = nroLicencia;
		this.telefono = telefono;
		this.codigoVehiculo = codigoVehiculo;
		this.placa = placa;
		this.diasAlquiler = diasAlquiler;
		this.fechaRegistro = fechaRegistro;
		this.fechaDevolucion = fechaDevolucion;
		this.horaRegistro = horaRegistro;
		this.direccion = direccion;
		this.email = email;
		this.activo = activo;
	}

	//Metododos GET y SET
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

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNroLicencia() {
		return nroLicencia;
	}

	public void setNroLicencia(String nroLicencia) {
		this.nroLicencia = nroLicencia;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getCodigoVehiculo() {
		return codigoVehiculo;
	}

	public void setCodigoVehiculo(int codigoVehiculo) {
		this.codigoVehiculo = codigoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getDiasAlquiler() {
		return diasAlquiler;
	}

	public void setDiasAlquiler(int diasAlquiler) {
		this.diasAlquiler = diasAlquiler;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public LocalTime getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(LocalTime horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
