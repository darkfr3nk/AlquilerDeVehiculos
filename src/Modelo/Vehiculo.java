package Modelo;

public class Vehiculo {
	private int codigo; 
	private String marca; 
	private String modelo; 
	private String placa; 
	private String color; 
	private int numPuertas; 
	private int numPasajeros; 
	private int numMaletas; 
	private int velocidadMax;
	private String tipoCombustible; 
	private int kilometraje;
	private boolean disponible;
	private double precio;
	private String tipo;
	private String imagenURL;
	
	public Vehiculo(){
		
	}
	
	public Vehiculo(int codigo, String marca, String modelo, String placa, String color, int numPuertas,
			int numPasajeros, int numMaletas, int velocidadMax, String tipoCombustible, int kilometraje,
			boolean disponible, double precio, String tipo, String imagenURL) {
		this.codigo = codigo;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.color = color;
		this.numPuertas = numPuertas;
		this.numPasajeros = numPasajeros;
		this.numMaletas = numMaletas;
		this.velocidadMax = velocidadMax;
		this.tipoCombustible = tipoCombustible;
		this.kilometraje = kilometraje;
		this.disponible = disponible;
		this.precio = precio;
		this.tipo = tipo;
		this.imagenURL = imagenURL;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }

    public int getNumMaletas() {
        return numMaletas;
    }

    public void setNumMaletas(int numMaletas) {
        this.numMaletas = numMaletas;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getImagenURL() {
		return imagenURL;
	}

	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}
    
}