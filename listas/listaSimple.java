package listas;

public class listaSimple {
	Nodo head;
	int size;
	
	public listaSimple() {
		this.head = null;
		this.size = 0;
	}
	public boolean estaVacia() {
		if(head == null) {
			return true;
		}
		else {
			return false;
		}
	}
	public Object obtenerValor(int index) {
		int contador = 0;
		Nodo temporal = head;
		while (contador < index) {
			temporal = temporal.obtenerSiguiente();
			contador++;
		}
		return temporal.obtenerValor();
	}
	public void addPrimero(Object objeto) {
		if (head == null) {
			this.head = new Nodo(objeto);
		}
		else {
			Nodo temp = head;
			Nodo nuevo = new Nodo(objeto);
			nuevo.enlazarSiguiente(temp);
			this.head = nuevo;
		}
		this.size++;
	}
	public void eliminar (int index) {
		int contador = 0;
		Nodo temp = head;
		while (contador <index-1){
			temp = temp.obtenerSiguiente();
			contador ++;
		}
		this.size--;
		temp.enlazarSiguiente(temp.obtenerSiguiente().obtenerSiguiente());
	}
	public int getSize() {
		return this.size;
	}
}