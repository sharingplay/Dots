package listas;

public class NodoDob {

	private NodoDob anterior;
	private Object valor;
	private NodoDob siguiente;
	
	public NodoDob() {
	}
	
	public NodoDob(Object valor,NodoDob anterior,NodoDob siguiente) {
		this.valor = valor;
		this.anterior = anterior;
		this.siguiente = siguiente;
	}
	public NodoDob getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoDob anterior) {
		this.anterior = anterior;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public NodoDob getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoDob siguiente) {
		this.siguiente = siguiente;
	}
}
