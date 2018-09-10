package listas;

public class listaDob {
	private NodoDob inicio;
	private NodoDob fin;

public listaDob() {
}
public void insertarInicio(NodoDob valor) {
	if (inicio == null){
		inicio = valor;
		fin = valor;
	}
	else {
	valor.setSiguiente(inicio);
	inicio.setAnterior(valor);
	inicio = valor;
	}
}
public void insertarFinal (NodoDob valor) {
	if (fin == null){
		inicio = valor;
		fin = valor;
	}
	else {
	fin.setSiguiente(valor);
	valor.setAnterior(fin);
	fin = valor;
	}
}
public void eliminar(NodoDob x) {
	if (inicio != null){
		if (inicio == x){
			inicio = inicio.getSiguiente();
		}
		else {
			NodoDob actual = inicio;
			while(actual.getSiguiente()!= x) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(actual.getSiguiente().getSiguiente());
			actual.getSiguiente().getSiguiente().setAnterior(actual);
		}
	}
	
}
}