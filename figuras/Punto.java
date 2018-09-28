package figuras;
import listas.listaSimple;
 
/**
 * @author Mario
 *clase punto
 */
public class Punto {
	//atributos x,y y vecinos
    private int x;
    private int y;
    private listaSimple vecinos = new listaSimple();
   
    public int getX() {
        return this.x;
    }
 
    public void setX(int x) {
        this.x = x;
    }
 
    public int getY() {
        return this.y;
    }
 
    public void setY(int y) {
        this.y = y;
    }
 
    public listaSimple getVecinos() {
        return this.vecinos;
    }
 
   
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }
 
    public void nuevoVecino (Punto vecino) {
    	this.vecinos.agregar(vecino);
    }
   
    @Override
    public boolean equals (Object valor) {//compara el valor de un punto con otro
        Punto punto = (Punto) valor;
        return (getX()==punto.getX() && getY()==punto.getY());
    }
}