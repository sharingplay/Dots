package listas;

public class Nodo {
	Object valor;
	Nodo siguiente;
	public Nodo (Object valor) {
		this.valor = valor;
		this.siguiente = null;
	}
	public Object obtenerValor() {
		return this.valor;
	}
	public void enlazarSiguiente(Nodo n) {
		this.siguiente = n;
	}
	public Nodo obtenerSiguiente(){
		return this.siguiente;
	}
}
