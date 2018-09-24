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
            head.next = nodoTemporal;
            size++;
        }
    }
   
    public void borrar (Object valor) {
        if (size == 0) {
        }
        else {
            size --;
            if (valor == (head.valor)) {
                head = head.next;
            }
            else {
                Nodo temporal = head;
                while (temporal != null) {
 
                    if (temporal.next.valor.equals(valor)) { // temporal.data == data does not work very well
                        temporal.next = temporal.next.next;
                    }
                    temporal = temporal.next;
                }
            }
        }
    }
   
    public Object getValor (Object valor) {
        Nodo temporal = head;
        while (temporal != null) {
            if (temporal.valor.equals(valor)) {
                return temporal.valor;
            }
            temporal = temporal.next;
        }
        return null;
    }
   
    public Object getValor (int indice) {
        Nodo temporal = head;
        int contador = 0;
        while (contador != indice) {
            temporal = temporal.next;
            contador ++;
            if (temporal == null) {
                return null;
            }
        }
        return temporal.valor;
    }

  
    @Override
    public String toString () {
        Nodo temp = head;
        String result = "";
       
        while (temp != null) {
            result += "[" + temp.valor.toString() + "]";
            temp = temp.next;
            if (temp != null) {
                result += (" ->");
            }
        }
       
        return result;
    }

	public int getSize() {
		return this.size;
	}
   
}