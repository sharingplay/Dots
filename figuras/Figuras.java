package figuras;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;

import listas.listaSimple;
 
/**
 * Clase figura, crea poligonos con una lista de puntos y los valida
 * @author wajib
 *
 */
public class Figuras {
 
	listaSimple listaPuntos = new listaSimple();
	listaSimple triangulos = new listaSimple();
    Polygon poligono = new Polygon();
    Shape forma;
    int player = 0;
   
    public Figuras (listaSimple listaPuntos, listaSimple allDots, int player) {
       
        int ERROR = 8; // Margen de error para dibujar en el centro de la lista
       
        for (int i=0; i<listaPuntos.getSize(); i++) {
            Punto temporal = (Punto) listaPuntos.getValor(i);
            this.listaPuntos.agregar(new Punto(temporal.getX(), temporal.getY()));
            poligono.addPoint(temporal.getX()+ERROR, temporal.getY()+ERROR);
        }
       
        this.player = player;
    }
    /**
     * 
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
     * 
     * @param figure
     * @return Si hay algo dentro del área del polígono
     */
    public boolean collide (Figuras figure) {
        Area area = new Area((Shape)this.getPolygon());
        Area areaUtilizada = new Area((Shape)figure.getPolygon());
        area.intersect(areaUtilizada);
        return !(area.isEmpty());//devuelve si tiene algo adentro un area o no
    } 
}