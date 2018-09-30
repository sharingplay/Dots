package listas;
/** 
 * @author Mario
 * clase nodo
 */
public class Nodo {
	//atributos del nodo, su valor y al que esta enlazado.
    protected Nodo next;
    protected Object valor;
 
    public Nodo(Object valor) {
        this.next = null;
        this.valor = valor;
    }

	public Nodo getNext() {
		return next;
	}

	public void setNext(Nodo next) {
		this.next = next;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
    
}