package listas;
public class listaSimple {
 /**
  * clase lista simple
  */
    private Nodo head = null;
    private int size = 0;

    public void agregar (Object valor) {
        Nodo aux = new Nodo(valor);
        if (head == null) {
            head = aux;
            size++;
        }
        else {
            Nodo nodoTemporal = head;
            head = aux;
            head.setNext(nodoTemporal);
            size++;
        }
    }
   
    public void borrar (Object valor) {
        if (size == 0) {
        }
        else {
            size --;
            if (valor == head.getValor()) {
                head = head.getNext();
            }
            else {
                Nodo temporal = head;
                while (temporal != null) {
 
                    if (temporal.getNext().getValor().equals(valor)) {
                        temporal.setNext(temporal.getNext().getNext()) ;
                    }
                    temporal = temporal.getNext();
                }
            }
        }
    }
   
    public Object getValor (Object valor) {
        Nodo temporal = head;
        while (temporal != null) {
            if (temporal.getValor().equals(valor)) {
                return temporal.getValor();
            }
            temporal = temporal.getNext();
        }
        return null;
    }
    public Object getValor (int indice) {
        Nodo temporal = head;
        int contador = 0;
        while (contador != indice) {
            temporal = temporal.getNext();
            contador ++;
            if (temporal == null) {
                return null;
            }
        }
        return temporal.getValor();
    }
	public int getSize() {
		return this.size;
	}
   
}