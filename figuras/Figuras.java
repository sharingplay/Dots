package figuras;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;

import listas.listaSimple;
 
/**
 * Clase figura, crea poligonos con una lista de puntos y los valida
 * @author wajib
 */
public class Figuras {
 
	listaSimple listaPuntos = new listaSimple();
	listaSimple triangulos = new listaSimple();
    Polygon poligono = new Polygon();
    int player = 0;
   
    public Figuras (listaSimple listaPuntos, int player) {
       
        int ajusteImagen = 8; //Ajuste de pixeles para que la imagen este dentro las lineas	
       /**
        * Usa los puntos de la lista que los contiene para formar poligonos
        */
        for (int i=0; i<listaPuntos.getSize(); i++) {
            Punto aux = (Punto) listaPuntos.getValor(i);
            Punto puntoNuevo = new Punto(aux.getX(), aux.getY());
            this.listaPuntos.agregar(puntoNuevo);
            poligono.addPoint(aux.getX()+ajusteImagen, aux.getY()+ajusteImagen);
        }
        this.player = player;//cambia de jugador
    }
    /**
     * @return el jugador actual
     */
    public int getPlayer () {
        return this.player;
    }
   
    public Polygon getPolygon () {
        return this.poligono;
    }
    
    public int getSegmentos () {
        return listaPuntos.getSize();
    }
    /**
     * @param figura
     * @return devuelve si 2 areas colisionan
     */
    public boolean colisiona (Figuras figura) {
        Area areaUtilizada = new Area((Shape)getPolygon());//crea un area con la forma del poligono grande
        Area area = new Area((Shape)figura.getPolygon());//crea un area con el poligono ingresado
        area.intersect(areaUtilizada);//verifica si una figura se traza dentro de otra
        return !(area.isEmpty());
    }
}