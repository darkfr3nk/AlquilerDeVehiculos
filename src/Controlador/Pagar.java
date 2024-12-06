package Controlador;

public class Pagar {
	
	public static double calcularMontoAPagar(int x) {
		double monto = 30.0;
		double adicional = 0.0;
		
		if(x > 300 && x <= 1000) {
			adicional = (x-300)*0.15;
		}
		else if(x > 1000){
			adicional = (700*0.15) + ((x-1000)*0.10);
		}
		System.out.println("Monto a pagar: "+(monto+adicional));
		return monto+adicional;
	}
	
	
	public static double calcularSubtotal(double x) {
		return x/1.18;
	}

}
