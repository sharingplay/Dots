package calculos;

public class Calculos {
	/**
	 * Calculos matematicos para validar distancias entre puntos
	 * @param x1 x del punto 1
	 * @param y1 y del punto 1
	 * @param x2 x del punto 2
	 * @param y2 y del punto 2
	 * @return devuelve la distancia entre puntos
	 */
	public int distancia(int x1,int y1,int x2,int y2) {//distancia entre 2 puntos
	int distancia = (int) Math.sqrt( Math.pow(x1 - x2,2) + Math.pow(y1 - y2,2));
	return distancia;
	}
	public int pitagoras(int x) {//distancia maxima permitida de un movimiento
		int distanciaMax =  (int) Math.sqrt(Math.pow(x,2) + Math.pow(x,2));
		return distanciaMax;
	}
}
